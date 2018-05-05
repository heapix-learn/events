import router from '../../../router'
import axios from 'axios'
import url from '../../index'

export default {
    state: {
      users: [],
      pendingUsers: [],
      currentUser: {},
      changePasswordError: '',
      postCreateNewUserError: '',
      postEdittedUserError: '',
      changePasswordServerError: '',
    },
    getters: {
      users: state => state.users,
      pendingUsers: state => state.pendingUsers,
      userById: state => id => state.currentUser,
      pendingUserById: state => id => state.pendingUsers.find(user => user.id === id),
      postCreateNewUserError: state => state.postCreateNewUserError,
      postEdittedUserError: state => state.postEdittedUserError,
    },
    mutations: {
      setUsers(state, users) {
        state.users = users
      },
      setPendingUsers(state, users) {
        state.pendingUsers = users
      },
      setCurrentUser(state, user) {
        state.currentUser = user
      },
      setPostCreateNewUserError(state, error) {
        state.postCreateNewUserError = error
      },
      setPostEdittedUserError(state, error) {
        state.postEdittedUserError = error
      },
      setChangePasswordServerError(state, error) {
        state.changePasswordServerError = error
      }
    },
    actions: {
      getAllUsers({commit}) {
        return axios.get(`${url}/users`)
          .then(res => {
            commit('setUsers', res)
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },

      getUserById({commit}, payload) {
        return axios.get(`${url}/user?id:${payload}`)
          .then(res => {
            commit('setCurrentUser', res)
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },
      
      getPendingUsers({commit}) {
        return axios.get(`${url}/pendingusers`)
          .then(res => {
            commit('setPendingUsers', res)
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
      },

      postEdittedUser({commit}, payload) {
        commit('setPostEdittedUserError', '')
        return axios.post(`${url}/edituser`, payload)
          .then(res => {
            router.push({path: '/users/' + payload.id})
            return res
          })
          .catch(rej => {
            commit('setPostEdittedUserError', rej)
            return rej
          })
      },

      changePassword({commit}, payload) {
        commit('changePasswordServerError', '')
        return axios.post(`${url}/changePassword`, payload)
          .then(res => {
            router.go(-1)
            return res
          })
          .catch(rej => {
            commit('changePasswordServerError', rej)
          })
      },

      unsubscribeUser({commit, state}, payload) {
        return axios.post(`${url}/unsubscribe?id:${payload}`)
          .then(res => {
            router.push({path: '/'})
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },

      postCreateNewUser({commit}, payload) {
        commit('setPostCreateNewUserError', '')
        return axios.post(`${url}/createnewuser`, payload)
          .then(res => {
            router.push({path: '/users'})
            return res
          })
          .catch(rej => {
            commit('setPostCreateNewUserError', rej)
            console.dir(rej)
            return rej
          })
      }
    }
}