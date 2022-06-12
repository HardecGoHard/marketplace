import React, {useContext, useState} from 'react';
import {NavLink, useHistory} from "react-router-dom";
import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faDragon} from "@fortawesome/free-solid-svg-icons";
import {Context} from "../index";
import {observer} from "mobx-react-lite";
import {ITEM_ROUTE, LOGIN_ROUTE, LOGOUT_ROUTE, MAIN_ROUTE, PROFILE_ROUTE, REG_ROUTE} from "../util/Const";

const NavBar = observer(() => {
        const {user} = useContext(Context)
        const history = useHistory()

        const logOut = () => {
            user.setUser({})
            user.setIsAuth(false)
        }
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                    <NavLink className="navbar-brand" to={MAIN_ROUTE}>
                        <FontAwesomeIcon icon={faDragon} className="mx-1"/> Marketplace
                    </NavLink>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                    {user.isAuth ?
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                                <NavDropdown title="Items" id="basic-nav-dropdown">
                                    <NavLink className="dropdown-item" to={ITEM_ROUTE}>All items</NavLink>
                                </NavDropdown>
                            </Nav>
                            <Nav>

                                <NavLink className="nav-link" to={PROFILE_ROUTE}>Профиль</NavLink>
                                <NavLink className="nav-link" to={LOGOUT_ROUTE} onClick={() => logOut()}>Выйти</NavLink>
                            </Nav>

                        </Navbar.Collapse>
                        :
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                            </Nav>
                            <Nav>
                                <NavLink className="nav-link" to={LOGIN_ROUTE}>Войти</NavLink>
                                <NavLink className="nav-link" to={REG_ROUTE}>Регистрация</NavLink>
                            </Nav>
                        </Navbar.Collapse>
                    }
                </Container>
            </Navbar>

        );
    }
);

export default NavBar;