<template>
  <div class="event-list">
    <event-shortcut v-for="event in getShortEvents" :key="event.id" :event="event"></event-shortcut>
  </div>
</template>

<script>
import EventShortcut from './EventShortcut.vue';
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'Events',
  computed: {
    ...mapActions([
      'getEvents'
    ]),
    ...mapGetters([
      'getShortEvents'
    ]),
  },
  components: {
    EventShortcut
  },
  mounted() {
    this.getEvents
  }
}
</script>

<style>
.event-list {
  padding-top: 15px;
}
</style>
