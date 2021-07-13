import create from 'zustand'
import {httpConfig} from "../utils/httpConfig";

const useStore = create(set => ({
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
  setAssemblyRecipe: (newAssemblyRecipe) => set(state => ({assemblyRecipe: newAssemblyRecipe})),

  requestReduction: async (url) =>  {
    const response = await httpConfig({
      method: 'post',
      url: '/api/kitchen/',
      data: {url: url}
    })

    set({assemblyRecipe: response.data})
  },

  requestSimmer: async (assemblyRecipe) => {
    const response = await httpConfig({
      method: 'put',
      url: '/api/kitchen/',
      data: assemblyRecipe
    })

    set({assemblyRecipe: response.data})
  }
}))