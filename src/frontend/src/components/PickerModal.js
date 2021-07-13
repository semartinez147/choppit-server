import {Button, Form, Modal, Col, Row} from "react-bootstrap";
import {useState} from "react";
import {Formik} from "formik";


function PickerModal(props) {
    const text = props.text
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);

    const handleShow = () => setShow(true);


    return(<>
        <li className='rounded-pill'  onClick={handleShow}>
            {text}
        </li>

        <Modal show={show} onHide={handleClose}>
            <Modal.Header className='bg-dark text-white'  closeButton>
                <Modal.Title className='bg-dark text-white'>Select Text</Modal.Title>
            </Modal.Header>
            <Modal.Body className='bg-dark text-white'>
                <PickerForm/>
            </Modal.Body>
        </Modal>
    </>)
}

export default PickerModal


function PickerForm() {

    const pickerForm = {inputText: ''}
    const submitPicker = () => {}
    return (
        <>
            <Formik
                initialValues={pickerForm}
                onSubmit={submitPicker}
            >
                {PickerFormContent}
            </Formik>
        </>
    )
}

const PickerFormContent = (props) => {
    const {
        status,
        values,
        errors,
        touched,
        dirty,
        isSubmitting,
        handleChange,
        handleBlur,
        handleSubmit,
        handleReset
    } = props;

    return (
        <>
        <Form id='textPicker' className='text-center'>
            <Row><Col>Is this text from an ingredient, or instructions?</Col></Row>
            <Row><Col><Form.Control plaintext readOnly/></Col></Row>
            <Row><Col><Button>Ingredient</Button></Col><Col><Button>Instruction</Button></Col></Row>
        </Form>
        </>
    )
}