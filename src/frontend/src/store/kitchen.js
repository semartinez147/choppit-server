import {createSlice} from "@reduxjs/toolkit";
import {httpConfig} from "../utils/httpConfig";

const kitchenSlice = createSlice({
  name: "assemblyRecipe",
  initialState: {},
  reducers: {
    getSite: (assemblyRecipe, action) => {
      return action.payload
    }

  }
})

export const {getSite} = kitchenSlice.actions

export const requestSite = (url) => async dispatch => {
  const {data} = await httpConfig({
      url:`/api/kitchen/`,
      data: {url: url}
})
  dispatch(getSite(data))
}
export default kitchenSlice.reducer