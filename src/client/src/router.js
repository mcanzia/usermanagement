import { createWebHistory, createRouter } from "vue-router";
import store from "./store.js"

const routes =  [
    {
        path: "/home",
        alias: "/home",
        name: "home",
        component: () => import("./components/main/Home"),
        meta: { requiresAuth: true }
    },
    {
        path: "/groups",
        name: "group-main",
        component: () => import("./components/main/GroupMain"),
        meta: { requiresAuth: true, requiresAdmin: true }
    },
    {
        path: "/register",
        name: "register",
        component: () => import("./components/auth/Register")
    },
    {
        path: "/login",
        name: "login",
        component: () => import("./components/auth/Login")
    }
];
const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!store.state.authLoggedIn) {
            next({
                name: "login"
            });
        } else {
            if (to.matched.some(record => record.meta.requiresAdmin)) {
                if(store.state.authCurrentUser.user.role !== "ADMIN") {
                    next({
                        name: "home"
                    });
                } else {
                    next();
                }
            } else {
                next();
            }
        }
    } else {
        next();
    }
});


export default router;
