<template>
  <div class="create-news-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
    <modal modalId="modal-delete" title="Please confirm deletion" @confirm="abort"></modal>
    
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
      <div class="row">
        <div class="input-field col s12">
          <select id="news-role" v-model="role" v-validate="'required'" name="Role">
            <option value="All">All</option>
            <option value="Members">Members</option>
            <option value="Moders">Moders</option>
            <option value="Admins">Admins</option>
          </select>
          <label for="news-role">Visible for</label>
          <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('Role')}}</span>
        </div>
      </div>        
    </form>
    <div class="create-news-buttons row center">
      <a @click="previewNews" :class="{disabled: errors.items.length > 0 || disabledByFields}" class="waves-effect green waves-light btn">Preview</a>
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
      ]),
      disabledByFields() {
        return !this.title || !this.text || !this.role
      }
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
