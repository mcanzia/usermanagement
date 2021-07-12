<template>
  <div class="container">
    <Alert></Alert>
    <div class="row align-items-start">
      <div class="col-md-8">
        <h4>Group List</h4>
        <AddGroup></AddGroup>
        <GroupList></GroupList>
      </div>
      <div class="col-md-4">
        <UnassignedUserList></UnassignedUserList>
      </div>
    </div>
  </div>
</template>

<script>
import GroupList from "@/components/datalists/GroupList";
import AddGroup from "@/components/modals/AddGroup";
import UnassignedUserList from "@/components/datalists/UnassignedUserList";
import Alert from "@/components/main/Alert";
export default {
  name: "GroupMain",
  components: {GroupList, AddGroup, UnassignedUserList, Alert},
}
</script>

<style scoped>

</style>