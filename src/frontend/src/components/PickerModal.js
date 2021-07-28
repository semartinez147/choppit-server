import {Button, Form, Modal, Col, Row} from "react-bootstrap";
import {useState} from "react";
import {Formik} from "formik";


function PickerModal(props) {
    let text = props.text
    if (text.length >= 50) {
        text = text.substring(0,50) + ' ...'
    }
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
                <PickerForm text={props.text}/>
            </Modal.Body>
        </Modal>
    </>)
}

export default PickerModal


function PickerForm(props) {
    const pickerForm = {inputText: props.text}
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
            <Row><Col><Form.Control as='textarea' plaintext disabled id='inputText' name='inputText' style={{height: 'max-content', resize: 'none'}} className='form-control-lg lead text-white text-center my-4' value={values.inputText}/></Col></Row>
            <Row><Col><Button>Ingredient</Button></Col><Col><Button>Instruction</Button></Col></Row>
        </Form>
        </>
    )
}