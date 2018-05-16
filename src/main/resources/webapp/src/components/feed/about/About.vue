<template>
  <div>
    <h1 class="about-title">{{publicLayout.title}}</h1>
    <div v-html="publicLayout.body"></div>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex';

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
    created() {
      this.getAbout()
    }
  }
</script>

<style>
  .about-title {
      text-align: center;
  }
</style>
