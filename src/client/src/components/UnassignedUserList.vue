<template>
  <div class="list row">
    <div class="col-md-12">
      <h4>Unassigned User List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(user, index) in users"
            :key="index"
            @click="setActiveUser(user, index)"
        >
          {{ user.firstName }} {{ user.lastName }}
        </li>
      </ul>

    </div>
  </div>
</template>

<script>
import UserDataService from "@/services/UserDataService";

export default {
  name: "UnassignedUserList",
  data() {
    return {
      users: [],
      currentUser: null,
      currentIndex: -1,
    };
  },
  methods: {
    retrieveUsers() {
      UserDataService.getUnassigned()
          .then(response => {
            this.users = response.data;
            this.setActiveUser(this.users[0], 0);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrieveUsers();
      this.currentUser = null;
      this.currentIndex = -1;
      this.$store.dispatch('updateUserSelection', null);
    },

    setActiveUser(user, index) {
      this.currentUser = user;
      this.currentIndex = user ? index : -1;
      this.$store.dispatch('updateUserSelection', user);
    },

  },
  mounted() {
    this.retrieveUsers();
    this.emitter.on("refresh-unassigned-users", () => {
      this.retrieveUsers();
    });
  },
}
</script>

<style scoped>

</style>