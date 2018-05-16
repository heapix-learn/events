<template>
  <div class="row">
    <h3 class="center">Edit About</h3>
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <textarea v-model="layout.title" id="title-textarea" class="materialize-textarea"></textarea>
          <label for="title-textarea">Title</label>
        </div>
        <div class="input-field col s12">
          <textarea v-model="layout.body" id="body-textarea" class="materialize-textarea"></textarea>
          <label for="body-textarea">Body</label>
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
      'layout',
      'publicLayout',
    ]),
  },
  methods: {
    ...mapActions([
      'setLayout',
      'getAbout'
    ]),
    previewAbout () {
      this.setLayout({title: this.layout.title, body: this.layout.body})
      this.$router.push('/about/preview')
    }
  },
  updated() {
    M.updateTextFields();
    // M.textareaAutoResize(document.querySelector('#body-textarea'));
  },
  mounted() {
    if (this.layout.title === '' && this.layout.body === '') {
      this.getAbout()
    }
    M.updateTextFields();
    M.textareaAutoResize(document.querySelector('#body-textarea'));
  }
}
</script>

<style>
#about-textarea {
  min-width:200px 
}
</style>
