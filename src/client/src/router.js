import { createWebHistory, createRouter } from "vue-router";
const routes =  [
    {
        path: "/home",
        alias: "/home",
        name: "home",
        component: () => import("./components/Home")
    },
    {
        path: "/user/:id",
        name: "user-details",
        component: () => import("./components/UserList")
    },
    {
        path: "/groups",
        name: "group-details",
        component: () => import("./components/GroupList")
    }
];
const router = createRouter({
    history: createWebHistory(),
    routes,
});
export default router;
