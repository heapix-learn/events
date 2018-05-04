import router from '../../../router'
import axios from 'axios'

const url = 'https://jsonplaceholder.typicode.com'

export default {
  state: {
    isLogged: false,
    isLoading: false,
    loggedUser: {},
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
    toggleLoading(state) {
      state.isLoading = !state.isLoading;
    },
  },
  actions: {
    signIn({commit}, payload) {
        commit('toggleLoading')
        return axios.post(`${url}/photos`, payload)
          .then(res => {
            commit('setIsLogged', true)
            commit('setUser', res.user)
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
        return axios.post(`${url}/photos`, payload)
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