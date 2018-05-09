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
            <a v-if="!showRegistration && !alreadyRegistered" @click="openRegistration" class="green-text reg-button">Registration</a>
            <a v-if="alreadyRegistered" @click="closeRegistration" class="green-text reg-button">Unsubscribe</a>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showRegistration" class="row">
      <div class="col s12">
        <h5 class="center">Register for the event</h5>
        <form @submit.prevent>
          <div class="input-field" v-for="(label, index) in event.labels" :key="index">
            <input @blur="inputOnBlur" :id="label.label + '-reg-form'" type="text" v-model="eventRegistrationForm[label.label]" v-validate="'required'" :name="label.label">
            <label :for="label.label + '-reg-form'">{{label.label}}</label>
            <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first(label.label)}}</span>
          </div>
          <a v-if="!$route.path.match('preview')" @click="eventSignUp" :class="{disabled: errors.items.length > 0}" class="waves-effect green waves-light btn event-btn-signup col s2 offset-s5">Sign up</a>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'EventItem',
  props: ['event'],
  data() {
    return {
      showRegistration: false,
      eventRegistrationForm: {

      },
      isSignUpFieldsEmpty: true,
    }
  },
  components: {
    Modal
  },
  computed: {
    ...mapGetters([
      'loggedUser',
      'alreadyRegistered'
    ])
  },
  methods: {
    ...mapActions([
      'signUpForEvent',
      'userAlreadyRegistered',
      'unsubscribe'
    ]),
    openRegistration() {
      this.showRegistration = true
    },
    closeRegistration() {
      this.unsubscribe(this.$route.params.id);
    },
    eventSignUp() {
      this.$validator.validateAll()
        .then(() => {
          if (this.errors.items.length > 0) {
            return
          } else {
            this.signUpForEvent(this.$route.params.id)
          }
        })
    },

    inputOnBlur() {
      for (let key in this.eventRegistrationForm) {
        if (this.eventRegistrationForm[key] === '') {
          this.isSignUpFieldsEmpty = true;
          return
        }
      }
      this.isSignUpFieldsEmpty = false
      return
    }
  },
  mounted() {
    M.Modal.init(document.querySelectorAll('.modal'));
    this.userAlreadyRegistered(this.$route.params.id);
    this.userRegistered = this.alreadyRegistered;
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
      