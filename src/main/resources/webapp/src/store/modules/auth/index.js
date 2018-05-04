import router from '../../../router'
import axios from 'axios'
import url from '../../index'

const user = {
  firstName: 'uucc',
  lastName: 'uucc',
  firstPhone: '+111111111',
  lastPhone: '',
  email: 'uucc@uucc.uucc',
  role: 'member',
}

export default {
  state: {
    isLogged: false,
    isLoading: false,
    loggedUser: {},
    authToken: '',
  },
  getters: {
    isLogged: state => state.isLogged,
    isLoading: state => state.isLoading,
    loggedUserId: state => state.loggedUser.id,
    loggedUserName: state => state.loggedUser.firstName,
    loggedUserRole: state => state.loggedUser.role,
  },
  mutations: {
    setIsLogged(state, isLogged) {
      state.isLogged = isLogged
    },
    setUser(state, user) {
      state.loggedUser = user
    },
    setAuthToken(state, token) {
      state.authToken = token
    },
    toggleLoading(state) {
      state.isLoading = !state.isLoading;
    },
  },
  actions: {
    signIn({commit}, payload) {
      //FOR TESTING PURPOSE ONLY
      //DO NOT REMOVE THIS BLOCK 
      // if (payload.email === 'uucc@uucc.uucc' ) {
      //   commit('setIsLogged', true)
      //   commit('setUser', user)
      //   commit('setAuthToken', '12345')
      //   router.push({path: '/'})
      //   return
      // }
       
     
      //FOR TESTING PURPOSE ONLY
        commit('toggleLoading')
        return axios.post(`${url}/photos`, payload)
          .then(res => {
            commit('setIsLogged', true)
            commit('setUser', res.user)
            commit('setAuthToken', res.token)
            router.push({path: '/'})
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
          .then(res => {
            commit('toggleLoading')
            return res
          })
    },
    signOut({commit}, payload) {
        commit('toggleLoading')
        return axios.post(`${url}/photos`, payload)
          .then(res => {
            commit('setIsLogged', false)
            router.push({path: '/'})
            return res
          })
          .catch(rej => {
            commit('logoutError', rej)
            return rej
          })
          .then(res => {
            commit('toggleLoading')
            return res
          })
    },
    signUp({commit}, payload) {
        commit('toggleLoading')
        return axios.post(`${url}/register`, payload)
          .then(res => {
            router.push({path: '/'})
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
          .then(res => {
            commit('toggleLoading')
            return res
          })
    },
  }
}