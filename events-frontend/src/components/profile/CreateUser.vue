<template>    
<div>
  <modal title="All changes will be discarded" @confirm="discardChanges"></modal>
  <div class="create-user container">
  <h4 class="center">New user</h4>
    <div class="col s12">
      <div class="row edit-form">
        <form class="col s12">
          <div class="row">
            <div class="input-field col s6">
              <input id="first_name" type="text" v-model="firstName">
              <label class="text-green" for="first_name"><span class="required-field">First Name</span></label>
            </div>
            <div class="input-field col s6">
              <input id="last_name" type="text" v-model="lastName">
              <label for="last_name"><span class="required-field">Last Name</span></label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s6">
              <input id="first_phone" type="text" v-model="firstPhone">
              <label class="text-green" for="first_phone"><span class="required-field">Phone number</span></label>
            </div>
            <div class="input-field col s6">
              <input id="secondary_phone" type="text" v-model="lastPhone">
              <label for="secondary_phone">Phone number</label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="email" type="email" v-model="email">
              <label for="email"><span class="required-field">Email</span></label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <select v-model="role">
                <option value="Member">Member</option>
                <option value="Moderator">Moderator</option>
                <option value="Administrator">Administrator</option>
              </select>
              <label><span class="required-field">Role</span></label>
            </div>
          </div>
        </form>
        <div class="create-user-buttons row center">
            <a class="waves-effect waves green btn cub-create z-depth-1">Create</a>
            <a class="waves-effect waves blue btn cub-cancel z-depth-1 modal-trigger" href="#modal">Clear</a>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Modal from '../utils/Modal.vue'

export default {
  name: 'CreateUser',
  data () {
    return {
      firstName: '',
      lastName: '',
      firstPhone: '',
      lastPhone: '',
      email: '',
      role: 'Member'
    }

  },
  components: {
    Modal
  },
  mounted() {
    M.FormSelect.init(document.querySelector('select'));
    M.Modal.init(document.querySelectorAll('.modal'));
  },

  methods: {
    discardChanges() {
      this.firstName = '',
      this.lastName = '',
      this.firstName = '',
      this.lastPhone = '',
      this.email = '',
      this.role = 'Member'
    }
  },
}
</script>

<style>
.required-field::after {
  content: " *";
  color: red;
  font-size: 0.8em;
}
.create-user-buttons a{
  margin-left: 5px;
  margin-right: 5px;
}

</style>
