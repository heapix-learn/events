<template>
  <div class="auth-view row">
    <div class="left-side-auth col s6">
      <router-view></router-view>
    </div>
    <div class="right-side-auth col s6">
      <div class="oblique"></div>
      <router-link to="../" class="auth-redirect light-green-text text-accent-2">LO MARRACO</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AuthView'
}
</script>

<style>
.auth-view {
  width: 100%;
  height: 100%;
  background-color: #eee;
}
.auth-view.row {
  margin: 0;
}

.left-side-auth, .right-side-auth {
  height: 100%;
  width: 50%;
  padding: 0;
  margin: 0;
  position: relative;
}
.left-side-auth {
  background-color: white;
}
.right-side-auth {
  background-color: #333;
  overflow: hidden;
}
.oblique {
  background-color: #282828;
  position: absolute;
  left: 50%;
  top: -50px;
  transform: translate(-50%) rotate(15deg);
  height: 120%;
  width: 25%;
}
.auth-redirect {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  font-size: 4em;
  font-weight: 900;
}
/* label focus color */
.input-field input:focus + label {
  color: #4CAF50 !important;
}
/* label underline focus color */
.input-field input:focus {
  border-bottom: 1px solid #4CAF50 !important;
}
</style>
