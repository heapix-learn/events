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
      return axios.get(`${url}/photos`)
        .then(res => {
          commit('setPendingUsers', res.news)
          router.push({path: '/news'})
          return res
        })
        .catch(rej => {
          console.log(rej)
          return rej
        })
    },
  }
}
    
