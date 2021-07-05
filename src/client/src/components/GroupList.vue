<template>
 <div class="list row">
   <div class="col-md-6">
     <h4>Groups</h4>
     <ul class="list-group">
       <li class="list-group-item"
           :class="{ active: index == currentIndex }"
           v-for="(group, index) in groups"
           :key="index"
           @click="setActiveGroup(group, index)"
       >
         {{ group.name }}
       </li>
     </ul>
   </div>
 </div>
</template>

<script>
import GroupDataService from "@/services/GroupDataService";

export default {
  name: "group-list",
  data() {
    return {
      groups: [],
      currentGroup: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrieveGroups() {
      GroupDataService.getAll()
          .then(response => {
            this.groups = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrieveGroups();
      this.currentGroup = null;
      this.currentIndex = -1;
    },

    setActiveGroup(group, index) {
      this.currentGroup = group;
      this.currentIndex = group ? index : -1;
    },

    removeSelectedGroup() {
      if (this.currentGroup != null) {
        console.log(this.currentGroup.id);
        GroupDataService.delete(this.currentGroup.id)
            .then(response => {
              console.log(response.data);
              this.refreshList();
            })
            .catch(e => {
              console.log(e);
            });
      }
    }
  },
  mounted() {
    this.retrieveGroups();
  }
}
</script>

<style scoped>

</style>