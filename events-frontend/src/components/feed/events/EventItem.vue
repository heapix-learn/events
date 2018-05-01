<template>
  <div class="event-item container">

    <div class="row">
      <div class="col s12">
        <div class="card white darken-1">
          <div class="card-content black-text">
            <span class="card-title center event-title">{{event.title}}</span>
            <p class="event-description">{{event.description}}</p>
            <div class="where-when container">
              <div class="row">
                <p class="col s6">Where: <span>{{event.location}}</span></p>
                <p class="col s6">When: <span>{{event.date}} {{event.time}}</span></p>
              </div>
            </div>
            <p class="center" v-if="event.capacityMin && event.capacityMax">Capacity: minimum {{event.capacityMin}} and maximum {{event.capacityMax}}</p>
            <p>Price:{{event.price !== 0 ? ' ' + event.price + '$' : ' Free!'}}</p>
          </div>
          <div class="card-action">
            <router-link class="green-text" :to="'../'">Back</router-link>
            <a v-if="!showRegistration" @click.prevent="openRegistration" class="green-text">Registration</a>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showRegistration" class="row">
      <div class="col s12">
        <form @submit.prevent>
          <div v-for="(label, index) in event.labels" :key="index">
            <input :id="label.label + '-reg-form'" type="text" v-model="eventRegistrationForm[label.label]">
            <label :for="label.label + '-reg-form'">{{label.label}}</label>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapGetters } from 'vuex';

export default {
  name: 'EventItem',
  data() {
    return {
      showRegistration: false,
      eventRegistrationForm: {}
    }
  },
  computed: {
    event() {
      if (this.$route.path.match('/events/preview')) {
        const event = this.getEventPreview();
        if (event !== null) {
          console.log(event)
          return event;
        } else {
          this.$router.go(-1)
        }
      } else {
        return this.$store.getters.getEventById(this.$route.params.id * 1)
      }
    }
  },
  components: {
    Modal
  },
  methods: {
    ...mapGetters([
      'getEventPreview'
    ]),
    openRegistration() {
      this.showRegistration = true
    }
  },
  mounted() {
    M.Modal.init(document.querySelectorAll('.modal'));
  }
}
</script>

<style>
.event-description {
  padding: 10px 0;
  font-size: 1.18em;
}
.event-item {
  margin-top: 15px;
}
.event-title.card-title {
  font-size: 1.8em;
  font-weight: bold;
}
.where-when {
  padding-top: 10px;
}
.where-when span {
  font-weight: bold;
  font-size: 1.1em;
}
</style>
      