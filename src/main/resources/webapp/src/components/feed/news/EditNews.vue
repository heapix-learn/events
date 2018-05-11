<template>
  <div class="create-news-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="deleteNewsById"></modal>
    
    <form @submit.prevent class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="news-title" type="text" v-model="currentNews.title" v-validate="'required'" name="Title">
          <label :class="{active: currentNews.title}" for="news-title">Title</label>
          <span class="helper-text red-text" >{{errors.first('Title')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="news-text" class="materialize-textarea active" v-model="currentNews.body" v-validate="'required'" name="Text"/>
          <label :class="{active: currentNews.body}" for="news-text">Text</label>
          <span class="helper-text red-text">{{errors.first('Text')}}</span>
        </div>
      </div>
    </form>
    <div class="create-news-buttons row center">
      <a @click="previewNews" :class="{disabled: errors.items.length > 0}" class="waves-effect green waves-light btn">Preview</a>
      <a class="waves-effect red lighten-2 red btn modal-trigger" href="#modal">Cancel</a>
      <a class="waves-effect red darken-2 red btn modal-trigger" href="#modal-delete">Delete</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapActions, mapGetters } from 'vuex';
  export default {
    name: 'NewsEditor',
    data() {
      return {
      }
    },
    methods: {
      ...mapActions([
        'getNewsById',
        'setNewsPreview',
        'clearNewsPreview',
        'deleteNews'
      ]),
      previewNews(){
        this.$validator.validateAll()
        .then(() => {
          if (this.errors.items.length > 0) {
            return
          } else {
            this.setNewsPreview({title: this.title, text: this.body, id: this.id})
            this.$router.push('/news/preview')
          }
        })
      },
      abort() {
        this.clearNewsPreview();
        this.$router.push('/news')
      },
      deleteNewsById() {
        this.deleteNews(this.$route.params.id)
      }
    },
    computed: {
      ...mapGetters([
        'currentNews'
      ]),
    },
    mounted() {
      M.Modal.init(document.querySelectorAll('.modal'));
      M.FormSelect.init(document.querySelector('select'));
      this.getNewsById(this.$route.params.id)
    },
    updated() {
      M.textareaAutoResize(document.querySelector('#news-text'));
    },
    components: {
      Modal,
    },
    beforeRouteLeave (to, from, next) {
      if (to.path !== '/news/preview') {
        this.clearNewsPreview()
      }
      next()
    }
  }
</script>

<style>
.create-news-form {
  margin-top: 18px;
}
.create-news-buttons a{
  margin-left: 5px;
  margin-right: 5px;
}
/* label focus color */
.input-field input:focus + label, .input-field textarea:focus + label {
  color: #4CAF50 !important;
}
/* label underline focus color */
.input-field input:focus, .input-field textarea:focus {
  border-bottom: 1px solid #4CAF50 !important;
}
</style>