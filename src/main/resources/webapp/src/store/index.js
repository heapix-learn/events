import Vue from 'vue'
import Vuex from 'vuex'

import auth from './modules/auth'
import users from './modules/users'
import news from './modules/news'
import events from './modules/events'

Vue.use(Vuex)
export const url = 'http://13.59.162.117:8080'
export default new Vuex.Store({
  state: {
    authToken: ''
  },
  getters: {
    authToken: state => state.authToken
  },
  mutations: {
    setAuthToken(state, token) {
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
    events
  }
})
