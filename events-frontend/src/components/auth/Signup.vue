<template>
    <div class="signup">
      <h1 class="center-align">Welcome!</h1>
      <div class="row">
        <form class="col s12">
          <div class="row">
            <div class="input-field col s6">
              <input id="first_name" type="text">
              <label class="text-green" for="first_name"><span class="required-field">First Name</span></label>
            </div>
            <div class="input-field col s6">
              <input id="last_name" type="text">
              <label for="last_name">Last Name</label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="email" type="email">
              <label for="email"><span class="required-field">Email</span></label>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s12">
              <input id="password" type="password">
              <label for="password"><span class="required-field">Password</span></label>
            </div>
          </div>
          <div class="center-align submit-buttons">
            <a class="waves-effect waves-light btn-large green">Sign Up!</a>
            <router-link to="login" class="btn-flat green-text">Already have account?</router-link>
          </div>
        </form>
      </div>
    </div>
</template>

<script>
import AuthView from './AuthView'

export default {
  name: 'Signup',
  components: {
    AuthView
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
