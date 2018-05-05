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
              <input id="first_name" type="text" v-model="firstName" v-validate="'required|alpha'" name="First Name">
              <label class="text-green" for="first_name"><span class="required-field">First Name</span></label>
              <span class="helper-text red-text" >{{errors.first('First Name')}}</span>
            </div>
            <div class="input-field col s6">
              <input id="last_name" type="text" v-model="lastName" v-validate="'required|alpha'" name="Last Name">
              <label for="last_name"><span class="required-field">Last Name</span></label>
              <span class="helper-text red-text" >{{errors.first('Last Name')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s6">
              <input id="first_phone" type="text" v-model="firstPhone" v-validate="{ required: true, regex: /^\+([0-9]{9,12})$/ }" name="Phone number">
              <label class="text-green" for="first_phone"><span class="required-field">Phone number</span></label>
              <span class="helper-text red-text" >{{errors.first('Phone number')}}</span>
            </div>
            <div class="input-field col s6">
              <input id="secondary_phone" type="text" v-model="lastPhone" v-validate="{ regex: /^\+([0-9]{9,12})$/ }" name="Phone number 2">
              <label for="secondary_phone">Phone number</label>
              <span class="helper-text red-text" >{{errors.first('Phone number 2')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="email" type="email" v-model="email" v-validate="'required|email'" name="Email">
              <label for="email"><span class="required-field">Email</span></label>
              <span class="helper-text red-text">{{errors.first('Email')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="password" type="password" v-model="password" v-validate="'required'" name="Password">
              <label for="password"><span class="required-field">Password</span></label>
              <span class="helper-text red-text">{{errors.first('Password')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <select v-model="role"  v-validate="'required'" name="Role">
                <option value="Member">Member</option>
                <option value="Moderator">Moderator</option>
                <option value="Administrator">Administrator</option>
              </select>
              <label><span class="required-field">Role</span></label>
              <span class="helper-text red-text">{{errors.first('Role')}}</span>
            </div>
          </div>
        </form>
        <div class="row center red-text">{{serverError}}</div>
        <div class="create-user-buttons row center">
            <a @click="createNewUser" :class="{disabled: errors.items.length > 0}" class="waves-effect waves green btn cub-create z-depth-1">Create</a>
            <a class="waves-effect waves blue btn cub-cancel z-depth-1 modal-trigger" href="#modal">Clear</a>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Modal from '../utils/Modal.vue'
import { mapActions } from 'vuex';

export default {
  name: 'CreateUser',
  data () {
    return {
      firstName: '',
      lastName: '',
      firstPhone: '',
      lastPhone: '',
      email: '',
      role: 'Member',
      password: '',
      serverError: '',
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
    ...mapActions([
      'createUser',
    ]),
    discardChanges() {
      this.firstName = '',
      this.lastName = '',
      this.firstName = '',
      this.lastPhone = '',
      this.email = '',
      this.role = 'Member'
    },
    createNewUser() {
      this.$validator.validateAll()
        .then(() => {
          if (this.errors.items.length > 0) {
            return
          } else {
            this.serverError = '',
            this.createUser({
              firstName: this.firstName,
              lastName: this.lastName,
              firstPhone: this.firstPhone,
              lastPhone: this.lastPhone,
              email: this.email,
              role: this.role,
              password: this.password
            }).then(res => {
                router.push({path: '/users'})
                return res
              })
              .catch(rej => {
                this.serverError = rej.response.status
                return rej
              })
          }
        })
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
