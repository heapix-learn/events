import router from '../../../router'

export default {
  state: {
    events: [],
    preview: null
  },
  getters: {
    getEvents: state => state.events,
    getEventById: state => id => state.events.find(event => event.id === id),
    getShortEvents: state => state.events.reduce((result, event) => {
      result.push({
        id: event.id,
        title: event.title,
        date: event.date,
        time: event.time,
        location: event.location
      })
      return result
    }, []),
    getEventPreview: state => state.preview
  },
  mutations: {
    setEventPreview(state, payload) {
      const labels = payload.labels.filter(i => {if (i.label !== '') return i})
      state.preview = payload
      state.preview.labels = labels
    },
    clearEventPreview(state) {
      state.preview = null
    },
    setEvents(state, events) {
      state.users = events
    },
},
  actions: {    
    setEventPreview({commit}, event) {
      commit('setEventPreview', event)
    },
    clearEventPreview({commit}) {
      commit('clearEventPreview');
    },
    getUsers({commit}) {
      return axios.get(`${url}/photos`)
        .then(res => {
          commit('setEvents', res.events)
          router.push({path: '/events'})
          return res
        })
        .catch(rej => {
          console.log(rej)
          return rej
        })
    },
  }
}
    
