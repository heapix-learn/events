<template>
  <div class="create-event-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="abort"></modal>

    <h4 class="center">Event body</h4>
    <form @submit.prevent="previewEvent" class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="event-title" type="text" class="validate" v-model="eventData.title" v-validate="'required'" name="Title">
          <label :class="{active: eventData.title}" for="event-title">Title</label>
          <span class="helper-text red-text" >{{errors.first('Title')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-date" type="text" v-model="eventData.date" v-validate="'required'" name="Date">
          <label :class="{active: eventData.date}" for="event-date">Date</label>
          <span class="helper-text red-text" >{{errors.first('Date')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-time" type="text" v-model="eventData.time" v-validate="'required'" name="Time">
          <label :class="{active: eventData.time}" for="event-time">Time</label>
          <span class="helper-text red-text" >{{errors.first('Time')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-location" type="text" class="validate" v-model="eventData.location" v-validate="'required'" name="Location">
          <label :class="{active: eventData.location}" for="event-location">Location</label>
          <span class="helper-text red-text" >{{errors.first('Location')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-price" type="text" class="validate" v-model="eventData.price" v-validate="'required|decimal'" name="Price">
          <label :class="{active: eventData.price}" for="event-price">Price</label>
          <span class="helper-text red-text" >{{errors.first('Price')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="event-description" class="materialize-textarea" v-model="eventData.description" v-validate="'required'" name="Description"/>
          <label :class="{active: eventData.description}" for="event-text">Description</label>
          <span class="helper-text red-text" >{{errors.first('Description')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s4">
          <input id="event-cap-min" type="text" class="validate" v-model="eventData.capacityMin"  name="Min capacity">
          <label :class="{active: eventData.capacityMin}" for="event-cap-min">Min capacity</label>
          <span class="helper-text red-text" >{{errors.first('Min capacity')}}</span>
        </div>
        <div class="input-field col s4">
          <input id="event-cam-max" type="text" class="validate" v-model="eventData.capacityMax"  name="Max capacity">
          <label :class="{active: eventData.capacityMax}" for="event-cam-max">Max capacity</label>
          <span class="helper-text red-text" >{{errors.first('Max capacity')}}</span>
        </div>
      </div>        
    </form>

    <h4 class="center">Event registration</h4>

    <div class="row">
      <div v-for="(label, index) in eventData.labels" :key="index">
        <div class="input-field col offset-s3 s5">
          <input :id="label + '-reg-form'" type="text" v-model="label.label">
          <label :class="{active: label.label}" :for="label + '-reg-form'">Input name</label>
        </div>

        <div class="input-props col s4 valign-wrapper">
          <i @click.prevent="removeField(index)" class="material-icons red-text delete-input">clear</i>
        </div>
      </div>
      <a @click.prevent="addField" class="waves-effect waves-light green col offset-s4 s4 btn">Add field</a>
    </div>
    <hr>

    <div class="create-event-buttons row center">
      <a @click="previewEvent" :class="{disabled: errors.items.length > 0 || disabledByFields}" class="waves-effect green waves-light btn">Preview</a>
      <a class="waves-effect red lighten-2 red btn modal-trigger" href="#modal">Cancel</a>
      <a v-if="isEdit" class="waves-effect red darken-2 red btn modal-trigger" href="#modal-delete">Delete</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'CreateEditEvent',
  data() {
    return {
      eventData: {
        id: null,
        title: '',
        location: '',
        description: '',
        date: '',
        time: '',
        price: 0,
        capacityMin: 0,
        capacityMax: 0,
        role: '',
        labels: [
          {label: ''}
        ]
      },
      isEdit: this.$route.path.match(/edit/)
    }
  },
  components: {
    Modal
  },
  computed: {
    ...mapGetters([
      'certainEvent',
      'getEventPreview'
    ]),
    disabledByFields() {
      const data = this.eventData
      return !data.title || !data.location || !data.description || !data.date || !data.time || !data.price
    }
  },
  methods: {
    ...mapActions([
      'setEventPreview'
    ]),
    abort() {
      this.$router.go(-1);
    },
    previewEvent() {
      this.setEventPreview({...this.eventData, labels: this.eventData.labels})
      this.$router.push('/events/preview')
    },
    addField() {
      this.eventData.labels.push({label: ''})
    },
    removeField(i) {
      this.eventData.labels.splice(i, 1)
    }
  },
  mounted() {
    M.FormSelect.init(document.querySelector('select'));
    M.Modal.init(document.querySelectorAll('.modal'));

    if (this.isEdit) {
      this.eventData = this.certainEvent
    } else {
      const previewEvent = this.getEventPreview;
      if (previewEvent) {
        this.eventData = previewEvent
      }
    } 
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
