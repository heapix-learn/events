import Vue from 'vue'
import Vuex from 'vuex'

import auth from './modules/auth'
import users from './modules/users'
import news from './modules/news'
import events from './modules/events'

Vue.use(Vuex)
export const url = 'http://localhost:8080'
export default new Vuex.Store({
  state: {
    
  },
  mutations: {

  },
  actions: {

  },
  modules: {
    auth,
    users,
    news,
    events
  }
})
