import { useEffect, useState } from "react";
import { Alert, Button, Container, FloatingLabel, Form, Table } from "react-bootstrap";
import { connect } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getRequest, postRequest, updateRequest } from "../service/ApiService";

const CartPage = ({ cartItems }) => {

    const navigate = useNavigate();
    const [createOrderEnable, setCreateOrderEnable] = useState(false);
    const [shippingDetails, setShippingDetails] = useState(null);
    const [mobile, setMobile] = useState("");
    const [address, setAddress] = useState("");
    const [postalCode, setPostalCode] = useState("");
    const userId = sessionStorage.getItem('user_id');
    const [show, setShow] = useState(false);

    useEffect(() => {
        if (cartItems.items.length !== 0 && shippingDetails !== null) {
            setCreateOrderEnable(true);
        } else {
            setCreateOrderEnable(false);
        }

    }, [cartItems.items, shippingDetails])

    const handleOrder = async () => {
        const response = await getRequest(`/users/${userId}`);

        const order = {
            "order_id": 1,
            "total": cartItems.total,
            "user": response.data,
            "shippingDetails": shippingDetails,
            "orderedBooks": cartItems.books
        }

        try {
            
            const res = await postRequest("/orders", order);

            if (res.status === 201) {
                const bookUpdatePromises = cartItems.items.map(async (item) => {
                    const amount = {
                        "amount": item.amount
                    }
                    const cart = {
                        "order_id":res.data.order_id,
                        "name":item.name,
                        "language":item.language,
                        "category":item.category,
                        "amount":item.amount,
                        "subTotal":item.subTotal,
                        "price":item.price

                    }
                    await postRequest("/shoppingcart",cart);
                    await updateRequest(`/books/${item.id}`, amount);

                })
                await Promise.all(bookUpdatePromises);
                
                setShow(true);
                cartItems.items = []
                cartItems.books = []
                cartItems.total = 0
                setAddress("");
                setMobile("");
                setPostalCode("");
                setShippingDetails(null);
            }
        } catch (error) {
            console.error("An error occurred:", error);
        }
    }
    const handleShipping = (event) => {
        event.preventDefault();
        setShippingDetails(
            {
                "address": address,
                "postalCode": postalCode,
                "mobile": mobile
            }
        )
    }
    if (show) {
        return (
            <div className="">
                <Alert variant="success" onClose={() => setShow(false)} dismissible>
                    <Alert.Heading>Your Order successfully completed!</Alert.Heading>

                </Alert>
            </div>

        );
    }


    return (
        <>
            <Container>
                <header className="header mb-3">
                    <div className="my-title mb-2">
                        Shoping Cart
                    </div>
                    <div className='right-manu'>
                        <Button className='nav-btn' onClick={() => { navigate("/home") }}>Home</Button>

                    </div>
                </header>

                <Table bordered hover >
                    <thead>
                        <tr >
                            <th>Title</th>
                            <th>Unit Price</th>
                            <th>Language</th>
                            <th>Category</th>
                            <th>Amount</th>
                            <th className="text-end">Sub Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            cartItems.items.map(book => {
                                return (
                                    <tr key={book.id}>
                                        <td>{book.name}</td>
                                        <td>$ {book.price}</td>
                                        <td>{book.language}</td>
                                        <td>{book.category}</td>
                                        <td>{book.amount}</td>
                                        <td className="text-end">$ {book.price * book.amount}</td>

                                        <td className="text-end btn-column">
                                            <Button variant="secondary" size="sm" className="mr-2 action-btn">Edit</Button>&nbsp;
                                            <Button variant="danger" size="sm" className="action-btn" >Delete</Button>
                                        </td>


                                    </tr>
                                )
                            })
                        }
                        <tr>
                            <th colSpan={5} className="text-end" >Total</th>
                            <th className="text-end">$ {cartItems.total}</th>
                        </tr>
                    </tbody>

                </Table>
                <div className="text-end mb-3">
                    <Button variant="secondary" disabled={!createOrderEnable} onClick={handleOrder}>Create Order</Button>
                </div>




            </Container>
            <Container>
                <div className="shipping-form">
                    <>
                        <div className="my-title mb-2">Shipping Details</div>
                        <Form onSubmit={handleShipping}>
                            <FloatingLabel
                                controlId="address"
                                label="Address"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Enter your Address" value={address} onChange={(event) => { setAddress(event.target.value) }} />
                            </FloatingLabel>
                            <FloatingLabel controlId="postalcode" label="Postal Code" className="mb-3">
                                <Form.Control type="text" placeholder="Postal Code" value={postalCode} onChange={(event) => { setPostalCode(event.target.value) }} />
                            </FloatingLabel>
                            <FloatingLabel controlId="mobile" label="Phone">
                                <Form.Control type="text" placeholder="Mobile Number" className="mb-3" value={mobile} onChange={(event) => { setMobile(event.target.value) }} />
                            </FloatingLabel>
                            <div className="text-end">
                                <Button variant="secondary" type="submit">Submit</Button>
                            </div>
                        </Form>

                    </>
                </div>
            </Container>

        </>
    )

}
const mapStateToProps = (state) => {
    return {
        cartItems: state.cart
    }

}
export default connect(mapStateToProps)(CartPage);