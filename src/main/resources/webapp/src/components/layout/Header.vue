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
              <li><a class="dropdown-trigger" data-target="dropdown1">{{loggedUser.firstName}}<i class="material-icons right">arrow_drop_down</i></a></li>
              <li key="dropdown" class="dropdown-list">
                <ul id="dropdown1" class="dropdown-content">
                  <li style="height:65px"><a class="dropdown-trigger" style="height:65px"><i class="material-icons right"  style="padding-top:10px">arrow_drop_up</i></a></li>
                  <li><router-link :to="'/users/' + loggedUser.id" style="text-align:center">Profile</router-link></li>
                  <li><a @click="$store.dispatch('signOut')" style="text-align:center">Logout</a></li>
                </ul>
              </li>
            </template>
            <!-- <ul class="collapsible">
              <li>
                <div class="collapsible-header">My profile</div>
                <div class="collapsible-body">
                  <ul>
                    <router-link :to="'/users/' + loggedUser.id">My profile</router-link>
                    <a @click="$store.dispatch('signOut')">Logout</a>
                  </ul>
                </div>
              </li>
            </ul> -->
            <template v-else>
                <li key="login"><router-link to="/auth/login">Login</router-link></li>
                <li><router-link to="/auth/signup">Signup</router-link></li>
            </template>
            <li :class="activePath('/about')"><router-link to="/about">About</router-link></li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Header',
  data() {
    return {
    }
  },
  computed: {
    ...mapGetters([
      'isLogged',
      'loggedUser',
    ]),


  },
  watch: {

  },
  methods: {
      ...mapActions([ 'getLoggedUser' ]),
    activePath(tab) {
      return this.$route.path.match(tab) ? 'active' : ''
    },
  },
  mounted() {
    if(this.isLogged) this.getLoggedUser();
    let elem = document.querySelector('.dropdown-trigger');
    
    if (elem) {
      M.Dropdown.init(elem)
    } 
    console.log(this.loggedUser)
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
