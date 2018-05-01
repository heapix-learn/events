<template>
  <div id="app">
    <auth-view v-if="isAuth"></auth-view>
    <layout v-else>
      <router-view></router-view>
    </layout>
  </div>
</template>

<script>
import AuthView from './components/auth/AuthView.vue'
import Layout from './components/layout/Layout.vue'

export default {
  name: 'App',
  components: {
    AuthView,
    Layout
  },
  computed: {
      isAuth () {
        return this.$route.path.startsWith( '/auth')
      }
  },
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
*:focus {
    outline: none;
}
body {
  font-family: 'Open Sans', sans-serif;
}
#app, body {
  width: 100%;
  height: 100vh;
}
</style>

