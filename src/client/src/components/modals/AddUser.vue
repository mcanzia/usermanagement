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
        <Form @submit="addNewUser" :validation-schema="newUserSchema">
          <div class="modal-body">
            <label for="userFirstNameForm" class="form-label text-left">First Name</label>
            <Field type="text" class="form-control" name="firstName" id="userFirstNameForm" placeholder="Enter First Name here.." />
            <ErrorMessage name="firstName" class="error-text"/>
            <br />
            <label for="userLastNameForm" class="form-label text-left">Last Name</label>
            <Field type="text" class="form-control" name="lastName" id="userLastNameForm" placeholder="Enter Last Name here.." />
            <ErrorMessage name="lastName" class="error-text"/>
            <br />
            <label for="userEmailForm" class="form-label text-left">Email</label>
            <Field type="text" class="form-control" name="email" id="userEmailForm" placeholder="name@example.com" />
            <ErrorMessage name="email" class="error-text"/>
            <br />
            <label for="roleOptions" class="form-label text-left">Role</label>
            <select class="form-select" name="role" id="roleOptions" v-model="userRole">
              <option v-if="isAdmin" value="ADMIN">Admin</option>
              <option value="BASIC">Basic</option>
            </select>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-outline-primary" @click="addNewUser" data-bs-dismiss="modal">Save changes</button>
          </div>
        </Form>
      </div>
    </div>
  </div>
</template>

<script>
import UserDataService from "@/services/UserDataService";
import { Field, Form, ErrorMessage } from 'vee-validate';
import * as yup from "yup";

export default {
  name: "AddUser",
  components: {
    Field,
    Form,
    ErrorMessage
  },
  data() {
    let newUserSchema = yup.object({
      firstName: yup.string().required('First Name required').max(100),
      lastName: yup.string().required('Last Name required').max(100),
      email: yup.string().required('Email required').email().max(100),
    });
    return {
      newUserSchema,
      userRole: "",
    }
  },
  computed: {
    isAdmin() {
      if (this.$store.state.authCurrentUser) {
        return this.$store.state.authCurrentUser.user.role === "ADMIN";
      }
      return false;
    }
  },
  methods: {
    addNewUser(newUser) {
      if (newUser != null && this.userRole != null && this.userRole != '') {
        const createUser = {
          firstName: newUser.firstName,
          lastName: newUser.lastName,
          email: newUser.email,
          role: this.userRole,
          groupId: -1,
          groupName: null
        }

        UserDataService.create(createUser)
            .then(response => {
              console.log(response.data);
              this.resetUserSchema();
              // Refresh list on UserList component
              this.emitter.emit("refresh-userlist-users", "");
              this.emitter.emit("displayAlert", {
                type: 'alert-success',
                message: 'Successfully created user'
              });
            })
            .catch(e => {
              console.log(e);
              this.resetUserSchema();
              this.emitter.emit("displayAlert", {
                type: 'alert-danger',
                message: 'Failed to create user'
              });
            });
      } else {
        this.emitter.emit("displayAlert", {
          type: 'alert-danger',
          message: 'Failed to create user. One of the fields was empty.'
        });
      }
    },
    resetUserSchema() {
      this.newUserSchema.firstName = '';
      this.newUserSchema.lastName = '';
      this.newUserSchema.email = '';
      this.userRole = '';

    }
  }
}
</script>

<style scoped>
  .error-text {
    font-size: 15px;
    color: red;
  }
</style>