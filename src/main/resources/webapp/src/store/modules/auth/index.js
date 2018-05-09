import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'
import root from '../../index'

export default {
  state: {
    isLogged: localStorage.getItem('eventAppToken') != null,
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
    setRole(state, role) {
      state.role = role
    },
    setUserFromSignIn(state, res) {
      state.loggedUser = {
        firstName: res.firstName,
        id: res.id,
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
      return axios.post(`http://localhost:8080/auth`, payload)
        .then(res => {

          commit('setIsLogged', true)
          commit('setUserFromSignIn', res.data)
          root.dispatch('setAuthToken', res.data.auth.token)
          router.push('/')
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
      return axios.post(`{$url}/register`, payload)
        .then(res => {
          router.push('/')
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
      router.push('/')
    },

    getLoggedUser({commit, state}) {
      axios.get(`${url}/users/me`,
          {
              headers: {
                  Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
              }
          })
        .then(res => {
          console.log('set current user to ' + res.data.email);
          commit('setUser', res.data);
        })
        .catch(rej => {
          console.dir(rej)
        })
    }
  }
}