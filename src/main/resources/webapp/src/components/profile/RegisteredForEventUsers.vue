<template>
  <div class="registered-users reg-users-padding">
    <div class="users-list">
      <div v-for="data in registeredForEventUsers" :key="data.id" class="col m8 offset-m2">
        <div class="card horizontal">
          <div class="card-stacked">
            <div class="card-content users-list-card-content valign-wrapper row row-users-event">
              <div class="user-name col l3">
                <p>{{data.user.firstName}} {{data.user.lastName}}</p>
              </div>
              <div class="user-buttons col l9 right-align">
                <a @click = "showInfo(data)"><i class=" cursor material-icons black-text">visibility</i></a>
              </div>
            </div>
            <form-view v-if="data.showInfo" :info="data.info"></form-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import FormView from "../feed/events/FormView";
export default {
  name: 'RegisteredForEventUsers',
    computed: {
    ...mapGetters([
      'registeredForEventUsers'
    ])
  },
  methods: {
    ...mapActions([
      'getRegisteredForEventUsers'
    ]),
      showInfo(user) {
        user.showInfo =!user.showInfo;

      },
  },
    components: {
        FormView
    },
  created () {
    this.getRegisteredForEventUsers(this.$route.params.id)
  }
}

</script>

<style>
.cursor {
  cursor: pointer;
}
  .reg-users-padding {
    padding-top: 20px;
  }
  .row-users-event {
    margin-bottom: 0px !important;
  }
</style>
