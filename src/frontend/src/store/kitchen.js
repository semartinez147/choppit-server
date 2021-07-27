import {createSlice} from "@reduxjs/toolkit";
import {httpConfig} from "../utils/httpConfig";

const kitchenSlice = createSlice({
  name: "assemblyRecipe",
  initialState: null,
  reducers: {
    getReduction: (assemblyRecipe, action) => {
      return action.payload
    }

  }
})

export const {getReduction} = kitchenSlice.actions

export const requestSite = (url) => async dispatch => {
  const {data} = await httpConfig({
    method: 'post',
    url: `/api/kitchen/`,
    data: {url: url, wantHtml: true}
  })
  dispatch(getReduction(data))
}
export default kitchenSlice.reducer