import Vue from 'vue'
import Vuex from 'vuex'

import auth from './modules/auth'
import users from './modules/users'
import news from './modules/news'
import events from './modules/events'
import about from './modules/about'

Vue.use(Vuex)


//export const url = 'http://312e08a6.ngrok.io'
export const url = 'http://18.188.143.172:8080'
// export const url = 'http://localhost:8080'

export default new Vuex.Store({
  state: {
    authToken: ''
  },
  getters: {
    authToken: state => state.authToken
  },
  mutations: {
    setAuthToken(state, token) {
      localStorage.setItem('eventAppToken', token)
      state.authToken = token
    }
  },
  actions: {
    setAuthToken({commit}, payload) {
      commit('setAuthToken', payload)
    }
  },
  modules: {
    auth,
    users,
    news,
    events,
    about
  }
})
