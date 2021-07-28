import { configureStore, combineReducers} from "@reduxjs/toolkit";
import kitchen from "./kitchen";

const reducer = combineReducers({kitchen})

export default configureStore({reducer})