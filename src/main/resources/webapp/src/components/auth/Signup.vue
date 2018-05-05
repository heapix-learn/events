<template>
    <div class="signup">
      <h1 class="center-align">Welcome!</h1>
      <div class="row">
        <form class="col s12">
          <div class="row">
            <div class="input-field col s6">
              <input @input="serverError = ''" id="first-name" type="text" v-model="user.firstName" v-validate="'required|alpha'" name="firstName">
              <label class="text-green" for="first-name"><span class="required-field">First Name</span></label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('firstName')}}</span>
            </div>
            <div class="input-field col s6">
              <input @input="serverError = ''" id="last-name" type="text" v-model="user.lastName" v-validate="'alpha'" name="lastName">
              <label for="last-name">Last Name</label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('lastName')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input @input="serverError = ''" id="email" type="email" v-model="user.email" v-validate="'required|email'" name="email">
              <label for="email"><span class="required-field">Email</span></label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('email')}}</span>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input @input="serverError = ''"  id="password" type="password" v-model="user.password" v-validate="'required'" name="password">
              <label for="password"><span class="required-field">Password</span></label>
              <span class="helper-text red-text" data-error="wrong" data-success="right">{{errors.first('password')}}</span>
            </div>
          </div>
          <div class="row red-text center">
            {{serverError}}
          </div>          
          <div class="center-align submit-buttons">
            <a @click="signUpPost" :class="{disabled: errors.items.length > 0 || disabledByFields}"  class="waves-effect waves-light btn-large green">Sign Up!</a>
            <router-link to="login" class="btn-flat green-text">Already have account?</router-link>
          </div>
        </form>
      </div>
    </div>
</template>

<script>
import AuthView from './AuthView'
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'Signup',
  components: {
    AuthView
  },
  data () {
    return {
      user: {
        email: '',
        firstName: '',
        lastName: '',
        password: '',
      },
      serverError: '',
    }
  },
  computed: {
    ...mapGetters([
      'isLoading'
    ]),
    disabledByFields() {
      return !this.user.email || !this.user.password || this.user.isLoading || !this.user.firstName
    }
  },
  methods: {
    ...mapActions([
      'signUp'
    ]),
    signUpPost() {
      this.serverError = ''
      this.signUp(this.user)          
        .then(res => {
          this.$router.push({path: '/'})
          return res
        })
        .catch(rej => {
          this.serverError = rej.response.status
          return rej
        })
    }
  }
}
</script>

<style scoped>
.signup {
  width: 500px;
  min-height: 300px;
  background-color: #fff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.submit-buttons a:first-child {
  margin-right: 10px;
}
.required-field::after {
  content: " *";
  color: red;
  font-size: 0.8em;
}
</style>
