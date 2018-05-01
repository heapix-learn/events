<template>
  <div class="event-list">
    <event-shortcut v-for="event in getShortEvents" :key="event.id" :event="event"></event-shortcut>
  </div>
</template>

<script>
import EventShortcut from './EventShortcut.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'Events',
  computed: {
    ...mapGetters([
      'getShortEvents'
    ])
  },
  components: {
    EventShortcut
  }
}
</script>

<style>
.event-list {
  padding-top: 15px;
}
</style>
