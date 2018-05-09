import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'

export default {
  state: {
    news: [],
    currentNews: {},
    preview: null    
  },
  getters: {
    allNews(state) {
      return state.news;
    },
    currentNews(state) {
      return state.currentNews
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
    setCurrentNews(state, payload) {
      state.currentNews = payload
    },
    clearNewsPreview(state) {
      state.preview = null
    },
    setNews(state, news) {
      state.news = news
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
      return axios.get(`${url}/news`, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
        }
      })
        .then(res => {
          commit('setNews', res.data)
          return res
        })
        .catch(rej => {
          console.log(rej)
          return rej
        })
    },

    getNewsById({commit}, id) {
      return axios.get(`${url}/news/${id}`, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
        }
      })
        .then(res => {
          commit('setNewsPreview', res.data)
          commit('setCurrentNews', res.data)
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },

    postNewNews({commit}, payload) {
      return axios.post(`${url}/news`, payload, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
        }
      })
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
      return axios.put(`${url}/news`, payload)
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
      return axios.delete(`${url}/news/${id}`)
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
    
