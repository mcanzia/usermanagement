<template>
  <div v-if="showAlert" :class="['alert', className]" role="alert" id="alert">
    <button type="button" @click="closeAlert" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    {{alertMessage}}
  </div>
</template>

<script>
export default {
  name: "Alert",
  data() {
    return {
      showAlert: false,
      alertMessage: "",
      className: "",
    }
  },
  methods: {
    closeAlert() {
      this.showAlert = false;
    }
  },
  mounted() {
    this.emitter.on('displayAlert', (data) => {
      this.showAlert = true;
      this.alertMessage = data.message;
      this.className = data.type;
    });
  }
}
</script>

<style scoped>

</style>