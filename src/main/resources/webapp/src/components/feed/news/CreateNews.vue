<template>
  <div class="create-news-form container">
    <modal title="All changes will be discarded" @confirm="abort"></modal>
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
          <textarea id="news-text" class="materialize-textarea active" v-model="body" v-validate="'required'" name="Text"/>
          <label :class="{active: body}" for="news-text">Text</label>
          <span class="helper-text red-text">{{errors.first('Text')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s5 offset-s3">
          <select v-model="role">
            <option value="5">All</option>
            <option value="4">Members</option>
            <option value="3">Moderators</option>
            <option value="2">Administrators</option>
          </select>
          <label>Visible for</label>
        </div>
      </div>
    </form>
    <div class="create-news-buttons row center">
      <a @click="previewNews" :class="{disabled: errors.items.length > 0 || disabledByFields}" class="waves-effect green waves-light btn">Preview</a>
      <a class="waves-effect red lighten-2 red btn modal-trigger" href="#modal">Cancel</a>
    </div>
  </div>
</template>

<script>
import Modal from '../../utils/Modal.vue'
import { mapActions, mapGetters } from 'vuex';

  export default {
    name: 'NewsCreator',
    data() {
      return {
        title: '',
        body: '',
        role: '5'
      }
    },

    methods: {
      ...mapActions([
        'setNewsPreview',
        'clearNewsPreview',
      ]),
      previewNews(){
        this.setNewsPreview({title: this.title, body: this.body, role: this.role, id: -1})
        this.$router.push('/news/preview')
      },
      abort() {
        this.clearNewsPreview();
        this.$router.push('/news')
      },
    },
    
    computed: {
      ...mapGetters([
        'getNewsById',
        'getNewsPreview'
      ]),
      disabledByFields() {
        return !this.title || !this.body
      }
    },
    mounted() {
      M.Modal.init(document.querySelectorAll('.modal'));
      M.FormSelect.init(document.querySelector('select'));
      const preview = this.getNewsPreview 
      if (preview) {
        this.title = preview.title;
        this.body = preview.body;
        this.role = preview.role
      }
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
