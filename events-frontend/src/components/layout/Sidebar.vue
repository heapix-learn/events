<template>
  <ul class="collapsible sidebar">
    <li>
      <div class="collapsible-header green white-text"><i class="material-icons">message</i>News</div>
      <div class="collapsible-body sb-menu-item">
        <router-link to="/news/create">
          <button class="btn white black-text waves-effect waves-light sb-inner-btn" type="submit" name="action">
            <i class="material-icons left">add_box</i>
            Add news
          </button>
        </router-link>
      </div>
    </li>
    <li>
      <div class="collapsible-header green white-text"><i class="material-icons">place</i>Events</div>
      <div class="collapsible-body sb-menu-item">
        <router-link to="/events/create">
          <button class="btn white black-text waves-effect waves-light sb-inner-btn" type="submit" name="action">
            <i class="material-icons left">pin_drop</i>
            New event
          </button>
        </router-link>
      </div>
    </li>
    <li>
      <div class="collapsible-header green white-text"><i class="material-icons">person</i>Users</div>
      <div class="collapsible-body sb-menu-item">
        <router-link to="/createuser">
          <button class="btn white black-text waves-effect waves-light sb-inner-btn" type="submit" name="action">
            <i class="material-icons left">person_add</i>
            New user
          </button>
        </router-link>
        <router-link to="/users">
          <button class="btn white black-text waves-effect waves-light sb-inner-btn" type="submit" name="action">
            <i class="material-icons left">people</i>
            All users
          </button>
        </router-link>
      </div>
    </li>

  </ul>      
</template>

<script>
export default {
  name: 'Sidebar',
  mounted() {
    M.Collapsible.init(document.querySelector('.collapsible'));
  }
}
</script>

<style>
  .sidebar {
    margin-top: 25px;
  }
  .active-sidebar {
    background-color: #4caf50;
    color: white;
  }
  .sb-menu-item {
    padding: 0;
  }
  .sb-inner-btn {
    width: 100%;
    text-align: left;
    height: 55px;
    padding-left: 22px;
    text-transform: none;
    box-shadow: none;
    font-size: 15px;
  }
  .sb-inner-btn i {
    margin-right: 19px;
  }
  .sb-inner-btn:hover, .sb-menu-item {
    box-shadow: none;
  }
  a {
    color: black;
  }
</style>
