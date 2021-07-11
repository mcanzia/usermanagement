<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>User List</h4>
      <AddUser></AddUser>
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
    <br />
    <div class="col-md-6">
      <div v-if="currentUser">
        <h4>User</h4>
        <div>
          <label><strong>First Name:</strong></label> {{ currentUser.firstName }}
        </div>
        <div>
          <label><strong>Last Name:</strong></label> {{ currentUser.lastName }}
        </div>
        <div>
          <label><strong>Email:</strong></label> {{ currentUser.email }}
        </div>
        <div>
          <label><strong>Role:</strong></label> {{ currentUser.role }}
        </div>
        <div>
          <label><strong>Group Name:</strong></label> {{ currentUser.groupName }}
        </div>
        <br />
        <div>
          <button class="btn btn-sm btn-danger" @click="removeSelectedUser">
            Remove Selected User
          </button>
        </div>
        <!--<router-link :to="'/products/' + currentProduct.id" class="badge badge-warning">Edit</router-link>-->
      </div>
      <div v-else>
        <br />
        <p>Please click on a User...</p>
      </div>
    </div>
  </div>
</template>

<script>
import UserDataService from "@/services/UserDataService";
import AddUser from "@/components/modals/AddUser";

export default {
  name: "user-list",
  components: {AddUser},
  data() {
    return {
      users: [],
      currentUser: null,
      currentIndex: -1,
    };
  },
  methods: {
    retrieveUsers() {
      UserDataService.getAll()
          .then(response => {
            this.users = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
            this.emitter.emit("displayAlert", {
              type: 'alert-danger',
              message: 'Failed to retrieve users'
            });
          });
    },

    refreshList() {
      this.retrieveUsers();
      this.currentUser = null;
      this.currentIndex = -1;
    },

    setActiveUser(user, index) {
      this.currentUser = user;
      this.currentIndex = user ? index : -1;
    },

    removeSelectedUser() {
      if (this.currentUser != null) {
        console.log(this.currentUser.id);
        UserDataService.delete(this.currentUser.id)
            .then(response => {
              console.log(response.data);
              this.refreshList();
              this.emitter.emit("displayAlert", {
                type: 'alert-success',
                message: 'Successfully removed user'
              });
            })
            .catch(e => {
              console.log(e);
              this.emitter.emit("displayAlert", {
                type: 'alert-danger',
                message: 'Failed to remove user'
              });
            });
      }
    },

  },
  mounted() {
    this.retrieveUsers();
    this.emitter.on("refresh-userlist-users", () => {
      this.retrieveUsers();
    });
  }
}
</script>

<style scoped>

</style>