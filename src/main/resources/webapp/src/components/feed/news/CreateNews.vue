<template>
  <div class="create-news-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="abort"></modal>
    
    <form @submit.prevent class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <input id="news-title" type="text" class="validate" v-model="title">
          <label :class="{active: title}" for="news-title">Title</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <textarea id="news-text" class="materialize-textarea active" v-model="text"></textarea>
          <label :class="{active: text}" for="news-text">Text</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <select id="news-role" v-model="role">
            <option value="All">All</option>
            <option value="Members">Members</option>
            <option value="Moders">Moders</option>
            <option value="Admins">Admins</option>
          </select>
          <label for="news-role">Visible for</label>
        </div>
      </div>        
    </form>
    <div class="create-news-buttons row center">
      <a @click="previewNews" class="waves-effect green waves-light btn">Preview</a>
      <a class="waves-effect red lighten-2 red btn modal-trigger" href="#modal">Cancel</a>
      <a v-if="isEdit" class="waves-effect red darken-2 red btn modal-trigger" href="#modal-delete">Delete</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapActions, mapGetters } from 'vuex';

  export default {
    data() {
      return {
        title: '',
        text: '',
        role: '',
        isEdit: this.$route.path.match(/edit/)
      }
    },
    methods: {
      ...mapActions([
        'setNewsPreview',
        'clearNewsPreview'
      ]),
      previewNews(){
        this.setNewsPreview({title: this.title, text: this.text, role: this.role})
        this.$router.push('/news/preview')
      },
      abort() {
        this.clearNewsPreview();
        this.$router.push('/news')
      },
    },
    computed: {
      ...mapGetters([
        'getNewsById'
      ])
    },
    name: 'NewsCreator',
    mounted() {
      M.Modal.init(document.querySelectorAll('.modal'));
      M.FormSelect.init(document.querySelector('select'));

      if (this.isEdit) {
        const news = this.getNewsById(this.$route.params.id * 1)
        this.title = news.title;
        this.text = news.text;
        this.role = news.role; 
      }
      const news = this.$store.getters.getNewsPreview; 
      if (news) {
        this.title = news.title;
        this.text = news.text;
        this.role = news.role; 
      }
    },
    components: {
      Modal,
    }
  }
</script>

<style>
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
