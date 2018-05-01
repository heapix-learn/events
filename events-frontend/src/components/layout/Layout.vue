<template>
  <div class="layout">
    <Header />
    <div class="layout-main">
      <div class="row">
        <div class="col s2">
          <sidebar v-if="isAllowed"></sidebar>
        </div>
        <div class="col s8 main-content">
          <slot></slot>
        </div>
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import Header from './Header'
import Footer from './Footer'
import Sidebar from './Sidebar'
import { mapGetters } from 'vuex';

export default {
  name: 'Layout',
  components: {
    Header,
    Footer,
    Sidebar
  },
  computed: {
    ...mapGetters([
      'isLogged',
      'role',
    ]),
    isAllowed() {
      return this.isLogged && this.role === 'administrator'
    }
  },
  mounted() {
    M.Collapsible.init(document.querySelector('.collapsible'));
  }
}
</script>

<style>
.layout-main {
  height: calc(100vh - 128px);
}
.main-content, .row {
  height: 100%;
}

</style>
