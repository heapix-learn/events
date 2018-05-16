<template>
  <div>
    <form-generator-view :form-inputs="endSignUpForm" :isPreview="true" :userFormInput="inputs"></form-generator-view>
    <div class="publish-back-buttons">
      <a @click="publish" class="waves-effect col s1 offset-s5 green waves-light btn">Publish</a>
      <a @click="$router.go(-1)" class="waves-effect col s1 blue waves-light btn">Back</a>
    </div>
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
      'postPublicEndSignUpForm',
    ]),
    publish () {
      this.postPublicEndSignUpForm(this.endSignUpForm) //TODO SEND TO SERVER
      this.$router.push('/users')
    }
  }
}
</script>

<style>
.publish-back-buttons a{
  margin-left: 5px;
  margin-right: 5px;
}
</style>
