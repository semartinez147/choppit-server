import {createSlice} from "@reduxjs/toolkit";
import {httpConfig} from "../utils/httpConfig";

const kitchenSlice = createSlice({
  name: "assemblyRecipe",
  initialState: {},
  reducers: {
    sendOrder: (assemblyRecipe, action) => {
      return action.payload
    }

  }
})

export const {sendOrder} = kitchenSlice.actions

export const requestSite = (url, navigate) => async dispatch => {

  await httpConfig.post(
      `/api/kitchen/`,
      {url: url, wantHtml: true})
  .then(response => {
    if (response.status < 300) {
      console.log('store received:', response)
      dispatch(sendOrder(response.data))
      navigate()
    }
  });

}
export default kitchenSlice.reducer