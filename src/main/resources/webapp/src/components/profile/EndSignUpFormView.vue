<template>
  <div>
    <form-generator-view :form-inputs="endSignUpForm" :isPreview="true" :userFormInput="inputs"></form-generator-view>
    <a @click="publish" class="waves-effect col s1 offset-s5 green waves-light btn">Publish</a>
    <a @click="$router.go(-1)" class="waves-effect col s1 blue waves-light btn">Back</a>
  </div>
</template>

<script>
import FormGeneratorView from '../feed/events/FormGeneratorView.vue'
import { mapActions, mapGetters } from 'vuex';
export default {
  name: 'EndSignUpFormView',
  data () {
    return {
      inputs: []
    }
  },
  computed: {
    ...mapGetters([
      'endSignUpForm'
    ])
  },
  components: {
    FormGeneratorView
  },
  methods: {
    ...mapActions([
      'putPublicEndSignUpForm'
    ]),
    publish () {
      this.putPublicEndSignUpForm(this.endSignUpForm) //TODO SEND TO SERVER
      this.$router.push('/users')
    }
  }
}
</script>

<style>

</style>
