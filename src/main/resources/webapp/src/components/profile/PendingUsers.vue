<template>
<div class="users-list">
    <div v-for="user in pendingUsers" :key="user.id" class="col m8 offset-m2">
      <div class="card horizontal">
        <div class="card-stacked">
          <div class="card-content users-list-card-content valign-wrapper row">
            <div class="user-name col l3">
              <p>{{user.firstName}} {{user.lastName}}</p>
            </div>
            <div class="user-buttons col l9 right-align">
              <router-link :to="'users/' + user.id"><i class="material-icons black-text">visibility</i></router-link>
              <router-link :to="'users/edit/' + user.id"><i class="material-icons black-text">create</i></router-link>
              <a @click.prevent="acceptUser(user.id)"><i class="material-icons black-text">check</i></a>
            </div>
          </div>
        </div>
      </div>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'PendingUsers',
  data () {
    return {
    }
  },
  methods: {
    ...mapActions([
      'getPendingUsers',
      'registerUser'
    ]),
    acceptUser(id) {
      this.registerUser(id)
        .then(res => this.getPendingUsers())
    }
  },
  computed: {
    ...mapGetters([
      'pendingUsers'
    ])
  },
  created () {
    this.getPendingUsers()
  }
}
</script>

<style>
i {
  cursor: pointer;
}
.users-list {
  padding-top: 25px;
}
.users-list-card-content {
  height: 40px;
  margin-bottom: 0;
}
.user-buttons a:not(:last-child) {
  padding-right: 10px;
}
.user-buttons a i:hover {
  color:#4caf50 !important;
}
</style>
