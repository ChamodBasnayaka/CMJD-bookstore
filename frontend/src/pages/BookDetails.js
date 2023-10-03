import { useEffect, useState } from "react";
import { Alert, Button, Col, Container, Image, Row } from "react-bootstrap";
import { getRequest } from "../service/ApiService";
import { Link, useNavigate, useParams } from "react-router-dom";
import { connect, useDispatch, useSelector } from "react-redux";
import { selectedProduct } from "../redux/actions/ProductActions";
import addToCart from "../redux/actions/cartActions";
import IncDecCounter from "../service/IncDecCounter";



const BookDetails = ({ addToCart }) => {
    const product = useSelector((state) => state.product);
    const { id } = useParams();
    const dispatch = useDispatch();
    const [loading, setLoading] = useState(true);
    const [amount, setAmount] = useState(1);
    const navigate = useNavigate();
    const [show, setShow] = useState(false);
    let fetchDetails = true;

    const fetchBookDetails = async () => {
        try {
            const response = await getRequest(`/books/${id}`);
            dispatch(selectedProduct(response.data));
            setLoading(false);

        } catch (error) {
            console.error("Error:", error);
        }

    }
    useEffect(() => {
        if (id) {
            if (fetchDetails) {
                fetchDetails = false;
                fetchBookDetails();
            }

        }
    }, [id])



    const handleCart = (id, name, price, language, category, qty, coverImage, discription, auther) => {
        if (amount <= qty) {
            const book = {
                "id": id,
                "name": name,
                "price": price,
                "language": language,
                "category": category,
                "amount": amount,
                "subTotal":amount*price,
                
                "product": {
                    "id": id,
                    "title": name,
                    "auther": auther,
                    "price": price,
                    "coverImage": coverImage,
                    "description": discription
                }

            }
            addToCart(book);
        } else {
            setShow(true);
            setAmount(1);
        }






    }
    if (show) {
        return (
            <div className="">
                <Alert variant="danger" onClose={() => setShow(false)} dismissible>
                    <Alert.Heading>Oh...! sorry, We haven't enough books in Store.</Alert.Heading>

                </Alert>
            </div>

        );
    }


    if (loading) {
        return (
            <div>
                Loading....
            </div>
        )
    }

    return (
        <>
            <Container>
                <header className="header mb-3">
                    <div className="my-title mb-2">
                        Book Details
                    </div>
                    <div className='right-manu'>
                        <Button className='nav-btn' onClick={() => { navigate("/cart") }}>Cart</Button>
                        <Button className='nav-btn' onClick={() => { navigate("/home") }}>Home</Button>
                    </div>
                </header>

                <Row >
                    <Col lg={6}>
                        <Row className="my-title mb-2">{product.title}</Row>
                        <Row className="my-col mb-2 my-row"><Image className="mt-3" src={`http://localhost:8082/coverImage/${product.coverImage}`} height={250} alt='cover image' /></Row>
                        <Row className="my-col mb-1">Author: </Row>
                        <Row className="my-col mb-2">{product.auther}</Row>
                        <Row className="my-col mb-1">Price: </Row>
                        <Row className="my-col mb-2">{product.price}</Row>
                        <Row className="my-col mb-1">Language: </Row>
                        <Row className="my-col mb-2">{product.category.category_name}</Row>
                        <Row className="my-col mb-1">Category: </Row>
                        <Row className="my-col mb-2">{product.subCategory.subcategory_name}</Row>
                    </Col>
                    <Col className="my-col">
                        <span className="my-title">About;</span><br />

                        {product.description}
                    </Col>
                    
                </Row>
                <IncDecCounter onNumChange={(num) => { setAmount(num) }} />
                <Button className="add-btn" variant="secondary" onClick={() => {
                    handleCart(product.id, product.title, product.price, product.category.category_name,
                        product.subCategory.subcategory_name, product.quantity, product.coverImage, product.description, product.auther)
                }}>Add to Cart</Button>
                <Link to={"/cart"}>
                    <Button className="add-btn" variant="secondary">Cart</Button>
                </Link>




            </Container>


        </>

    )
}
export default connect(null, { addToCart })(BookDetails);