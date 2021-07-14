import {combineReducers, configureStore} from "@reduxjs/toolkit";
import kitchen from './kitchen'
const reducer =  combineReducers({kitchen, /*recipe*/})

export const store = configureStore({reducer})