<template>
  <div>
    <event-item></event-item>
      <div class="create-event-buttons row center">
        <a @click="publish" class="waves-effect green waves-light btn">Publish</a>
        <a @click="cancel" class="waves-effect red lighten-2 red btn">Cancel</a>
    </div>
  </div>
</template>
<script>
import EventItem from "./EventItem.vue"
import Modal from "../../utils/Modal.vue"
import { mapActions, mapGetters } from 'vuex';
export default {
  name:'EventPreview',
  components: {
    EventItem,
    Modal
  },
  computed: {
    ...mapGetters([
      'getEventPreview'
    ])
  },
  methods: {
    ...mapActions([
      'clearEventPreview'
    ]),
    publish() {
      this.clearEventPreview()
      this.$router.push('/events')
    },
    cancel() {
      this.$router.go(-1)
    }
  },
}
</script>
<style>

</style>
