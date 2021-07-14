<template>
  <Alert></Alert>
  <div class="card auth-card">
    <Form @submit="submitUserRegister" :validation-schema="registerSchema">
      <div class="card-body">
        <h5 class="card-title card-title-style">Register User</h5>
          <label for="registerEmailForm" class="form-label text-left">Email</label>
          <Field type="text" class="form-control" name="email" id="registerEmailForm" placeholder="Enter email here..." />
          <ErrorMessage name="email" class="error-text"/>
          <br />
          <label for="registerPasswordForm" class="form-label text-left">Password</label>
          <Field type="password" class="form-control" name="password" id="registerPasswordForm" placeholder="Enter password here..."/>
          <ErrorMessage name="password" class="error-text"/>
          <br />
          <label for="registerConfirmPasswordForm" class="form-label text-left">Confirm Password</label>
          <Field type="password" class="form-control" name="confirmPassword" id="registerConfirmPasswordForm" placeholder="Enter password here..."/>
          <ErrorMessage name="confirmPassword" class="error-text"/>
      </div>
      <div class="card-footer d-grid">
        <button type="submit" class="btn btn-primary btn-lg">Register</button>
      </div>
    </Form>
  </div>
</template>

<script>
import { Field, Form, ErrorMessage } from 'vee-validate';
import * as yup from 'yup';
import Alert from '@/components/main/Alert.vue';

export default {
  name: "Register",
  components: {
    Field,
    Form,
    ErrorMessage,
    Alert
  },
  data() {
    const registerSchema = yup.object({
      email: yup.string().required('Email required').email().max(40),
      password: yup.string().required('Password Required').min(8).max(40),
      confirmPassword: yup.string().required('Confirm Password Required')
          .test('passwords-match', 'Passwords must match', function(value){
            return this.parent.password === value
          })
    });
    return {
      registerSchema,
      successful: false,
      message: "",
    }
  },
  computed: {
    loggedIn() {
      return this.$store.authLoggedIn;
    }
  },
  methods: {
    submitUserRegister(registerUser) {
      console.log(registerUser);

      this.$store.dispatch('register', registerUser).then(
          (data) => {
            this.message = data.message;
            this.successful = true
            console.log('register success');
            this.emitter.emit("displayAlert", {
              type: 'alert-success',
              message: 'Successfully registered user! Please continue to login page.'
            });
            //this.$router.push("/login");
          },
          (error) => {
            this.message = error.toString();
            this.successful = false;
            console.log(this.message);
            this.emitter.emit("displayAlert", {
              type: 'alert-danger',
              message: 'Register failed'
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