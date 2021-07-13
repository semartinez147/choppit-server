import React from "react"

import logo from "../images/choppit-logo.svg"
import {Nav, Navbar} from "react-bootstrap";


export const ChoppitNav = () => {
    return (
        <>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home">
                    <img
                        alt=""
                        src={logo}
                        width="30"
                        height="30"
                        className="d-inline-block align-middle"
                    />{' '}
                    Choppit
                </Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="/home">Home</Nav.Link>
                    <Nav.Link href="/select">Selection</Nav.Link>
                    <Nav.Link href="/recipe">Recipe</Nav.Link>
                    <Nav.Link href="/edit">Edit</Nav.Link>
                    <Nav.Link href="/recipe" >Recipe</Nav.Link>
                    <Nav.Link href="/cookbook">Cookbook</Nav.Link>
                </Nav>
            </Navbar>
        </>
    )
}