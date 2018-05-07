<template>
  <div class="event-list">
    <event-shortcut v-for="event in allEvents" :key="event.id" :event="event"></event-shortcut>
  </div>
</template>

<script>
import EventShortcut from './EventShortcut.vue';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'Events',
  computed: {
    ...mapGetters([
      'allEvents'
    ]) 
  },
  methods: {
    ...mapActions([
      'getAllEvents'
    ]),
  },
  created () {
    this.getAllEvents()
  },
  components: {
    EventShortcut
  },
}
</script>

<style>
.event-list {
  padding-top: 15px;
}
</style>
