import router from '../../../router'
import axios from 'axios'
import url from '../../index'

export default {
  state: {
    isLogged: false,
    isLoading: false,
    loggedUser: {},
    authToken: '',
    postSignUpError: '',
    postSignInError: '',
  },
  getters: {
    isLogged: state => state.isLogged,
    isLoading: state => state.isLoading,
    loggedUser: state => state.loggedUser,
    loggedUserId: state => state.loggedUser.id,
    loggedUserName: state => state.loggedUser.firstName,
    loggedUserRole: state => state.loggedUser.role,

    postSignInError: state => state.postSignInError,
    postSignUpError: state => state.postSignUpError,
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
    setPostSignInError(state, errorMessage) {
      state.postSignInError = errorMessage
    },
    setPostSignUpError(state, errorMessage) {
      state.postSignUpError = errorMessage
    },
    signOut(state) {
      state.isLogged = false;
      state.loggedUser = {};
      state.authToken = '';
    }
  },
  actions: {

    postSignIn({commit, state}, payload) {
      commit('toggleLoading')
      commit('setPostSignInError', '')
      return axios.post(`${url}/login`, payload)
        .then(res => {
          commit('setIsLogged', true)
          commit('setUser', res.user)
          commit('setAuthToken', res.token)
          router.push({path: '/'})
          return res
        })
        .catch(rej => {
          commit('setPostSignInError', 'Wrong credentials')
          return rej
        })
        .then(res => {
          commit('toggleLoading')
          return res
        })
    },

    postSignUp({commit}, payload) {
      commit('toggleLoading')
      commit('setPostSignUpError', '')
      return axios.post(`${url}/signup`, payload)
        .then(res => {
          router.push({path: '/'})
          return res
        })
        .catch(rej => {
          commit('setPostSignUpError', 'Failed to sign up')
          console.dir(rej)
          return rej
        })
        .then(res => {
          commit('toggleLoading')
          return res
        })
    },

    signOut({commit}) {
      commit('signOut')
      router.push({path: '/'})
    },
  }
}