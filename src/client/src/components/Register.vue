<template>
  <div class="card auth-card">
    <div class="card-body">
      <h5 class="card-title card-title-style">Register User</h5>
      <label for="registerEmailForm" class="form-label text-left">Email</label>
      <input type="text" class="form-control" id="registerEmailForm" v-model="registerUser.email" placeholder="Enter Email here..">
      <label for="registerPasswordForm" class="form-label text-left">Password</label>
      <input type="text" class="form-control" id="registerPasswordForm" v-model="registerUser.password">
      <label for="registerConfirmPasswordForm" class="form-label text-left">Confirm Password</label>
      <input type="text" class="form-control" id="registerConfirmPasswordForm" v-model="confirmPassword">
    </div>
    <div class="card-footer d-grid">
      <button type="button" class="btn btn-primary btn-lg" @click="submitUserRegister">Register</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      registerUser: {},
      confirmPassword: ""
    }
  },
  methods: {
    submitUserRegister() {
      console.log(this.registerUser)
    }
  }
}
</script>

<style scoped>
  .auth-card {
    margin: auto;
    width: 500px;
  }

  .card-title-style {
    text-align: center;
  }

  .btn-style {
    width: 400px;

  }
</style>