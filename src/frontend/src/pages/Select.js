import {ChoppitNav} from "../components/ChoppitNav";
import React, {useState} from "react";
import {useStore} from '../store/zustand'
import {Button, Col, Container, Form, Row, ToggleButton, ToggleButtonGroup} from "react-bootstrap";
import PickerModal from "../components/PickerModal";
import {useLocation} from "react-router";


const selector = state => state.assemblyRecipe
function Select() {
    const url = useLocation().state.url
    const assemblyRecipe = useStore(selector)

    //FIXME requestReduction() is getting called repeatedly despite getRecipe not being used anywhere.
    const getRecipe = useStore(state => {
        console.log('requestReduction from Select')
        state.requestReduction(url);
    })
    console.log('assemblyRecipe:', assemblyRecipe)
    return (
        <>
            <ChoppitNav/>
            <div className="App">
                <Container className="explainer py-2">
                    Choppit extracted the following text from the website.<br/>
                    Click an item to mark it as an ingredient or recipe step.<br/>
                    This helps Choppit understand what parts of the text you want to keep.
                </Container>

                <Container className="text-selection border border-2 border-light rounded my-2" style={{minHeight: "20vh"}}>
                    <ul>
                        <PickerModal text='This is where'/>
                        <PickerModal text='the text will'/>
                        <li>be</li>
                        <li>when there is text to be</li>
                    </ul>
                </Container>
                <Container>
                    <Form>
                        <Row>
                            <Col className='mt-2'>
                                <Form.Group>
                                    <Form.Control type="text" readOnly/>
                                    <Form.Label>Ingredient Text</Form.Label>
                                </Form.Group>
                            </Col>
                            <Col className='mt-2'>
                                <Form.Group>
                                    <Form.Control type="text" readOnly/>
                                    <Form.Label>Instruction Text</Form.Label>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row><Col><Button block>Do Go More</Button></Col></Row>
                    </Form>
                </Container>
            </div>
        </>
    )
}

export default Select;
