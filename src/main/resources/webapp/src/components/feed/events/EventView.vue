<template>
  <div>
    <event-item :event="certainEvent"></event-item>
      <div class="create-event-buttons row center">
        <a @click="cancel" class="waves-effect red lighten-2 red btn">Back</a>
      </div>
  </div>
</template>
<script>
import EventItem from "./EventItem.vue"
import { mapActions, mapGetters } from 'vuex';
export default {
  name:'EventPreview',
  components: {
    EventItem,
  },
  computed: {
    ...mapGetters([
      'certainEvent'
    ])
  },
  created () {
    this.getEventById(this.$router.params.id)
  },
  methods: {
    ...mapActions([
      'clearEventPreview',
      'getEventById'
    ]),
    cancel() {
      this.$router.go(-1)
    }
  },
}
</script>
<style>

</style>
