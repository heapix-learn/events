<template>
  <div class="user">
    <div class="profile">
      <div class="col s12">
        <div v-if="currentUser.email" class="card-panel grey lighten-5 z-depth-1">

            <a @click="$router.push('/users/edit/' + $route.params.id)" 
              class="waves-effect waves grey lighten-5 black-text btn edit-button eb-edit z-depth-1">
              <i class="material-icons left">mode_edit</i>
              Edit
            </a>
            <a v-if="isMyProfle" @click="$router.push('/users/edit/changepassword')" 
              class="waves-effect waves grey lighten-5 black-text btn edit-button eb-password z-depth-2">
              Change password
            </a>
            <div class="row center">
              <h3>
                {{currentUser.firstName}} {{currentUser.lastName}}
              </h3> 
              <h5>{{currentUser.firstPhone}} {{currentUser.lastPhone}}</h5>
              <h5>{{currentUser.email}}</h5>
            </div>

        </div>
          <div v-else class="card-panel grey lighten-5 z-depth-1">
              <h3>
                 Sorry, you account has to be validated by moderator
              </h3>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Modal from '../utils/Modal.vue';

export default {
  name: 'UserView',
  data () {
    return {
      isMyProfle: this.$route.params.id === this.loggedUserId,
    }
  },
  methods: {
    ...mapActions([
      'getUserById',
      'clearCurrentUser'
    ]),
  },
  computed: {
    ...mapGetters([
      'currentUser',
      'loggedUserId'
    ])
  },
  components: {
    Modal
  },
  mounted() {
    this.getUserById(this.$route.params.id)
  },
  beforeRouteLeave (to, from, next) {
    this.clearCurrentUser()
    next()
  }
}
</script>

<style>
.profile {
  height: 100%;
}
.profile .col.s12 {
  margin-top: 18px;
}
.card-panel {
  position: relative;
}
.edit-button {
  position: absolute;
  width: 100px;
}
.eb-edit, .eb-save, .eb-cancel {
  top: 10px;
  right: 10px;
}
.eb-cancel {
  top: 50px;
}
.eb-unsub {
  bottom: 10px;
  right: 10px;
  width: 130px;
}
.eb-password {
  top: 50px;
  right: 10px;
  width: auto;
}
.edit-form {
  width: 100%;
}
.required-field::after {
  content: " *";
  color: red;
  font-size: 0.8em;
}

</style>
