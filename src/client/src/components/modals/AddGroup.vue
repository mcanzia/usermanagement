<template>
  <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#addGroupModal">
    Add New Group
  </button>
  <br /><br />
  <!-- Modal -->
  <div class="modal fade" id="addGroupModal" tabindex="-1" aria-labelledby="addGroupModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addGroupModalLabel">New Group</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <label for="groupNameForm" class="form-label text-left">Group Name</label>
          <input type="text" class="form-control" id="groupNameForm" v-model="newGroupName" placeholder="Enter group name here..">

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="addNewGroup(newGroupName)" data-bs-dismiss="modal">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GroupDataService from "@/services/GroupDataService";

export default {
  name: "AddGroup",
  data() {
    return{
      newGroupName: "",
    }
  },
  methods: {
    addNewGroup(groupName) {
      const newGroupData = {
        name: groupName,
      };
      GroupDataService.create(newGroupData)
          .then(response => {
            //this.group.id = response.data.id;
            this.alertMessage = "Group added successfully!";
            // Refresh list on GroupList component
            this.emitter.emit("refresh-grouplist-groups", "");
            console.log(response.data);
            this.emitter.emit("displayAlert", {
              type: 'alert-success',
              message: 'Successfully added group'
            });
          })
          .catch(e => {
            console.log(e);
            this.emitter.emit("displayAlert", {
              type: 'alert-danger',
              message: 'Failed to add new group'
            });
          });
    },
  }
}
</script>

<style scoped>

</style>