<template>
    <div class="login">
      <h1 class="center-align">Welcome!</h1>
      <div class="row">
        <form class="col s12" @submit.prevent="signIn">
          <div class="row">
            <div class="input-field col s12">
              <input id="email" type="email" v-validate="'required|email'" name="email">
              <label for="email">Email</label>
              <span class="helper-text" data-error="wrong" data-success="right">{{errors.first('email')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="password" type="password" v-validate="'required'" name="password">
              <label for="password">Password</label>
              <span class="helper-text" data-error="wrong" data-success="right">{{errors.first('password')}}</span>
            </div>
          </div>
          <div class="center-align submit-buttons">
            <a @click="signIn" :class="{disabled: this.errors.items.length > 0}" class="waves-effect waves-light btn-large green">Login</a>
            <router-link to="signup" class="btn-flat green-text">Don't have account?</router-link>
          </div>
        </form>
      </div>
    </div>     
</template>

<script>
import AuthView from './AuthView.vue'
import { mapActions } from 'vuex';

export default {
  name: 'Login',
  components: {
    AuthView
  },
  methods: {
    ...mapActions([
      'signIn',
    ])
  },

}
</script>

<style>
.login {
  width: 500px;
  min-height: 400px;
  background-color: #fff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%)
}
.submit-buttons a:first-child {
  margin-right: 10px;
}

</style>
