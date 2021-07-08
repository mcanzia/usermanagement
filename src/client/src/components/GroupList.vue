<template>
  <div class="container">
    <!--<Alert alert-message="{{alertMessage}}" aria-hidden="true"></Alert>-->
    <div class="row align-items-start">
      <div class="col-md-8">
        <h4>Group List</h4>
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

        <div class="accordion" id="groupAccordion">
          <div class="accordion-item"
               v-for="(group, groupIndex) in groups"
               :key="groupIndex"
               @click="setActiveGroup(group, groupIndex)">
            <h2 class="accordion-header" id="groupHeading">
              <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="`#collapse${groupIndex}`" aria-expanded="false" aria-controls="collapseOne">
                {{ group.name }}
              </button>
            </h2>
            <div :id="`collapse${groupIndex}`" class="accordion-collapse collapse" aria-labelledby="groupHeading">
              <div class="accordion-body">
                <div class="list row">
                  <div class="col-md-6">
                    <ul class="list-group">
                      <li class="list-group-item"
                          :class="{ active: userIndex == currentUserIndex && groupIndex == currentGroupIndex }"
                          v-for="(user, userIndex) in filteredUsers(group.id)"
                          :key="userIndex"
                          @click="setActiveUser(user, userIndex)"
                      >
                        {{ user.firstName }} {{ user.lastName }}
                      </li>
                    </ul>
                  </div>
                </div>
                <br />
                <button type="button" class="btn btn-outline-primary" v-if="groupIndex == currentGroupIndex" @click="addUserToGroup(group.id)">
                  Add User to Group
                </button>
                <button type="button" class="btn btn-outline-danger ml-3" v-if="groupIndex == currentGroupIndex" @click="removeSelectedGroup">
                  Delete Current Group
                </button>
                <button type="button" class="btn btn-outline-danger ml-3" v-if="currentUserIndex != -1 && groupIndex == currentGroupIndex" @click="removeSelectedUser">
                  Remove Selected User from Group
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <UnassignedUserList></UnassignedUserList>
      </div>
    </div>
  </div>
</template>

<script>
import GroupDataService from "@/services/GroupDataService";
import UnassignedUserList from "@/components/UnassignedUserList";
import UserDataService from "@/services/UserDataService";

export default {
  name: "group-list",
  components: {UnassignedUserList},
  data() {
    return {
      groups: [],
      currentGroup: null,
      currentGroupIndex: -1,
      newGroupName: "",
      alertMessage: "",
      users: [],
      currentUser: null,
      currentUserIndex: -1,
    };
  },
  computed: {

  },
  methods: {
    retrieveGroups() {
      GroupDataService.getAll()
          .then(response => {
            this.groups = response.data;
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshGroupList() {
      this.retrieveGroups();
      this.currentGroup = null;
      this.currentGroupIndex = -1;
    },

    setActiveGroup(group, index) {
      this.currentGroup = group;
      this.currentGroupIndex = group ? index : -1;
    },

    removeSelectedGroup() {
      if (this.currentGroup != null) {
        /*
        const usersInGroup = this.filteredUsers(this.currentGroupIndex);
        for (var u in usersInGroup) {
          this.removeSelectedUser(u);
        }*/
        GroupDataService.delete(this.currentGroup.id)
            .then(response => {
              this.refreshGroupList();
              this.emitter.emit("refresh-unassigned-users", "");
              console.log(response.data);
            })
            .catch(e => {
              console.log(e);
            });
      }
    },

    addNewGroup(groupName) {
      const newGroupData = {
        name: groupName,
      };
      GroupDataService.create(newGroupData)
          .then(response => {
            //this.group.id = response.data.id;
            this.alertMessage = "Group added successfully!";
            this.refreshGroupList();
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },


    retrieveUsersByGroup() {
      UserDataService.getAll()
          .then(response => {
            this.users = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshUserList() {
      this.retrieveUsersByGroup();
      this.currentUser = null;
      this.currentUserIndex = -1;
    },

    setActiveUser(user, index) {
      this.currentUser = user;
      this.currentUserIndex = user ? index : -1;
    },

    removeSelectedUser() {
      if (this.currentUser != null && this.currentGroup != null) {
        console.log(this.currentUser.id);

        const updateUser = {
          id: this.currentUser.id,
          firstName: this.currentUser.firstName,
          lastName: this.currentUser.lastName,
          role: this.currentUser.role,
          email: this.currentUser.email,
          groupId: -1
        };
        UserDataService.update(this.currentUser.id, updateUser)
            .then(response => {
              console.log(response.data);
              this.refreshUserList();
              this.emitter.emit("refresh-unassigned-users", "");
            })
            .catch(e => {
              console.log(e);
            });
      }
    },

    filteredUsers(groupId) {
      const filtered = this.users.reduce((a, u) => (u.groupId === groupId && a.push(u), a), []);
      return filtered;
    },

    addUserToGroup(groupId) {
      if (this.$store.state.selectedUnassignedUser != null) {
        const updateUser = {
          id: this.$store.state.selectedUnassignedUser.id,
          firstName: this.$store.state.selectedUnassignedUser.firstName,
          lastName: this.$store.state.selectedUnassignedUser.lastName,
          role: this.$store.state.selectedUnassignedUser.role,
          email: this.$store.state.selectedUnassignedUser.email,
          groupId: groupId
        };
        UserDataService.update(this.$store.state.selectedUnassignedUser.id, updateUser)
            .then(response => {
              console.log(response.data);
              this.refreshUserList();
              this.$store.dispatch('updateUserSelection', null);
              this.emitter.emit("refresh-unassigned-users", "");
            })
            .catch(e => {
              console.log(e);
            });
      }
    }
  },
  mounted() {
    this.retrieveGroups();
    this.retrieveUsersByGroup();
  }
}
</script>

<style scoped>

</style>