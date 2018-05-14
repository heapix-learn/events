<template>
  <div class="registered-users">
    {{$route.params}}
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
  name: 'RegisteredForEventUsers',
  computed: {
    ...mapGetters([
      'registeredForEventUsers'
    ])
  },
  methods: {
    ...mapActions([
      'getRegisteredForEventUsers'
    ])
  },
  created () {
    this.getRegisteredForEventUsers(this.$route.params.id)
  }
}
</script>

<style>

</style>
