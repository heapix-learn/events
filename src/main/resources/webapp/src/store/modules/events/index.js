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
      state.events = events
    },
    setCertainEvent(state, event) {
      state.certainEvent = event
    }
},
  actions: {    
    getAllEvents({commit}) {
      return axios.get(`${url}/events`, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
        }
      })
        .then(res => {
          console.log(res)
          commit('setEvents', res.data)
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    getEventById({commit}, id) {
      return axios.get(`${url}/events/${id}`, {
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
        }
      })
        .then(res => {
          commit('setCertainEvent', {
            id,
            title: res.data.firstName,
            date: res.data.date,
            description: res.data.info,
            location: res.data.location,
            price: res.data.price,
            capacityMin: res.data.minNumberOfRegistrations,
            capacityMax: res.data.maxNumberOfRegistrations
          })
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    postNewEvent({commit, state}) {
      const event = {
        date: '2018-05-07T18:18:35.260Z',
        firstName: state.preview.title,
        info: state.preview.description,
        location: state.preview.location,
        maxNumberOfRegistrations: 0,
        minNumberOfRegistrations: 0,
        price: state.preview.price * 1
      }
      axios({method: 'POST', url: `${url}/events`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: event})
        .then(res => {
          router.push({path: '/events'})
          return res
        })
        .catch(rej => {
          console.dir(rej)
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
    signUpForEvent({commit}, id) {
      axios({method: 'POST', url: `${url}/events/registration`, headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}, data: {"eventId": id}})
        .then(res => {
          router.push({path: '/events'})
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
    
