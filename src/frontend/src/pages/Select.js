import {ChoppitNav} from "../components/ChoppitNav";
import React, {useState} from "react";
import {
  Button,
  Col,
  Container,
  Form,
  Row,
  ToggleButton,
  ToggleButtonGroup
} from "react-bootstrap";
import PickerModal from "../components/PickerModal";
import {Formik} from "formik";
import {zustand} from '../store/zustand'

function Select() {

  const [radioValue, setRadioValue] = useState(1);

  const [ingredient, setIngredient] = useState('')
  const [instruction, setInstruction] = useState('')


  const changeSelectMethod = (val) => {
    setRadioValue(val)
  }
  const assemblyRecipe = zustand(state => state.assemblyRecipe)

  const menu = document.createRange().createContextualFragment(assemblyRecipe.reduction.toString())
  let broad = []
  let narrow = []
  let smart = []
  for (let i = 0; i < menu.children.length; i++) {
    let element = menu.children[i]
    broad.push(element)
    if (element.outerHTML.substring(0,3) === '<li') {
      narrow.push(element)
    }
    if (element.outerHTML.match(/ingredient/i) || element.outerHTML.match(/step/i) || element.outerHTML.match(/instruction/i)) {
      smart.push(element)
    }
  }
  return (
      <>
        <ChoppitNav/>
        <div className="App">
          <Container className="explainer py-2">
            Choppit extracted the following text from the website.<br/>
            Click an item to mark it as an ingredient or recipe step.<br/>
            This helps Choppit understand what parts of the text you want to
            keep.
          </Container>
          <Container>
            <Row className='justify-content-end'>
              <ToggleButtonGroup type="radio" name='selectionMethod'
                                 value={radioValue}
                                 onChange={changeSelectMethod}>
                <Button variant='outline-light'
                        onClick={() => {setRadioValue(radioValue<3? radioValue+1 : 1)}}
                >Text Selection Mode:</Button>
                <ToggleButton variant='outline-success'
                              value={1}>Broad</ToggleButton>
                <ToggleButton variant='outline-primary'
                  value={2}>Narrow</ToggleButton>
                <ToggleButton variant='outline-warning'
                  value={3}>Smart</ToggleButton>
              </ToggleButtonGroup></Row>
          </Container>
          <Container
              className="text-selection border border-2 border-light rounded my-2"
              style={{minHeight: "20vh"}}>
            <ul>
              {radioValue === 1? broad.map(el => <PickerModal key={el.innerText} text={el.innerText} setInstruction={setInstruction} setIngredient={setIngredient}/>) : radioValue === 2? narrow.map(el => <PickerModal text={el.innerText}/>) : smart.map(el => <PickerModal text={el.innerText}/>)}

            </ul>
          </Container>
          <Container>
            <SelectForm ingredient={ingredient} instruction={instruction}/>
          </Container>
        </div>
      </>
  )
}

function SelectForm(props) {
  const selectForm = {ingredient: props.ingredient, instruction: props.instruction}
  const submitSelect = () => {}
  return (
      <>
      <Formik
      initialValues={selectForm}
      onSubmit={submitSelect}>
        {SelectFormContent}
      </Formik>
      </>
  )
}

const SelectFormContent = (props) => {
  return (
      <Form id='selectForm'>
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
  )
}

export default Select;
