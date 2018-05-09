import router from '../../../router'
import axios from 'axios'
import {url} from '../../index'
import root from '../../index'

export default {
  state: {
    events: [],
    certainEvent: null,
    preview: null,
    alreadyRegistered : false
  },
  getters: {
    allEvents: state => state.events,
    getEventById: state => id => state.events.find(event => event.id === id),
    getEventPreview: state => state.preview,
    certainEvent: state => state.certainEvent,
    alreadyRegistered: state => state.alreadyRegistered,
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
    },
    alreadyRegistered(state, registered) {
      state.alreadyRegistered = registered;
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
          });
          return res
        })
        .catch(rej => {
          console.dir(rej)
          return rej
        })
    },
    postNewEvent({commit, state},) {
      const event = {
        date: state.preview.date,
        firstName: state.preview.title,
        info: state.preview.description,
        location: state.preview.location,
        maxNumberOfRegistrations: state.preview.capacityMax * 1,
        minNumberOfRegistrations: state.preview.capacityMin * 1,
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
      return axios.put(`${url}/events/${id}`, state.preview)
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
    unsubscribe({commit}, eventId) {
        axios.delete(`${url}/events/registration/${eventId}`,  {headers: {Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')}})
            .then(res => {
                commit('alreadyRegistered', false)
            })
            .catch(rej => {
                console.dir(rej)
                return rej
            })
    },
    setEventPreview({commit}, event) {
      commit('setEventPreview', event)
    },
    clearEventPreview({commit}) {
      commit('clearEventPreview');
    },
    userAlreadyRegistered({commit}, eventId) {
        return axios.get(`${url}/events/registration/${eventId}`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('eventAppToken')
            }
        })
            .then(res => {
              commit('alreadyRegistered', !!res.data)
            })
            .catch(rej => {
                console.dir(rej)
                return rej
            })
    }
  }
}
    
