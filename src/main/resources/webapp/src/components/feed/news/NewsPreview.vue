<template>
  <div>
    <news-item :news="getNewsPreview"></news-item>
      <div class="create-news-buttons row center">
        <a @click="publish" class="waves-effect green waves-light btn">Publish</a>
        <a @click="cancel" class="waves-effect red lighten-2 red btn">Cancel</a>
    </div>
  </div>
</template>
<script>
import NewsItem from "./NewsItem.vue"
import Modal from "../../utils/Modal.vue"
import { mapActions, mapGetters } from 'vuex';
export default {
  name:'NewsPreview',
  components: {
    NewsItem,
    Modal
  },
  computed: {
    ...mapGetters([
      'getNewsPreview'
    ])
  },
  methods: {
    ...mapActions([
      'clearNewsPreview'
    ]),
    publish() {
      this.clearNewsPreview()
      this.$router.push('/news')
    },
    cancel() {
      this.$router.go(-1)
    }
  },
}
</script>
<style>

</style>
