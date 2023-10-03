import { useEffect, useState} from "react";
import { Button, Container, FloatingLabel, Form } from "react-bootstrap";
import axios from "axios";
import {useNavigate} from "react-router-dom"
const Register = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [registerEnable, setRegisterEnable] = useState(false);
    const [error, setError] = useState("");

    const navigate = useNavigate();

    const handleUsername = (event) => {
        setUsername(event.target.value);

    }

    const handlePassword = (event) => {
        setPassword(event.target.value);
    }

    const handleEmail = (event) => {
        setEmail(event.target.value);

    }
    useEffect(() => {
        const regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g
        if (username.length > 5 && password.length > 5 && regex.test(email)) {
            setRegisterEnable(true);
        } else {
            setRegisterEnable(false);
        }
    }, [username, password, email])

    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = {
            "username": username,
            "password": password,
            "email": email
        }
        try {
            await axios.post('http://localhost:8082/auth/register', data);
            setError("")
            setEmail("")
            setPassword("")
            setUsername("")
            navigate("/login")
        } catch (error) {
            setError(error.response.data.massege)

        }
    }


    return (
        <>
            <Container>
                <div className="login-box shadow-sm rounded" >
                    <div className="text-center mb-5">
                        <h1>Register User</h1>
                    </div>
                    <Form onSubmit={handleSubmit}>
                        <FloatingLabel controlId="username" label="Select a Username" className="mb-3">
                            <Form.Control placeholder="Select a username" value={username} onChange={handleUsername} />
                        </FloatingLabel>
                        <FloatingLabel controlId="password" label="Select a password" className="mb-3">
                            <Form.Control placeholder="Enter your password" type="password" value={password} onChange={handlePassword} />
                        </FloatingLabel>
                        <FloatingLabel controlId="email" label="Enter your email" className="mb-3">
                            <Form.Control placeholder="Enter your email" type="email" value={email} onChange={handleEmail} />
                        </FloatingLabel>
                        {
                        error &&
                            <div className="text-danger mb-3">
                                {error}
                            </div>
                        }

                        <div className="text-end">
                            <Button variant="primary" type="submit" disabled={!registerEnable}>Register</Button>
                        </div>
                    </Form>
                </div>
            </Container>
        </>
    )
}
export default Register;