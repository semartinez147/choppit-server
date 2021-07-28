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
import {useSelector} from "react-redux";


function Select() {

  const [radioValue, setRadioValue] = useState(1);

  const changeSelectMethod = (val) => {

    setRadioValue(val)
    // change selection method
  }
  const initialEffects = async () => {
  }

  React.useEffect(initialEffects)
  const assemblyRecipe = useSelector(
      state => state.kitchen ? state.kitchen : null)

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
              {radioValue === 1? broad.map(el => <PickerModal text={el.innerText}/>) : radioValue === 2? narrow.map(el => <PickerModal text={el.innerText}/>) : smart.map(el => <PickerModal text={el.innerText}/>)}

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
