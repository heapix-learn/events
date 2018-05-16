import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'

export default {
  state: {
    publicLayout: '',
    layout: {title: '', body: ''},
  },
  getters: {
    layout: state => state.layout,
    publicLayout: state => state.publicLayout
  },
  mutations: {
    setLayout: (state, layout) => state.layout = layout,
    setPublicLayout: (state, layout) => state.publicLayout = layout,
  },
  actions: {    
    setLayout: ({commit}, layout) => commit('setLayout', layout),
    putAbout({commit, state}) {
      axios({method: 'PUT', url: `${url}/about`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: state.layout})
        .then(res => {
          commit('setPublicLayout', layout)
          router.push({path: '/about'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    getAbout({commit, state}) {
      return axios.get(`${url}/about`)
        .then(res => {
          commit('setPublicLayout', res.data)
          if (state.layout.title === '' && state.layout.body === '') {
            commit('setLayout', res.data)
          }
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
  }
}
    
