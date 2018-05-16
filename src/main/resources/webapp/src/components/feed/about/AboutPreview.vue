<template>
  <div>
    <h1 class="about-title">{{layout.title}}</h1>
    <div v-html="layout.body"></div>
    <div class="publish-back-buttons">
      <a @click="$router.go(-1)" class="waves-effect blue lighten-2 red btn col s1 offset-s5">Back</a>
      <a @click="putAbout" class="waves-effect green lighten-2 red btn col s1">Publish</a>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'AboutPreview',
  computed: {
    ...mapGetters([
      'layout'
    ])
  },
  methods: {
    ...mapActions([
      'getAbout',
      'putAbout'
    ])
  }
}
</script>

<style>
.new-about {
  padding-top: 25px;
}
.publish-back-buttons a{
  margin-left: 5px;
  margin-right: 5px;
}
</style>
