<template>
  <div class="end-form">
    <h2 class="center-align">Finish sign up!</h2>
    <form-generator-view :isPreview="false" :userFormInput="inputs" :formInputs="publicEndSignUpForm"></form-generator-view>
    <a @click="endSignUp" class="waves-effect waves-light btn green">Finish sign up</a>
  </div>
</template>

<script>
import FormGeneratorView from '../../components/feed/events/FormGeneratorView.vue'
import { mapActions, mapGetters } from 'vuex';
export default {
  name: 'EndSignUp',
  data () {
    return {
      inputs: {},
    }
  },
  computed: {
    ...mapGetters([
      'publicEndSignUpForm'
    ])
  },
  components: {
    FormGeneratorView
  },
  methods: {
    ...mapActions([
      'getPublicEndSignUpForm',
      'postEndSignUp'
    ]),
    endSignUp () {
      this.postEndSignUp(this.inputs)
    }
  },
  mounted () {
    M.FormSelect.init(document.querySelectorAll('select'))
  },
  updated () {
    M.FormSelect.init(document.querySelectorAll('select'))
  },
  created () {
    this.getPublicEndSignUpForm()
  }
}
</script>

<style>
.end-form {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%)
}
</style>
