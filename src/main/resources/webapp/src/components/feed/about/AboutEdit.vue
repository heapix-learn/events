<template>

  <div class="row">
    <h3 class="center">Edit About</h3>
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <textarea v-model="layout" id="about-textarea" class="materialize-textarea"></textarea>
          <label for="textarea1">About</label>
        </div>
      </div>
    </form>
    <a @click="previewAbout" class="waves-effect green waves-light btn col s2 offset-s5">Preview</a>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'AboutEdit',
  computed: {
    ...mapGetters([
      'layout'
    ]),
    layout: {
      get () {
        return this.$store.getters.layout
      },
      set (value) {
        this.$store.commit('setLayout', value)
      }
    }
  },
  methods: {
    ...mapActions([
      'setLayout'
    ]),
    previewAbout () {
      this.$router.push('/about/preview')
    }
  },
  updated() {
    M.updateTextFields();
    M.textareaAutoResize(document.querySelector('#about-textarea'));
  },
  mounted() {
    M.updateTextFields();
  }
}
</script>

<style>
#about-textarea {
  min-width:200px 
}
</style>
