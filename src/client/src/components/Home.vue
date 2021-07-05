<template>
  <div>
    <h1>Hello {{ user }}</h1>
    <UserList />
  </div>
</template>

<script>
import UserList from "@/components/UserList";
export default {
  name: "Home",
  components: {UserList},
  props: {
    user: String,
  }
}
</script>

<style scoped>

</style>