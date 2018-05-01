<template>
<div class="container">
  <div class="row">
    <div class="news-item col s12">
      <h3 class="header">{{news.title}}</h3>
      <router-link v-if="!($route.path.match('preview') || !isLogged)" :to="'news/edit/' + news.id">
        <a class="waves-effect green btn-floating edit-news-button"><i class="material-icons left ">edit</i></a>
      </router-link>
      <div class="card horizontal">
        <div class="card-stacked">
          <div @click = "changeSize" class="card-content news-text shorten">
            {{news.text}}
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    name: 'NewsItem',
    props: ["news"],
    methods: {
      changeSize(event) {
              event.currentTarget.classList.toggle("shorten");
          },
    },
    computed: {
      ...mapGetters([
        'isLogged'
      ])
    }
}
</script>

<style>
  .shorten {
    width: 700px;
    display:inline-block;
    white-space: nowrap;
    overflow:hidden !important;
    text-overflow: ellipsis;
    word-wrap: break-word;
  }
  .news-item {
    position: relative;
  }
  .edit-news-button {
    position: absolute;
    margin: 30px 15px 0 0;
    top: 0;
    right: 0;
  }
</style>
