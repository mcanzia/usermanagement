<template>
  <div>
    <Alert></Alert>
    <h1>Hello {{ loggedInUsername }}</h1>
    <UserList />
  </div>
</template>

<script>
import UserList from "@/components/datalists/UserList";
import Alert from "@/components/main/Alert";
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
  }
}
</script>

<style scoped>

</style>