import {ChoppitNav} from "../components/ChoppitNav";
import React, {useState} from "react";
import {Button, Col, Container, Form, InputGroup, Row, ToggleButton, ToggleButtonGroup} from "react-bootstrap";

function Recipe() {

    console.log('recipe')
    return (
        <>
            <ChoppitNav/>
            <div className='App'>
                <Container fluid >
                    <Row className='mb-2'><Col><Form.Control aria-label='recipe title' size='lg' placeholder='Recipe Title'/></Col><Col className='d-flex'><span className='display-6 align-self-end text-muted align-bottom'>www.recipeurl.com</span></Col></Row>
                    <Row>
                        <Col md={3} id='rec-ingredients'>
                            <Form.Group controlId='recipe.Ingredients'><Form.Label>Ingredients</Form.Label>
                                <ul>
                                    <li><InputGroup><Form.Control placeholder='Ingredit 1'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Ingrededt 2'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Grinediet 3'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Nigrnidet 4'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Ingredietn 5'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Ingredient 6'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                </ul>
                                <Button size='sm'>Add Ingredient</Button>
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId='recipe.Instructions'><Form.Label>Instructions</Form.Label>
                                <ol>
                                    <li><InputGroup><Form.Control placeholder='Step the first: Do the first step'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Step the second: Do the second step'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Step the third: Do the third step'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                    <li><InputGroup><Form.Control placeholder='Step the fourth: Do the fourth step'/><InputGroup.Append><Button variant='secondary'>X</Button></InputGroup.Append></InputGroup></li>
                                </ol>
                                <Button size='sm'>Add Step</Button>

                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col><Form.Group controlId='recipe.RecipeNotes'>
                            <Form.Label>Notes</Form.Label>
                            <Form.Control block as='textarea' rows={3}/>
                        </Form.Group></Col>
                    </Row>
                </Container></div>
        </>
    )
}

export default Recipe