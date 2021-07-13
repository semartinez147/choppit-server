import {createSlice} from "@reduxjs/toolkit";
import {httpConfig} from "../utils/httpConfig";

const kitchenSlice = createSlice({
  name: "assemblyRecipe",
  initialState: {},
  reducers: {

  }
})

export const {} = kitchenSlice.actions

export const requestSite = () => async dispatch => {
  const {data} = await httpConfig.get(`/api/kitchen`)
  dispatch()
}