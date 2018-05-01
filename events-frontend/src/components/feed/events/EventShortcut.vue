<template>
  <div class="event-shortcut container">
    <div class="row">
      <div class="col s12">
        <div class="card white darken-1">
          <div class="card-content black-text">
            <span class="card-title">{{event.title}}</span>
            <p>Where: {{event.location}}</p>
            <p>When: {{event.date}} {{event.time}}</p>
          </div>
          <div class="card-action">
            <router-link class="green-text" :to="'/events/' + event.id">More</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'EventShortcut',
  props: ['event'],

}
</script>

<style>

</style>
