import React, {useContext, useState} from 'react';
import {Button, Card, Container, Form, Row} from "react-bootstrap";
import {LOGIN_ROUTE, MAIN_ROUTE, REG_ROUTE} from "../util/Const";
import {NavLink, useHistory, useLocation} from "react-router-dom";
import {loginMethod, registration} from "../http/UserApi";
import {Context} from "../index";
import {observer} from "mobx-react-lite";

const Auth = observer(() => {
    const {user} = useContext(Context)

    const location = useLocation()
    const history = useHistory()
    const isLogin = location.pathname === LOGIN_ROUTE
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')

    const click = async () => {
        try {
            let data = {}
            if (isLogin) {
                data = await loginMethod(username, password);
            } else {
                data = await registration(email, username, password);
            }
            console.log(data)
            user.setUser(data)
            user.setIsAuth(true)
            history.push(MAIN_ROUTE)
        } catch (e) {
            alert(e.response.data.message)
        }

    }

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: 700}} className="p-5 my-5">
                <h2 className="m-auto">{isLogin ? 'Авторизация' : "Регистрация"}</h2>
                <Form className="d-flex flex-column">
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш login..."
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                    {!isLogin?
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш email..."
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />
                        :
                        <>
                        </>
                    }
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш пароль..."
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        type="password"
                    />
                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        {isLogin ?
                            <div>
                                Нет аккаунта? <NavLink to={REG_ROUTE}>Зарегистрируйся!</NavLink>
                            </div>
                            :
                            <div>
                                Есть аккаунт? <NavLink to={LOGIN_ROUTE}>Войдите!</NavLink>
                            </div>
                        }
                        <Button
                            variant={"outline-success"}
                            onClick={click}
                            className="mt-3"
                        >
                            {isLogin ? 'Войти' : 'Регистрация'}
                        </Button>
                    </Row>

                </Form>
            </Card>
        </Container>
    );
});

export default Auth;