<template>
  <form @submit.prevent="previewEvent" class="change-password-form col s6 offset-s3">
      <div class="row">
        <div class="input-field col s12">
          <input @input="serverError = ''" id="current-password" type="password" class="validate" v-model="currentPassword" v-validate="'required'" name="Current password">
          <label :class="{active: currentPassword}" for="current-password">Current password</label>
          <span class="helper-text red-text" >{{errors.first('Current password')}}</span>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input @input="notEqualError = ''" id="new-password" type="password" v-model="newPassword" v-validate="'required'" name="New password">
          <label :class="{active: newPassword}" for="new-password">New password</label>
          <span class="helper-text red-text" >{{errors.first('New password')}}</span>
        </div>
        <div class="input-field col s6">
          <input @input="notEqualError = ''" id="repeat-password" type="password" v-model="repeatPassword" v-validate="'required'" name="Repeat password">
          <label :class="{active: repeatPassword}" for="repeat-password">Repeat password</label>
          <span class="helper-text red-text" >{{errors.first('Repeat password')}}</span>
        </div>
      </div>
      <div class="row red-text center">{{notEqualError || serverError}}</div>
      <div class="row center">
        <a @click="editPassword" :class="{disabled: serverError}" class="waves-effect green waves-light btn">Change</a>
        <a @click="$router.go(-1)" class="waves-effect red lighten-2 red btn">Cancel</a>
      </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'EditPassword',
  data () {
    return {
      currentPassword: '',
      newPassword: '',
      repeatPassword: '',
      notEqualError: '',
      serverError: '',
    }
  },
  methods: {
    ...mapActions([
      'changePassword'
    ]),
    editPassword() {
      this.$validator.validateAll()
        .then(() => {
          if (this.errors.items.length > 0) {
            return
          } else if (this.newPassword !== this.repeatPassword) {
            this.notEqualError = 'Passwords are not equal'
            return
          } else {
            this.serverError = '';
            this.changePassword({currentPassword: this.currentPassword, newPassword: this.newPassword})
              .catch(rej => {
                console.dir(rej)
                this.serverError = 'Wrong password'
              })
              return
          }
        })
    }
  }
}
</script>

<style>
.change-password-form {
  margin-top: 25px;
}
</style>
