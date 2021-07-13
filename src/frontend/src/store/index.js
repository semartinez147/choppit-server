import {combineReducers, configureStore} from "@reduxjs/toolkit";

const reducer =  combineReducers({kitchen, recipe})
//^^recipe,
export const store = configureStore({reducer})