import router from '../../../router'

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
  },
  actions: {    
    setNewsPreview({commit}, news) {
        commit('setNewsPreview', news)
    },
    clearNewsPreview({commit}) {
      commit('clearNewsPreview');
    }
  }
}
    
