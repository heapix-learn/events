import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'
import root from '../../index'

export default {
  state: {
    events: [],
    certainEvent: null,
    preview: null,
  },
  getters: {
    allEvents: state => state.events,
    getEventById: state => id => state.events.find(event => event.id === id),
    getShortEvents: state => {
      state.events.reduce((result, event) => {
        result.push({
          id: event.id,
          title: event.title,
          date: event.date,
          time: event.time,
          location: event.location
        })
        return result
      }, [])
    },
    getEventPreview: state => state.preview,
    certainEvent: state => state.certainEvent,
  },
  mutations: {
    setEvents(state, payload) {
      state.events = payload
    },
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
    setCertainEvent(state, event) {
      state.certainEvent = event
    }
},
  actions: {    
    getAllEvents({commit}) {
      return axios.get(`${url}/events`)
        .then(res => {
          commit('setEvents', res)
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    getEventById({commit}, id) {
      return axios.get(`/events/${id}`)
        .then(res => {
          commit('setCertainEvent', {
            id,
            title: res.firstName,
            date: res.date,
            description: res.info,
            location: res.location,
            price: res.price,
            capacityMin: res.minNumberOfRegistrations,
            capacityMax: res.maxNumberOfRegistrations
          })
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    putEdittedEvent({commit, state}, id) {
      state.preview.id = id
      return axios.put(`events/${id}`, state.preview)
        .then(res => {
          commit('clearEventPreview')
          router.push({path: '/events'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
        })
    },
    setEventPreview({commit}, event) {
      commit('setEventPreview', event)
    },
    clearEventPreview({commit}) {
      commit('clearEventPreview');
    },
  }
}
    
