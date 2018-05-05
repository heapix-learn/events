import router from '../../../router'
import axios from 'axios'
import url from '../../index'

export default {
  state: {
    news: [],
    preview: null    
  },
  getters: {
    getNews(state) {
      return state.news;
    },
    getNewsPreview(state) {
      return state.preview
    },
    getNewsById: state => id => state.news.find(news => news.id === id)
  },
  mutations: {
    setNewsPreview(state, payload) {
      state.preview = payload
    },
    clearNewsPreview(state) {
      state.preview = null
    },
    setNews(state, news) {
      state.users = news
    },
  },
  actions: {    
    setNewsPreview({commit}, news) {
      commit('setNewsPreview', news)
    },
    clearNewsPreview({commit}) {
      commit('clearNewsPreview');
    },
    getNews({commit}) {
      return axios.get(`${url}/news`)
        .then(res => {
          commit('setNews', res)
          return res
        })
        .catch(rej => {
          console.log(rej)
          return rej
        })
    },

    getNewsById({commit}, id) {
      return axios.get(`${url}/news?id:${id}`)
        .then(res => {
          commit('setNewsPreview', res)
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },

    postNewNews({commit}, payload) {
      return axios.post(`${url}/newnews`, payload)
        .then(res => {
          commit('clearNewsPreview')
          router.push({path: '/news'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },

    putNews({commit}, payload) {
      return axios.post(`${url}/putnews`, payload)
        .then(res => {
          commit('clearNewsPreview')
          router.push({path: '/news'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },

    deleteNews({commit}, id) {
      return axios.delete(`${url}/deletenews?id:${id}`)
        .then(res => {
          router.push({path: '/news'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    }
  }
}
    
