<template>
  <div>
    <news-item v-for="newsItem in allNews" 
    :key="newsItem.id" :news="newsItem"></news-item>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';
import NewsItem from "./NewsItem.vue"
export default {
  name:'NewsList',
  computed: {
    ...mapGetters([
      'allNews'
    ])
  },
  methods: {
    ...mapActions([
      'getNews'
    ])
  },
  created () {
    this.getNews()
  },
  components: {
    NewsItem,
  },
}
</script>
<style>

</style>
