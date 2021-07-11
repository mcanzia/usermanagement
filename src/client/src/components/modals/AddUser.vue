<template>
  <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#addUserModal">
    Add New User
  </button>
  <br /><br />
  <!-- Modal -->
  <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addUserModalLabel">New User</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <label for="userFirstNameForm" class="form-label text-left">First Name</label>
          <input type="text" class="form-control" id="userFirstNameForm" v-model="newUser.firstName" placeholder="Enter First Name here..">
          <label for="userLastNameForm" class="form-label text-left">Last Name</label>
          <input type="text" class="form-control" id="userLastNameForm" v-model="newUser.lastName" placeholder="Enter Last Name here..">
          <label for="userEmailForm" class="form-label text-left">Email</label>
          <input type="text" class="form-control" id="userEmailForm" v-model="newUser.email" placeholder="name@example.com">
          <label for="roleOptions" class="form-label text-left">Role</label>
          <select class="form-select" id="roleOptions" v-model="newUser.role">
            <option value="ADMIN">Admin</option>
            <option value="BASIC">Basic</option>
          </select>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="addNewUser" data-bs-dismiss="modal">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserDataService from "@/services/UserDataService";

export default {
  name: "AddUser",
  data() {
    return {
      newUser: {},
    }
  },
  methods: {
    addNewUser() {
      if (this.newUser != null) {
        const createUser = {
          firstName: this.newUser.firstName,
          lastName: this.newUser.lastName,
          email: this.newUser.email,
          role: this.newUser.role,
          groupId: -1,
          groupName: null
        }

        UserDataService.create(createUser)
            .then(response => {
              console.log(response.data);
              this.newUser = {};
              // Refresh list on UserList component
              this.emitter.emit("refresh-userlist-users", "");
              this.emitter.emit("displayAlert", {
                type: 'alert-success',
                message: 'Successfully created user'
              });
            })
            .catch(e => {
              console.log(e);
              this.newUser = {};
              this.emitter.emit("displayAlert", {
                type: 'alert-danger',
                message: 'Failed to create user'
              });
            });
      }
    }
  }
}
</script>

<style scoped>

</style>