import router from '../../../router'

export default {
  state: {
    events: [
      {
        id: 1,
        title: 'Sed nisi.',
        date: '2018-06-01',
        time: '12:00:00',
        location: 'Horse Arena, Budapest',
        description: `Curabitur sodales ligula in libero. Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. 
        Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh. Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. Proin quam. `,
        price: 0,
        capacityMin: 0,
        capacityMax: 0,
      },
      {
        id: 2,
        title: 'Etiam ultrices.',
        date: '2018-06-02',
        time: '06:00:00',
        location: 'Turtle Palace, Praha',
        description: `Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam!`,
        price: 10,
        capacityMin: 20,
        capacityMax: 100,
      },
    ],
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
      state.preview = payload
    },
    clearEventPreview(state) {
      state.preview = null
    }
},
  actions: {    
    setEventPreview({commit}, event) {
      commit('setEventPreview', event)
    },
    clearEventPreview({commit}) {
      commit('clearEventPreview');
    }
  }
}
    
