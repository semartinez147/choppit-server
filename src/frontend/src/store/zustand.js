import create from 'zustand'
import {httpConfig} from "../utils/httpConfig";

export const zustand = create(set => ({
  recipe: {
    recipeId: 0,
    title: '',
    url: '',
    favorite: false,
    steps: [],
    ingredients: [],
  },
  assemblyRecipe: {
    title: '',
    url: '',
    favorite: false,
    reduction: [],
    recipeId: 0,
    steps: [],
    ingredients: [],
    sampleStep: '',
    sampleIngredient: ''
  },
  setRecipe: (newRecipe) => set(state => ({recipe: newRecipe})),
  setAssemblyRecipe: (newAssemblyRecipe) => set(
      state => ({assemblyRecipe: newAssemblyRecipe})),

  requestReduction: async (url, navigate) => {
    console.log("requestReduction")
    const response = await httpConfig({
      method: 'post',
      url: '/api/kitchen/',
      data: {url: url, wantHtml: true}
    })

    console.log('response', response)
    if (response.status === 200) {
      console.log("setting recipe")
      set({assemblyRecipe: response.data})
      navigate()
    }

  },
  //
  // requestReduction: async (url) => {
  //   console.log("requestReduction")
  //   await httpConfig({
  //     method: 'post',
  //     url: '/api/kitchen/',
  //     data: {url: url, wantHtml: true}
  //   }).then(response => {
  //     console.log('response', response)
  //         if (response.status === 200) {
  //           console.log("setting recipe")
  //           set({assemblyRecipe: response.data})
  //         }
  //       }
  //   )
  // },

  requestSimmer: async (assemblyRecipe) => {
    const response = await httpConfig({
      method: 'put',
      url: '/api/kitchen/',
      data: assemblyRecipe
    })

    set({assemblyRecipe: response.data})
  }
}))