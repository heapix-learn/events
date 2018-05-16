import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'
import root from '../../index'

const ROLES = {
  'Super Administrator': 1,
  'Administrator': 2,
  'Moderator': 3,
  'Member': 4,
  'Anonymous': 5
}

export default {
  state: {
    isLogged: localStorage.getItem('eventAppToken') != null,
    isLoading: false,
    loggedUser: {role: 5},
    postSignUpError: '',
    postSignInError: '',
    publicEndSignUpForm: [],
    endSignUpForm: [],
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

    endSignUpForm: state => state.endSignUpForm,
    publicEndSignUpForm: state => state.publicEndSignUpForm,
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
      state.loggedUser = {role: 5};
      state.authToken = '';
    },
    setEndSignUpForm(state, form) {
      state.endSignUpForm = form
    },
    setPublicEndSignUpForm(state, form) {
      state.publicEndSignUpForm = form
    }
  },
  actions: {

    postSignIn({commit, state}, payload) {
      commit('toggleLoading')
      commit('setPostSignInError', '')
      axios.post(`${url}/auth`, payload)
        .then(res => {
          console.log(res)
          console.log(res.data)
          if (res.data === 'Сonfirmation of your account in progress') {
            console.log('adasdad')
            commit('setPostSignInError', 'Сonfirmation of your account in progress')
            return
          }
          if (!res.data.infoProvided) {
            root.dispatch('setAuthToken', res.data.auth.token)
            router.push('/auth/endsignup')
            return
          }
          const user = res.data;
          const userRoleId = ROLES[user.role]
          user.role = userRoleId
          commit('setIsLogged', true)
          commit('setUserFromSignIn', user)
          root.dispatch('setAuthToken', res.data.auth.token)
          router.push('/')
          return res
        })
        .catch(rej => {
          console.log('im in catch')
          console.log(rej)
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
      return axios.post(`${url}/register`, payload)
        .then(res => {
          router.push('/')
          return res
        })
        .catch(rej => {
          if (rej.response) {
            commit('setPostSignUpError', rej.response.data.message)
          } else {
            commit('setPostSignUpError', 'Failed to sign up')
          }
          console.dir(rej)
          return rej
        })
        .then(res => {
          commit('toggleLoading')
          return res
        })
    },

    postEndSignUp({commit}, payload) {
      const info = []
      for (let i in payload) {
        info.push({[i]: payload[i]})
      }
      axios({method: 'PUT', url: `${url}/reg-form`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: {inputs: JSON.stringify(info)}})
        .then(res => {
          router.push('/')
          return res
        })
        .catch(rej => {
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
      localStorage.removeItem("eventAppToken");
      router.push('/')
      location.reload();
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
          commit('setIsLogged', false)
          console.dir(rej)
        })
    },
    postPublicEndSignUpForm({commit}, form) {
      axios({method: 'POST', url: `${url}/reg-form`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: {inputs: JSON.stringify(form)}})
        .then(res => {
          router.push('/users')
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    getPublicEndSignUpForm({commit, state}) {
      axios({method: 'GET', url: `${url}/reg-form`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}})
        .then(res => {
          commit('setPublicEndSignUpForm', JSON.parse(res.data.inputs))
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    clearSignInErrorMessage({commit}) {
      commit('setPostSignInError', '')
    },
    setEndSignUpForm({commit}, form) {
      commit('setEndSignUpForm', form)
    },
    setPublicEndSignUpForm({commit}, form) {
      commit('setPublicEndSignUpForm', form)
    }
  }
}