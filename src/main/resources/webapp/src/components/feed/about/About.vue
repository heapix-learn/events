<template>
  <div v-html="publicLayout">

  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'About',
  computed: {
    ...mapGetters([
      'publicLayout'
    ])
  },
  methods: {
    ...mapActions([
      'getAbout'
    ])
  },
  created () {
    this.getAbout()
  }
}
</script>

<style>

</style>
