import { useEffect, useState } from "react";
import { getRequest, postRequestFile } from "../service/ApiService";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
const user_id = sessionStorage.getItem("user_id");

const Profile = () => {
    const [user, setUser] = useState(null);
    const [profileImage, setFrofileImage] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            const response = await getRequest(`/users/${user_id}`);
            setUser(response.data);
        }
        fetchUser();
    }, [])

    const handleFileChange = (event) => {
        setFrofileImage(event.target.files[0]);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = {
            "profileImage": profileImage
        }

        const response = await postRequestFile(`/users/${user_id}/profile`, data);
        if (response && response.status === 200) {
            setUser(response.data);
        }


    }
    return (
        <>
            <Container>
                <header className="header mb-3">
                    <div className="my-title">
                        User Profile
                    </div>
                    <div className='right-manu'>
                        <Link to={"/home"}>
                            <Button className='nav-btn'>Home</Button>
                        </Link>


                    </div>
                </header>

                {
                    user &&
                    <Row>
                        <Col className="my-col mb-1" lg={6}>Username:</Col>
                        <Col className="my-col mb-2">{user.username}</Col>
                        <Col className="my-col mb-1" lg={6}>Email:</Col>
                        <Col className="my-col mb-2">{user.email}</Col>
                        <Col className="my-col mb-1" lg={6}>Profile Picture:</Col>
                        <Col className="my-col">
                            <img src={`http://localhost:8082/uploads/${user.profileImage}`} width={250} />
                            <div>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group controlId="profileImage" className="mb-3">
                                        <Form.Label>profileImage</Form.Label>
                                        <Form.Control className="form-control-profile" type="file" onChange={handleFileChange} />
                                    </Form.Group>
                                    <div >
                                        <Button variant="primary" type="submit">Change</Button>
                                    </div>
                                </Form>
                            </div>

                        </Col>
                    </Row>

                }

            </Container>
        </>
    )
}
export default Profile;