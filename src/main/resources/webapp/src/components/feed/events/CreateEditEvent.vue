<template>
  <div class="create-event-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="abort"></modal>

    <h4 class="center">Event body</h4>
    <form @submit.prevent class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="event-title" type="text" class="validate" v-model="eventData.title">
          <label :class="{active: eventData.title}" for="event-title">Title</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-date" type="text" v-model="eventData.date">
          <label :class="{active: eventData.date}" for="event-date">Date</label>
        </div>
        <div class="input-field col s6">
          <input id="event-time" type="text" v-model="eventData.time">
          <label :class="{active: eventData.time}" for="event-time">Time</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="event-location" type="text" class="validate" v-model="eventData.location">
          <label :class="{active: eventData.location}" for="event-location">Location</label>
        </div>
        <div class="input-field col s6">
          <input id="event-price" type="text" class="validate" v-model="eventData.price">
          <label :class="{active: eventData.price}" for="event-price">Price</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="event-description" class="materialize-textarea" v-model="eventData.description"></textarea>
          <label :class="{active: eventData.description}" for="event-text">Description</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s4">
          <select id="event-role" v-model="eventData.role">
            <option value="All">All</option>
            <option value="Members">Members</option>
            <option value="Moders">Moders</option>
            <option value="Admins">Admins</option>
          </select>
          <label for="event-role">Visible for</label>
        </div>
        <div class="input-field col s4">
          <input id="event-cap-min" type="text" class="validate" v-model="eventData.capacityMin">
          <label :class="{active: eventData.capacityMin}" for="event-cap-min">Min capacity</label>
        </div>
        <div class="input-field col s4">
          <input id="event-cam-max" type="text" class="validate" v-model="eventData.capacityMax">
          <label :class="{active: eventData.capacityMax}" for="event-cam-max">Max capacity</label>
        </div>
      </div>        
    </form>

    <h4 class="center">Event registration</h4>

    <!-- <registration-form></registration-form> -->
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
      <a @click="previewEvent" class="waves-effect green waves-light btn">Preview</a>
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
        title: '',
        location: '',
        description: '',
        date: '',
        time: '',
        price: '',
        capacityMin: '',
        capacityMax: '',
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
      'getEventById',
      'getEventPreview'
    ])
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
      this.eventData = this.getEventById(this.$route.params.id * 1)
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
