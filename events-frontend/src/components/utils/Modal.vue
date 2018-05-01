<template>
  <div :id="modalId" class="modal">
    <div class="modal-content center">
      <p>{{title}}</p>
      <a @click="$emit('confirm')" class="modal-action modal-close waves-effect waves-light green btn">{{confirm}}</a>
      <a @click="$emit('cancel')" class="modal-action modal-close waves-effect waves-light red btn">{{cancel}}</a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Modal',
  data () {
    return {
    }
  },
  props: {
    title: {
      type: String,
    },
    confirm: {
      type: String,
      default: 'Confirm',
    },
    cancel: {
      type: String,
      default: 'Cancel',
    },
    modalId: {
      type: String,
      default: 'modal',
    }
  },
}
</script>

<style>
.modal {
  width: 400px;
}
.modal-content p {
  font-size: 1.3em;
  font-weight: bold;
}
.modal-content a {
  margin: 10px;
}
</style>
