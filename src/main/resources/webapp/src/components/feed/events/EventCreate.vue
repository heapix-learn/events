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
          <input @change="changeDate" id="datepicker" v-model="date" type="text" class="datepicker">
          <label for="datepicker">Date</label>
        </div>
        <div class="input-field col s6">
          <input @change="changeTime" id="timepicker" v-model="time" type="text" class="timepicker">
          <label for="timepicker">Time</label>
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
          <input id="event-cap-min" type="text" class="validate" placeholder="0" v-model="newEvent.minNumberOfRegistrations"  name="Min capacity">
          <label for="event-cap-min">Min capacity</label>
          <span class="helper-text red-text" >{{errors.first('Min capacity')}}</span>
        </div>
        <div class="input-field col s6">
          <input id="event-cam-max" type="text" class="validate" placeholder="0" v-model="newEvent.maxNumberOfRegistrations"  name="Max capacity">
          <label for="event-cam-max">Max capacity</label>
          <span class="helper-text red-text" >{{errors.first('Max capacity')}}</span>
        </div>
      </div>        
    </form>

    <h4 class="center">Event registration</h4>

    <div class="row">
      <div v-for="(field, index) in fields" :key="index">
        <div class="input-field col offset-s3 s5">
          <input :id="field.field + '-reg-form'" type="text" v-model="field.field">
          <label class="active" :for="field.field  + '-reg-form'">Input name</label>
        </div>
        <div class="input-props col s4 valign-wrapper">
          <i @click.prevent="removeField(index)" class="material-icons red-text delete-input">clear</i>
        </div>
      </div>
      <a @click.prevent="addField" class="waves-effect waves-light green col offset-s4 s4 btn">Add field</a>
    </div>
    <hr>

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

export default {
  name: 'EventCreate',
  data () {
    return {
      isEdit: this.$route.path.match(/edit/),
      date: '',
      time: '',
      fields: [
        {field: ''}
      ],
    }
  },
  watch: {
  },
  components: {
    Modal,
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
          this.newEvent.date = this.date
          this.newEvent.time = this.time
          this.newEvent.fields = this.fields 
          this.setNewEvent(this.newEvent)
          this.$router.push('/events/create/preview')
        }
      })
    },
    deleteNewEvent() {
      this.deleteEvent(this.newEvent.id)
    },
    changeDate(e) {
      this.date = e.target.value
    },
    changeTime(e) {
      this.time = e.target.value
    },
    addField() {
      this.newEvent.fields.push({field: ''})
    },
    removeField(i) {
      this.newEvent.fields.splice(i, 1)
    }
  },
  mounted() {
    M.FormSelect.init(document.querySelector('select'));
    M.Modal.init(document.querySelectorAll('.modal'));
    M.Datepicker.init(document.querySelector('#datepicker'), {format: 'yyyy-mm-dd'});
    M.Timepicker.init(document.querySelector('#timepicker'), {twelveHour: false});
    M.updateTextFields();

    this.date = this.newEvent.date
    this.time = this.newEvent.time
    this.fields = this.newEvent.fields

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
.modal.timepicker-modal {
  width: auto;
}
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
