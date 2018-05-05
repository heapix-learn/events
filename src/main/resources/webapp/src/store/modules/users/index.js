import router from '../../../router'
import axios from 'axios'
import url from '../../index'

export default {
    state: {
      users: [],
      pendingUsers: [],
      currentUser: {},
      changePasswordError: '',
    },
    getters: {
      users: state => state.users,
      pendingUsers: state => state.pendingUsers,
      userById: state => id => state.currentUser,
      pendingUserById: state => id => state.pendingUsers.find(user => user.id === id)
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
      }
    },
    actions: {
      getUsers({commit}) {
        return axios.get(`${url}/photos`)
          .then(res => {
            commit('setUsers', res.users)
            router.push({path: '/users'})
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
      },
      getUserById({commit}, payload) {
        return axios.get(`${url}/photos`)
          .then(res => {
            commit('setCurrentUser', res.user)
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
      },
      getPendingUsers({commit}) {
        return axios.get(`${url}/photos`)
          .then(res => {
            commit('setPendingUsers', res.users)
            router.push({path: '/pendingusers'})
            return res
          })
          .catch(rej => {
            console.log(rej)
            return rej
          })
      },
      changePassword() {
        return axios.post(`${url}/changePassword`)
          .then(res => {
            router.push({path: '/myprofile'})
            return res
          })
      },
      createUser({commit}, payload) {
        return axios.post(`${url}/newUser`, payload)
      },
      sendNewUserData({commit}, payload) {
        return axios.post(`${url}/editUser`, payload)
      },
      unsubscribeUser({commit, state}, payload) {
        return axios.post(`${url}/unsubscribe?id:${payload}`)
      }
    }
}