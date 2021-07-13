import {ChoppitNav} from "../components/ChoppitNav";
import React, {useState} from "react";
import {Button, Col, Container, Form, Row, ToggleButton, ToggleButtonGroup} from "react-bootstrap";

function Recipe() {

    console.log('recipe')
    return (
        <>
            <ChoppitNav/>
            <div className='App'>
                <Container fluid >
                    <Row className='mb-2'><Col><span className='display-1 align-self-end'>Recipe Title</span></Col><Col className='d-flex'><span className='display-6 align-self-end text-muted align-bottom'>www.recipeurl.com</span></Col></Row>
                    <Row><Col><span className='display-5'>Recipe Notes: </span></Col></Row><Row><Col><span className='text-muted'>These are the notes for the recipe they probably won't be super long or complicated.</span></Col></Row>
                    <Row>
                        <Col md={3} id='rec-ingredients'>
                            Ingredients
                            <ul>
                                <li>Ingredit 1</li>
                                <li>Ingrededt 2</li>
                                <li>Grinediet 3</li>
                                <li>Nigrnidet 4</li>
                                <li>Ingredietn 5</li>
                                <li>Ingredient 6</li>
                            </ul>
                        </Col>
                        <Col md={6}>
                            Instructions
                            <ul>
                                <li>Step the first: Do the first step</li>
                                <li>Step the second: Do the second step</li>
                                <li>Step the third: Do the third step</li>
                                <li>Step the fourth: Do the fourth step</li>
                            </ul>
                        </Col>
                    </Row>
                </Container></div>
        </>
    )
}

export default Recipe