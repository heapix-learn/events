<template>
  <div class="event-item container">

    <div class="row">
      <div class="col s12">
        <div class="card white darken-1">
          <div class="card-content black-text">
            <span class="card-title center event-title">{{newEvent.firstName}}</span>
            <p class="event-description">{{newEvent.info}}</p>
            <div class="where-when container">
              <div class="row">
                <p class="col s6">Where: <span>{{newEvent.location}}</span></p>
                <p class="col s6">When: <span>{{newEvent.date}} {{newEvent.time}}</span></p>
              </div>
            </div>
            <p class="center" v-if="newEvent.maxNumberOfRegistrations && newEvent.minNumberOfRegistrations">
              Capacity: minimum {{newEvent.minNumberOfRegistrations}} and maximum {{newEvent.maxNumberOfRegistrations}}</p>
            <p>Price:{{newEvent.price !== 0 ? ' ' + newEvent.price + '$' : ' Free!'}}</p>
          </div>
          <div class="card-action">
          </div>
        </div>
      </div>
    </div>

    <div class="create-event-buttons row center">
      <a @click="publish" class="waves-effect green waves-light btn">Publish</a>
      <a @click="goBack" class="waves-effect red lighten-2 red btn">Back</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'EventCreatePreview',
  components: {
    Modal
  },
  computed: {
    ...mapGetters([
      'newEvent',
    ])
  },
  methods: {
    ...mapActions([
      'postNewEvent',
      'putEvent',
      'clearNewEvent'
    ]),
    publish() {
      if (this.newEvent.id >= 0) {
        this.putEvent()
      } else {
        this.postNewEvent()
      }
    },
    goBack() {
      this.$router.go(-1)
    }
  },
  beforeRouteLeave (to, from, next) {
    if (to.path === '/events/create' || to.path.search(/edit/) !== -1) {
      next()
    } else {
      this.clearNewEvent()
      next()
    }
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
.reg-button {
  cursor: pointer;
}
.event-btn-signup {
  margin-top: 10px;
}

</style>
      