<template>
  <div class="create-news-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="deleteNewsById"></modal>
    
    <form @submit.prevent class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="news-title" type="text" v-model="title" v-validate="'required'" name="Title">
          <label :class="{active: title}" for="news-title">Title</label>
          <span class="helper-text red-text" >{{errors.first('Title')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="news-text" class="materialize-textarea active" v-model="text" v-validate="'required'" name="Text"/>
          <label :class="{active: text}" for="news-text">Text</label>
          <span class="helper-text red-text">{{errors.first('Text')}}</span>
        </div>
      </div>
    </form>
    <div class="create-news-buttons row center">
      <a @click="previewNews" :class="{disabled: errors.items.length > 0 || disabledByFields}" class="waves-effect green waves-light btn">Preview</a>
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
        title: '',
        text: '',
        id: '',
      }
    },
    methods: {
      ...mapActions([
        'setNewsPreview',
        'clearNewsPreview',
        'deleteNews'
      ]),
      previewNews(){
        this.setNewsPreview({title: this.title, text: this.text, id: this.id})
        this.$router.push('/news/previewedit')
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
        'getNewsById'
      ]),
      disabledByFields() {
        return !this.title || !this.text || !this.role
      }
    },
    mounted() {
      M.Modal.init(document.querySelectorAll('.modal'));
      M.FormSelect.init(document.querySelector('select'));
        const news = this.getNewsById(this.$route.params.id * 1)
        this.title = news.title;
        this.text = news.text;
        this.id = news.id; 
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