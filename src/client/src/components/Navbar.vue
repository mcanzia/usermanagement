<template>
  <div id="user-app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div v-if="currentUser" class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">Users</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/groups" class="nav-link">Groups</router-link>
        </li>
      </div>
    </nav>
    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  computed: {
    currentUser() {
      return true;
      //return this.$store.state.auth.user;
    }
  },
}
</script>

<style scoped>

</style>