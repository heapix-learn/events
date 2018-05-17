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
      changePasswordServerError: state => state.changePasswordServerError,
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
        return axios.get(`${url}/users/registered`, {
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
        return axios.get(`${url}/users/${id}`, {
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
      // getUserByIdAdmin({commit}, id) {
      //   return axios.get(`${url}/users/${id}`, {
      //     headers: {
      //       Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
      //     }
      //   })
      //     .then(res => {
      //       commit('setCurrentUser', res.data)
      //       return res
      //     })
      //     .catch(rej => {
      //       console.dir(rej)
      //       return rej
      //     })
      // },
      getPendingUsers({commit}) {
        return axios.get(`${url}/users/unregistered`, {
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

      putEdittedUser({commit}, payload) {
        commit('setPostEdittedUserError', '')
        console.log()
        return axios.put(
            `${url}/users/${payload.id}`,
            payload,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
            }
          },)
          .then(res => {
            router.go(-1)
            return res
          })
          .catch(rej => {
            commit('changePasswordServerError', rej)
          })
      },

      changePassword({commit}, payload) {
        commit('setChangePasswordServerError', '')
        return axios.put(
            `${url}/users/password`,
            payload,
            {
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
            }
        },)
          .then(res => {
            router.go(-1)
            return res
          })
          .catch(rej => {
            commit('setChangePasswordServerError', rej.response.data.message)
          })
      },

    blockUser({commit, state}, id) {
        return axios.delete(
            `${url}/users/${id}`,
            {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
                }
            })
            .then(res => {
                router.push({path: '/users'});
            })
            .catch(rej => {
                console.dir(rej)
                return rej
            })
    },

      unsubscribeUser({commit, state}) {
        return axios.delete(
            `${url}/users`,
            {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
                }
            })
          .then(res => {
            router.push({path: '/'})
            this.commit('signOut')
            M.toast({html: 'You were unsubscribed!', classes: 'green center'})
            return res
          })
          .catch(rej => {
            console.dir(rej)
            return rej
          })
      },

      postCreateNewUser({commit}, payload) {
        commit('setPostCreateNewUserError', '')
        axios({method: 'POST', url: `${url}/register`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: payload})
          .then(res => {
            router.push({path: '/users'})
            return res
          })
          .catch(rej => {
            commit('setPostCreateNewUserError', 'Cannot create user')
            console.dir(rej)
            return rej
          })
      },

      registerUser({commit}, id) {
        return axios({
            method: 'PUT',
            url: `${url}/register/${id}`,
            headers: {
              Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')},
            data: {}
        })
          .then(res => {
            router.push({path: '/pendingusers'})
            return res
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