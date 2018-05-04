import router from '../../../router'
import axios from 'axios'
import url from '../../index'

export default {
    state: {
      users: [],
      pendingUsers: [],
    },
    getters: {
      users: state => state.users,
      pendingUsers: state => state.pendingUsers,
      userById: state => id => state.users.find(user => user.id === id),
      pendingUserById: state => id => state.pendingUsers.find(user => user.id === id)
    },
    mutations: {
      setUsers(state, users) {
        state.users = users
      },
      setPendingUsers(state, users) {
        state.pendingUsers = users
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
    }
}