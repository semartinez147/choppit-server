import {ChoppitNav} from "../components/ChoppitNav";
import React, {useState} from "react";
import {Button, Col, Container, Form, Row, ToggleButton, ToggleButtonGroup} from "react-bootstrap";
import PickerModal from "../components/PickerModal";
import {useLocation} from "react-router";
import {useDispatch, useSelector} from "react-redux";
import {requestSite} from "../store/kitchen";
import {logDOM} from "@testing-library/react";

function Select() {
    const url = useLocation().state.url
    const dispatch = useDispatch()

    const [radioValue, setRadioValue] = useState(true);

    const changeSelectMethod = (val) => {
        setRadioValue(val)
        // change selection method
    }

    const initialEffects = url => {
        dispatch(requestSite(url))
    }
    React.useEffect(initialEffects, [dispatch])
    const assemblyRecipe = useSelector(state => state.assemblyRecipe)

    return (
        <>
            {() => console.log(assemblyRecipe)}
            <ChoppitNav/>
            <div className="App">
                <Container className="explainer py-2">
                    Choppit extracted the following text from the website.<br/>
                    Click an item to mark it as an ingredient or recipe step.<br/>
                    This helps Choppit understand what parts of the text you want to keep.
                </Container>
                <Container>
                    <Row className='justify-content-end'>
                        <ToggleButtonGroup type="radio" name='selectionMethod' value={radioValue} onChange={changeSelectMethod}>
                            <Button variant='outline-light' onClick={() => {
                                setRadioValue(!radioValue)
                            }}>Text Selection Mode:</Button>
                            <ToggleButton variant='outline-primary' value={true}>Narrow</ToggleButton>
                            <ToggleButton variant='outline-success' value={false}>Broad</ToggleButton>
                        </ToggleButtonGroup></Row>
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
