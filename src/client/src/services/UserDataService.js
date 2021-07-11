import http from "./http-common";
class UserDataService {
    getAll() {
        return http.get("/users");
    }
    getUnassigned() {
        return http.get("/users/unassigned")
    }
    get(id) {
        return http.get(`/users/${id}`);
    }
    create(data) {
        console.log(data);
        return http.post("/users", data);
    }
    update(id, data) {
        return http.put(`/users/${id}`, data);
    }
    delete(id) {
        return http.delete(`/users/${id}`);
    }

    deleteAll() {
        return http.delete(`/users`);
    }
    findByName(name) {
        return http.get(`/users?name=${name}`);
    }
}
export default new UserDataService();