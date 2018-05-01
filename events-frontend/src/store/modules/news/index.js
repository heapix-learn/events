import router from '../../../router'

export default {
    state: {
        news: [
        {
            id: 1,
            title: 'Curabitur sit amet mauris!',
            text: `Morbi in dui quis est pulvinar ullamcorper. Nulla facilisi. Integer lacinia sollicitudin massa. Cras metus. Sed aliquet risus a tortor. Integer id quam. Morbi mi. Quisque nisl felis, venenatis tristique, dignissim in, ultrices sit amet, augue. Proin sodales libero eget ante. Nulla quam. Aenean laoreet. Vestibulum nisi lectus, commodo ac, facilisis ac, ultricies eu, pede. `,
            role: 'All'
        },
        {
            id: 2,
            title: 'Nulla metus metus!',
            text: `Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. Proin quam.`,
            role: 'All'
        },
        {
            id: 3,
            title: ' Integer lacinia !',
            text: `Etiam ultrices. Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. In vel mi sit amet augue congue elementum. Morbi in ipsum sit amet pede facilisis laoreet. Donec lacus nunc, viverra nec, blandit vel, egestas et, augue. Vestibulum tincidunt malesuada tellus. Ut ultrices ultrices enim. 

            Curabitur sit amet mauris. Morbi in dui quis est pulvinar ullamcorper. Nulla facilisi. Integer lacinia sollicitudin massa. Cras metus. Sed aliquet risus a tortor. Integer id quam. Morbi mi. Quisque nisl felis, venenatis tristique, dignissim in, ultrices sit amet, augue. Proin sodales libero eget ante. Nulla quam. Aenean laoreet. Vestibulum nisi lectus, commodo ac, facilisis ac, ultricies eu, pede. `,
            role: 'Members'
        },
        ],
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
        }
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
    
