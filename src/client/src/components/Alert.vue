<template>
  <div class="alert alert-success" role="alert" id="alert">
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    {{alertMessage}}
  </div>
</template>

<script>
export default {
  name: "Alert",
  data() {
    return {

    }
  },
  props: {
    alertMessage: String,
  }
}
</script>

<style scoped>

</style>