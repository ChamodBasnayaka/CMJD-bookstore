import axios from "axios";
import { useState } from "react";
import { Button, Container, FloatingLabel, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";



const Login = () => {
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();
    const handleUsername = (event) => {
        setUsername(event.target.value);
    }
    const handlePassword = (event) => {
        setPassword(event.target.value);
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = {
            "username": username,
            "password": password
        }
        try {
            const response = await axios.post('http://localhost:8082/auth/login', data)
            setError("");
            setUsername("");
            setPassword("");

            sessionStorage.setItem('token', response.data.token);
            sessionStorage.setItem('user_id', response.data.id);
            sessionStorage.setItem('email', response.data.email);
            sessionStorage.setItem('username', response.data.username);
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;

            navigate("/home")

        } catch (error) {
            console.log(error);
            setError("Username or password incorect");
        }

    }
    return (
        <>
            <Container>
                <div className="login-box shadow-sm rounded">
                    <div className="text-center mb-5">
                        <h1>Login User</h1>
                    </div>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="username" label="Select Username" className="mb-3">
                            <Form.Control placeholder="Select username" value={username} onChange={handleUsername} />
                        </FloatingLabel>
                        <FloatingLabel controlId="password" label="Enter password" className="mb-3">
                            <Form.Control placeholder="Enter password" type="password" value={password} onChange={handlePassword} />
                        </FloatingLabel>
                        {error &&
                            <div className="text-danger mb-3">
                                {error}
                            </div>
                        }
                        <div>
                            <p>Don't have an Account ?..
                                <a href="/register" className="custom-link"> Sign Up </a> hear;
                            </p>
                        </div>

                        <div className="text-end" >
                            <Button variant="primary" type="submit">Login</Button>
                        </div>

                    </Form>

                </div>
            </Container>
        </>
    )
}
export default Login;