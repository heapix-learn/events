<template>
  <div class="user">
    <div class="profile">
      <div class="col s12">
        <div class="card-panel grey lighten-5 z-depth-1">
            <div v-if="hasEditRights">
            <a @click="$router.push('/users/edit/' + $route.params.id)" 
              class="waves-effect waves grey lighten-5 black-text btn edit-button eb-edit z-depth-1">
              <i class="material-icons left">mode_edit</i>
              Edit
            </a>
            <a v-if="isMyProfile" @click="$router.push('/users/edit/changepassword')"
              class="waves-effect waves grey lighten-5 black-text btn edit-button eb-password z-depth-2">
              Change password
            </a>
            </div>
            <div class="row center">
              <h3>
                {{currentUser.firstName}} {{currentUser.lastName}}
              </h3> 
              <h5>{{currentUser.firstPhone}} {{currentUser.lastPhone}}</h5>
              <h5>{{currentUser.email}}</h5>
              <h5 v-if="loggedUserRole <= 2">{{currentUser.annotations}}</h5>
              <div v-for="(input, index) in inputs" :key="index">
                <h5 v-for="(value, key) in input" :key="key">
                  {{key}}: {{value}}
                </h5>
              </div>
            </div> 
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
      isMyProfile: false,
      hasEditRights: false,
    }
  },
  methods: {
    ...mapActions([
      'getUserById',
      'getUserByIdAdmin',
      'clearCurrentUser'
    ]),
  showButtons() {
      this.isMyProfile = parseInt(this.$route.params.id) === this.loggedUser.id;
      this.hasEditRights = this.isMyProfile || this.loggedUser.role < 3;
  }
  },
  computed: {
    ...mapGetters([
      'currentUser',
      'loggedUser',
      'loggedUserRole',
    ]),
    inputs () {
      if (this.currentUser.inputs) {
        return JSON.parse(this.currentUser.inputs)
      } else {
        return null
      }
    }
  },
  components: {
    Modal
  },
  mounted() {
    this.getUserById(this.$route.params.id);
    this.showButtons();
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
