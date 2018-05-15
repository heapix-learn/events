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
      'setEndSignUpForm'
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
  }
}
</script>

<style>

</style>
