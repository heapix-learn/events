<template>
    <div class="login">
      <h1 class="center-align">Welcome!</h1>
      <div class="row">
        <form class="col s12" @submit.prevent="signIn">
          <div class="row">
            <div class="input-field col s12">
              <input id="email" type="email">
              <label for="email">Email</label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="password" type="password">
              <label for="password">Password</label>
            </div>
          </div>
          <div class="center-align submit-buttons">
            <a @click="signIn" class="waves-effect waves-light btn-large green">Login</a>
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
  }
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
