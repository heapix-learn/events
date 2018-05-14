<template>
  <div>
    <div v-html="layout" class="new-about">
      
    </div>
    <a @click="$router.go(-1)" class="waves-effect blue lighten-2 red btn col s1 offset-s5">Back</a>
    <a @click="putAbout" class="waves-effect green lighten-2 red btn col s1">Publish</a>
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
      'putAbout'
    ])
  }
}
</script>

<style>
.new-about {
  padding-top: 25px;
}
</style>
