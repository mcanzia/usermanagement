import http from "./http-common";
class GroupDataService {
    getAll() {
        return http.get("/groups");
    }
    get(id) {
        return http.get(`/groups/${id}`);
    }
    create(data) {
        return http.post("/groups", data);
    }
    update(id, data) {
        return http.put(`/groups/${id}`, data);
    }
    delete(id) {
        return http.delete(`/groups/${id}`);
    }

    deleteAll() {
        return http.delete(`/groups`);
    }
    findByName(name) {
        return http.get(`/groups?name=${name}`);
    }
}
export default new GroupDataService();