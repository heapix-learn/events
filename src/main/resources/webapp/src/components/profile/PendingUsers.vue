<template>
<div class="users-list">
    <div v-for="user in users" :key="user.id" class="col m8 offset-m2">
      <div class="card horizontal">
        <div class="card-stacked">
          <div class="card-content users-list-card-content valign-wrapper row">
            <div class="user-name col l3">
              <p>{{user.firstName}} {{user.lastName}}</p>
            </div>
            <div class="user-buttons col l9 right-align">
              <router-link :to="'users/' + user.id"><i class="material-icons black-text">visibility</i></router-link>
              <router-link :to="'users/edit/' + user.id"><i class="material-icons black-text">create</i></router-link>
            </div>
          </div>
        </div>
      </div>
  </div>
</div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'PendingUsers',
  data () {
    return {
      users: this.getPendingUsers()
    }
  },
  methods: {
    ...mapActions([
      'getPendingUsers'
    ])
  }
}
</script>

<style>
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
