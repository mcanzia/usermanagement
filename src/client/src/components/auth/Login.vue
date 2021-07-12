<template>
  <Alert></Alert>
  <div class="card auth-card">
    <Form @submit="submitUserLogin" :validation-schema="loginSchema">
      <div class="card-body">
        <h5 class="card-title card-title-style">Login User</h5>
        <label for="registerEmailForm" class="form-label text-left">Email</label>
        <Field type="text" class="form-control" name="email" id="registerEmailForm" placeholder="Enter Email here.." />
        <ErrorMessage name="email" class="error-text"/>
        <br />
        <label for="registerPasswordForm" class="form-label text-left">Password</label>
        <Field type="password" class="form-control" name="password" id="registerPasswordForm" />
        <ErrorMessage name="password" class="error-text"/>
      </div>
      <div class="card-footer d-grid">
        <button type="submit" class="btn btn-primary btn-lg">Login</button>
      </div>
    </Form>
  </div>
</template>

<script>
import { Field, Form, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import Alert from "@/components/main/Alert";

export default {
  name: "Login",
  components: {
    Alert,
    Field,
    Form,
    ErrorMessage,
  },
  data() {
    const loginSchema = yup.object({
      email: yup.string().required('Email required').email(),
      password: yup.string().required('Password Required')
    });
    return {
      loginSchema,
      successful: false,
      message: "",
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.authLoggedIn;
    }
  },
  methods: {
    submitUserLogin(loginUser) {
      this.$store.dispatch('login', loginUser).then(
          (data) => {
            this.message = data.message;
            this.successful = true
            this.$router.push("/home");
          },
          (error) => {
            this.message = error.toString();
            this.successful = false;
            console.log(this.message);
            this.emitter.emit("displayAlert", {
              type: 'alert-danger',
              message: 'Login failed'
            });
          });

    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/home");
    }
  },
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

.error-text {
  font-size: 15px;
  color: red;
}
</style>