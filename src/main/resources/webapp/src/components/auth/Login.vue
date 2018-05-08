<template>
    <div class="login">
      <h1 class="center-align">Welcome!</h1>
      <div class="row">
        <form class="col s12" @submit.prevent="signIn">
          <div class="row">
            <div class="input-field col s12">
              <input @input="serverError = ''" id="email" type="email" v-model="email" v-validate="'required|email'" name="email">
              <label for="email">Email</label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('email')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input @input="serverError = ''" @keyup.enter="signInPost" id="password" type="password" v-model="password" v-validate="'required'" name="password">
              <label for="password">Password</label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('password')}}</span>
            </div>
          </div>
          <div v-if="serverError" class="server-error center red-text">{{serverError}}</div>
          <div class="center-align submit-buttons">
            <a @click="signInPost" :class="{disabled: errors.items.length > 0 || disabledByFields}" class="waves-effect waves-light btn-large green">Login</a>
            <router-link to="signup" class="btn-flat green-text">Don't have account?</router-link>
          </div>
        </form>
      </div>
    </div>     
</template>

<script>
import AuthView from './AuthView.vue'
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'Login',
  components: {
    AuthView
  },
  data() {
    return {
      email: '',
      password: '',
      serverError: '',
    }
  },
  methods: {
    ...mapActions([
      'postSignIn',
    ]),
    signInPost() {
      this.postSignIn({username: this.email, password: this.password})
    },

  },
  computed: {
    ...mapGetters([
      'isLoading',
      'loginError'
    ]),
    disabledByFields() {
      return !this.email || !this.password || this.isLoading
    }
  },
  mounted() {
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
.server-error {
  padding-bottom: 20px;
}

</style>
