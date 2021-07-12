<template>
  <div id="user-app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div v-if="currentUser" class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">Users</router-link>
        </li>
        <li v-if="isAdmin" class="nav-item">
          <router-link to="/groups" class="nav-link">Groups</router-link>
        </li>
        <li class="nav-item align-left">
          <button @click="logout" class="nav-link">Logout</button>
        </li>
      </div>
      <div v-if="!currentUser" class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">Register</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">Login</router-link>
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
      return this.$store.state.authLoggedIn;
    },
    isAdmin() {
      if (this.$store.state.authCurrentUser) {
        return this.$store.state.authCurrentUser.user.role === "ADMIN";
      }
      return false;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.push("/login");
    }
  }
}
</script>

<style scoped>

</style>