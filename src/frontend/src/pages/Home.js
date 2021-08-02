import logo from "../images/choppit-logo.svg"
import {useHistory} from "react-router-dom";
import '../Home.css';
import {ChoppitNav} from "../components/ChoppitNav";
import {Formik} from "formik";
import React from "react";
import {Button, Col, Container, Form, InputGroup, Row} from "react-bootstrap";
import {requestSite} from "../store/kitchen";
import * as Yup from "../../node_modules/yup";
import { zustand } from '../store/zustand'

function Home() {
  return (
      <>
        <ChoppitNav/>
        <Container className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <p>Welcome to Choppit Server. There isn't much here yet.</p>
        </Container>
        <Container>
          <Row>
            <Col className='mt-2'>
              {UrlField()}
            </Col>
          </Row>
        </Container>
        <Container className='my-4'>
          <Button block variant='warning' size='lg'>My Cookbook</Button>
        </Container>

        <Container fluid id='about' className='my-4 text-white'>
          <p className="display-4 text-white">About Choppit</p>
          <p className='display-6 text-white'>Cupcake ipsum dolor sit amet jelly
            icing pie. Pudding sugar plum donut bonbon pudding cake oat cake
            marzipan cheesecake. Oat cake chupa chups pudding chocolate bar.
            Gingerbread wafer cupcake cookie caramels pie icing. Danish jelly
            beans jujubes cotton candy pie. Sweet roll gummi bears pudding.
            Lemon drops ice cream tart. Dessert donut cheesecake oat cake
            chocolate bar muffin caramels bear claw gummi bears. Chocolate bar
            cupcake jujubes sugar plum. Carrot cake oat cake tart pudding
            topping sweet cake cheesecake cake. Cupcake fruitcake cotton candy
            chocolate bar tiramisu. Gingerbread carrot cake candy canes dessert.
          </p><p>
          Cake sweet roll cookie sweet roll carrot cake bear claw sweet roll
          jelly-o chocolate cake. Tiramisu powder cotton candy cupcake
          marshmallow candy. Muffin muffin pudding bonbon chocolate bar. Dessert
          icing cotton candy chocolate cake carrot cake gingerbread. Lollipop
          cake sesame snaps. Oat cake chocolate tootsie roll cotton candy. Jelly
          beans tiramisu cheesecake chocolate cake. Marzipan powder apple pie
          gummi bears tart. Sugar plum candy tootsie roll oat cake danish wafer
          jelly gummi bears. Chocolate bar brownie cotton candy chupa chups
          cookie wafer. Pudding marzipan danish powder dragée sweet roll pie.
          Tootsie roll jujubes gingerbread. Caramels cotton candy jelly beans
          pudding fruitcake sugar plum gummies marshmallow sugar plum.</p>
        </Container>
      </>
  );
}

export default Home;

const UrlField = () => {

  const history = useHistory()
  const navigate = () => {
    history.push({pathname: '/select'})
  }
  const validator = Yup.object().shape({
    searchText: Yup.string()
    .url('not a valid url')
    .required("address is required"),

  });
  return (
      <Formik
          initialValues={{url: "https://www.foodnetwork.com/recipes/alton-brown/the-chewy-recipe-1909046"}}
          onSubmit={(values, {setSubmitting, resetForm}) => {
            setSubmitting(true);
            resetForm()
            // zustand(state => state.requestReduction(values.url, navigate))
            zustand.getState().requestReduction(values.url, navigate)
          }}
          // validationSchema={validator}
      >

        {({
          values,
          errors,
          touched,
          handleChange,
          handleBlur,
          handleSubmit,
          isSubmitting
        }) =>
            <Form onSubmit={handleSubmit}>
              <InputGroup>
                <Form.Control
                    type="text"
                    name="url"
                    placeholder="paste a URL to get started"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.url}
                />
                <InputGroup.Append><Button variant="success" type="submit"
                                           disabled={isSubmitting}>Chop a recipe
                </Button>
                </InputGroup.Append>
              </InputGroup>
            </Form>
        }
      </Formik>
  )
}
