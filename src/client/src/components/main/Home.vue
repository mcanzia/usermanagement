<template>
  <div>
    <Alert></Alert>
    <h1>Hello {{ loggedInUsername }}</h1>
    <hr>
    <UserList />
  </div>
</template>

<script>
import UserList from "@/components/datalists/UserList";
import Alert from "@/components/main/Alert";
import authHeader from "@/services/auth-header";
export default {
  name: "Home",
  components: {Alert, UserList},
  computed: {
    loggedInUsername() {
      return this.$store.state.authCurrentUser ? this.$store.state.authCurrentUser.user.firstName : "User";
    },
  },
  methods: {
  },
  mounted() {
    console.log('authheader: ' + authHeader());
  }
}
</script>

<style scoped>

</style>