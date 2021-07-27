import {BrowserRouter} from 'react-router-dom'
import {Route, Switch} from 'react-router'
import React from 'react'
import Home from "./pages/Home";
import Select from "./pages/Select";
import Recipe from "./pages/Recipe";
import Edit from "./pages/Edit";
import Cookbook from "./pages/Cookbook";
import {Provider} from "react-redux";

export const App = (store) => (
    <>
      <Provider store={store}>
      <BrowserRouter>
        <Switch>
          <Route exact path={['/', '/home']} component={Home}/>
          <Route exact path="/select" component={Select}/>
          <Route exact path="/recipe" component={Recipe}/>
          <Route exact path="/edit" component={Edit}/>
          <Route exact path="/cookbook" component={Cookbook}/>
        </Switch>
      </BrowserRouter>
    </Provider>
    </>
)