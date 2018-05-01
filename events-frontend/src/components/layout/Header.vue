<template>
  <div class="header">
    <nav>
      <div class="nav-wrapper green">
        <div class="row">
          <router-link to="/">
            <a class="brand-logo">Lo Marraco</a>
          </router-link>
          <ul class="right hide-on-med-and-down">

            <li :class="activePath('/events')"><router-link to="/events">Events</router-link></li>
            <li :class="activePath('/news')"><router-link to="/news">News</router-link></li>

            <template v-if="isLogged">
              <li key="dropdown">
                <ul id="dropdown1" class="dropdown-content">
                  <li><router-link :to="'/users/' + loggedUserId">My profile</router-link></li>
                  <li><a @click="$store.dispatch('signOut')">Logout</a></li>
                </ul>
              </li>
              <li><a class="dropdown-trigger" data-target="dropdown1">Profile<i class="material-icons right">arrow_drop_down</i></a></li>
            </template>
            
            <template v-else>
                <li key="login"><router-link to="/auth/login">Login</router-link></li>
                <li><router-link to="/auth/signup">Signup</router-link></li>
            </template>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Header',
  data() {
    return {
    }
  },
  computed: {
    ...mapGetters([
      'isLogged',
      'loggedUserId',
      'role'
    ]),
  },
  watch: {

  },
  methods: {
    activePath(tab) {
      return this.$route.path.match(tab) ? 'active' : ''
    }
  },
  mounted() {
    let elem = document.querySelector('.dropdown-trigger');
    
    if (elem) {
      M.Dropdown.init(elem)
    } 
  },
  
}
</script>

<style>

.header nav .nav-wrapper {
  width: 70%;
  margin: 0 auto;
}
nav {
  background-color: #4CAF50 !important;
}
nav .brand-logo {
  font-weight: 900;
  font-size: 2.5rem;
}
</style>
