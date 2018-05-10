<template>
  <div class="create-event-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="deleteNewEvent()"></modal>

    <h4 class="center">{{isEdit ? 'Edit event' : 'New event'}}</h4>
    <form @submit.prevent="previewEvent" class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="event-title" type="text" class="validate" v-model="newEvent.firstName" v-validate="'required'" name="Title">
          <label for="event-title">Title</label>
          <span class="helper-text red-text" >{{errors.first('Title')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-date" type="text" v-model="newEvent.date" name="Date">
          <label for="event-date">Date</label>
          <span class="helper-text red-text" >{{errors.first('Date')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-time" type="text" v-model="newEvent.time" name="Time">
          <label for="event-time">Time</label>
          <span class="helper-text red-text" >{{errors.first('Time')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-location" type="text" class="validate" v-model="newEvent.location" v-validate="'required'" name="Location">
          <label for="event-location">Location</label>
          <span class="helper-text red-text" >{{errors.first('Location')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-price" type="text" class="validate" v-model="newEvent.price" v-validate="'required|decimal'" name="Price">
          <label for="event-price">Price</label>
          <span class="helper-text red-text" >{{errors.first('Price')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="event-description" class="materialize-textarea" v-model="newEvent.info" v-validate="'required'" name="Description"/>
          <label for="event-text">Description</label>
          <span class="helper-text red-text" >{{errors.first('Description')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-cap-min" type="text" class="validate" placeholder="0" v-model="newEvent.maxNumberOfRegistrations"  name="Min capacity">
          <label for="event-cap-min">Min capacity</label>
          <span class="helper-text red-text" >{{errors.first('Min capacity')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-cam-max" type="text" class="validate" placeholder="0" v-model="newEvent.minNumberOfRegistrations"  name="Max capacity">
          <label for="event-cam-max">Max capacity</label>
          <span class="helper-text red-text" >{{errors.first('Max capacity')}}</span>
        </div>
      </div>        
    </form>

    <div class="create-event-buttons row center">
      <a @click="previewEvent" :class="{disabled: errors.items.length > 0}" class="waves-effect green waves-light btn">Preview</a>
      <a class="waves-effect red lighten-2 red btn modal-trigger" href="#modal">Cancel</a>
      <a v-if="isEdit" class="waves-effect red darken-2 red btn modal-trigger" href="#modal-delete">Delete</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapActions, mapGetters } from 'vuex';
import Datepicker from 'vuejs-datepicker';

export default {
  name: 'EventCreate',
  data () {
    return {
      isEdit: this.$route.path.match(/edit/)
    }
  },
  watch: {
  },
  components: {
    Modal,
    Datepicker
  },
  computed: {
    ...mapGetters([
      'newEvent'
    ]),
  },
  methods: {
    ...mapActions([
      'clearNewEvent',
      'setNewEvent',
      'deleteEvent',
      'setOldEventAsNewEvent',
    ]),
    abort() {
      this.$router.go(-1);
    },
    previewEvent() {
      this.$validator.validateAll()
      .then(() => {
        if (this.errors.items.length > 0) {
          return
        } else {
          this.setNewEvent(this.newEvent)
          this.$router.push('/events/create/preview')
        }
      })
    },
    deleteNewEvent() {
      console.log(this.newEvent)
      this.deleteEvent(this.newEvent.id)
    }
  },
  mounted() {
    M.FormSelect.init(document.querySelector('select'));
    M.Modal.init(document.querySelectorAll('.modal'));
    M.updateTextFields();

    if (this.isEdit) {
      if (this.newEvent.id < 0) {
        this.setOldEventAsNewEvent(this.$route.params.id)
      }
    }
  },
  updated() {
    M.updateTextFields();
  },
  beforeRouteLeave (to, from, next) {
    if (to.path !== '/events/create/preview') {
      this.clearNewEvent()
    }
    next()
  },
}
</script>

<style>
.create-event-buttons a{
  margin-left: 5px;
  margin-right: 5px;
}
hr {
  margin-bottom: 20px;
}
.modal.datepicker-modal{
  width: auto;
}
.input-props {
  height: 84px;
  padding-top: 50%;
}
.delete-input:hover {
  cursor: pointer;
}
</style>
