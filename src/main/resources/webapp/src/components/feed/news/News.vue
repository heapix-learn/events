<template>
  <div>
    <news-item v-for="newsItem in getNews" 
    :key="newsItem.id" :news="newsItem"></news-item>
  </div>
</template>
<script>
import { mapActions } from 'vuex';
import NewsItem from "./NewsItem.vue"
export default {
  name:'NewsList',
  computed:{
    ...mapActions([
      'getNews'
    ])
  },
  methods: {
  },
  components: {
      NewsItem,
  },
}
</script>
<style>

</style>
