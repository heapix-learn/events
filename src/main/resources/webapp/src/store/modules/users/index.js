import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'

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
      currentUser: state => state.currentUser,
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
        return axios.get(`http://7d159034.ngrok.io/users/registered`, {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
          }
        })
          .then(res => {
            commit('setUsers', res.data)
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },

      getUserById({commit}, id) {
        return axios.get(`http://7d159034.ngrok.io/users/${id}`, {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
          }
        })
          .then(res => {
            commit('setCurrentUser', res.data)
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },
      
      getPendingUsers({commit}) {
        return axios.get(`http://7d159034.ngrok.io/users/unregistered`, {
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
          }
        })
          .then(res => {
            commit('setPendingUsers', res.data)
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
      },

      postEdittedUser({commit}, payload) {
        commit('setPostEdittedUserError', '')
        return axios.post(`http://7d159034.ngrok.io/edituser`, payload)
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
        return axios.post(`http://7d159034.ngrok.io/changePassword`, payload)
          .then(res => {
            router.go(-1)
            return res
          })
          .catch(rej => {
            commit('changePasswordServerError', rej)
          })
      },

      unsubscribeUser({commit, state}, payload) {
        return axios.post(`http://7d159034.ngrok.io/unsubscribe?id:${payload}`)
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
        return axios.post(`http://7d159034.ngrok.io/createnewuser`, payload)
          .then(res => {
            router.push({path: '/users'})
            return res
          })
          .catch(rej => {
            commit('setPostCreateNewUserError', rej)
            console.dir(rej)
            return rej
          })
      },

      registerUser({commit}, id) {
        axios({method: 'PUT', url: `http://7d159034.ngrok.io/register/${id}`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: {}})
          .then(res => {
            router.push({path: '/pendingusers'})
          })
          .catch(rej => {
            console.dir(rej)
          })
      },
      clearCurrentUser({commit}) {
        commit('setCurrentUser', {})
      }
    }
}