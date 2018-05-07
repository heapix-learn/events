import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'
import root from '../../index'

export default {
  state: {
    isLogged: false,
    isLoading: false,
    loggedUser: {},
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
    setUserFromSignIn(state, res) {
      state.loggedUser = {
        firstName: res.firstName,
        id: res.is,
        role: res.role
      }
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
      return axios.post(`/login`, payload)
        .then(res => {
          commit('setIsLogged', true)
          commit('setUserFromSignIn', res)
          root.dispatch('setAuthToken', res.auth.token)
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
      console.log(url)
      return axios.post(`/register`, payload)
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
      root.dispatch('setAuthToken', '')
      router.push({path: '/'})
    },
  }
}