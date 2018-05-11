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
import { mapActions, mapGetters } from 'vuex';
export default {
  name:'NewsPreview',
  components: {
    NewsItem,
  },
  computed: {
    ...mapGetters([
      'getNewsPreview'
    ])
  },
  methods: {
    ...mapActions([
      'postNewNews',
      'putNews'
    ]),
    publish() {
      const news = this.getNewsPreview
      if (news.id !== -1) {
        this.putNews(news)
      } else {
        this.postNewNews(news)
      }
    },
    cancel() {
      this.$router.go(-1)
    }
  },
}
</script>
<style>

</style>
