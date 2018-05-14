<template>
  <div class="event-list">
    <event-shortcut v-for="event in allSortedEvents" :key="event.id" :event="event"></event-shortcut>
  </div>
</template>

<script>
import EventShortcut from './EventShortcut.vue';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'Events',
  computed: {
    ...mapGetters([
      'allSortedEvents'
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
