import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import { useEffect, useState } from 'react';
import { getRequest } from '../service/ApiService';
import { Button, Col, Dropdown, Image, NavDropdown, Row } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
const Home = () => {
    const [categories, setCategories] = useState(null);
    const [subCategories, setSubCategories] = useState(null);
    const [books, setBooks] = useState([]);
    const [bookList, setBookList] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            const response = await getRequest("/categories");
            setCategories(response.data);
            console.log(response);
        }
        fetchCategories();

        const fetchBooks = async () => {
            const response = await getRequest("/books");
            setBookList(response.data);
        }
        fetchBooks();
    }, [])


    const handleTypes = (assignedsubcategories) => {

        setSubCategories(assignedsubcategories);
    }

    const handleBooks = (async (category_id, subcategory_id) => {
        const response = await getRequest(`/categories/${category_id}/subcategories/${subcategory_id}/books`);
        setBooks(response.data);

    })
    const navigate = useNavigate();

    return (
        <>


            <div>
                <Container>
                    <div>
                        <header className='header'>

                            <Navbar expand="sm" className="navbar justify-content-end rounded">
                                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                                <Navbar.Collapse id="basic-navbar-nav">
                                    <Nav className="ml-auto px-3">

                                        <Nav.Link href="/home" className='nav-link nav-home px-3'>Home</Nav.Link>

                                        {
                                            categories && categories.map(category => {

                                                return (
                                                    <NavDropdown title={category.category_name} id={category.category_id} className='px-3' onClick={() => {
                                                        handleTypes(category.assignedsubcategories);
                                                        setBooks(null);

                                                    }}>
                                                        {
                                                            subCategories && subCategories.map(sub => {
                                                                return (
                                                                    <Dropdown >
                                                                        <Dropdown.Toggle id={sub.subcategory_id} className='dropdown-item' onClick={() => { handleBooks(category.category_id, sub.subcategory_id) }}>
                                                                            {sub.subcategory_name}

                                                                        </Dropdown.Toggle>

                                                                    </Dropdown>


                                                                )
                                                            })
                                                        }

                                                    </NavDropdown>


                                                )

                                            })
                                        }

                                    </Nav>
                                </Navbar.Collapse>
                            </Navbar>

                            <div className='right-manu'>
                                <Button className='nav-btn' onClick={() => { navigate("/cart") }}>Cart</Button>
                                <Button className='nav-btn' onClick={() => { navigate("/profile") }}>User</Button>
                            </div>
                        </header>

                    </div>
                    <div className='mt-3 book-data'>
                        <Row>
                            {
                                books && books.map(book => {
                                    return (

                                        <Col lg={2} className=' my-col' id={book.id}>
                                            <Image className="mb-2" src={`http://localhost:8082/coverImage/${book.coverImage}`} height={250} alt='cover image' />
                                            <Row className='px-3'>{book.title}</Row>
                                            <Row className='px-3'>{book.auther}</Row>
                                            <Row className='book-price px-3'>{book.price}</Row>
                                            <div >
                                                <Link to={`/bookdetails/${book.id}`}>
                                                    <Button variant='secondary' >Book Details</Button>
                                                </Link>

                                            </div>

                                        </Col>

                                    )
                                })
                            }
                        </Row>



                    </div>

                </Container>
            </div>
            <div>
                <Container>
                    <div className='mb-2 my-title'>
                        <>Books In Store</>
                    </div>
                    <Row>
                        {
                            bookList && bookList.map(book => {
                                return (
                                    <Col lg={2} className='mb-2 my-col'>
                                        <Image className="mb-2" src={`http://localhost:8082/coverImage/${book.coverImage}`} height={250} alt='cover image' />
                                        <Row className='px-3'>{book.title}</Row>
                                        <Row className='px-3'>{book.auther}</Row>
                                        <Row className='px-3'>{book.subCategory.subcategory_name}</Row>
                                        <Row className='book-price px-3'>{book.price}</Row>
                                        <Link to={`/bookdetails/${book.id}`}>
                                            <Button variant='secondary' className='mb-3'>Book Details</Button>
                                        </Link>
                                    </Col>
                                )
                            })
                        }
                    </Row>
                </Container>
            </div>


        </>
    )
}
export default Home;