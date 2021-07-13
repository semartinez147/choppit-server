import React from 'react';
import {ChoppitNav} from "../components/ChoppitNav";
import {Col, Container, Row} from "react-bootstrap";

function Cookbook() {

    return(
        <>
            <ChoppitNav/>
            <div className='App'>
                <Container fluid >
                    <Row className='mb-2'><Col><span className='display-1 align-self-end'>Your Cookbook</span></Col></Row>
                    <Row>Probably a search bar and filters</Row>
                </Container>
            </div>
        </>
    )
}

export default Cookbook