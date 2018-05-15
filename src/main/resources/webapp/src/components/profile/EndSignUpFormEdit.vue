<template>
  <div>
    <form-generator :form-inputs="inputs"></form-generator>
    <a @click="previewSignUpForm" class="waves-effect col s2 offset-s5 green waves-light btn">Preview</a>
  </div>
</template>

<script>
import FormGenerator from '../feed/events/FormGenerator.vue'
import { mapActions, mapGetters } from 'vuex';
export default {
  name: 'EndSignUpFormEdit',
  data () {
    return {
      inputs: []
    }
  },
  watch: {
    publicEndSignUpForm () {
      if (this.endSignUpForm.length <= 0)
      this.inputs = this.publicEndSignUpForm
    }
  },
  computed: {
    ...mapGetters([
      'endSignUpForm',
      'publicEndSignUpForm'
    ])
  },
  components: {
    FormGenerator
  },
  methods: {
    ...mapActions([
      'setEndSignUpForm',
      'getPublicEndSignUpForm'
    ]),
    previewSignUpForm() {
      this.setEndSignUpForm(this.inputs)
      this.$router.push('/endsignuppreview')
    }
  },
  mounted () {
    if (this.endSignUpForm.length < 0) {
      this.inputs = this.publicEndSignUpForm
    } else {
      this.inputs = this.endSignUpForm
    }
    M.updateTextFields();
  },
  updated () {
    M.updateTextFields();
  },
  created () {
    this.getPublicEndSignUpForm()
  }
}
</script>

<style>

</style>
