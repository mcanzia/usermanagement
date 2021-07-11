import axios from "axios";

const BASE_URL = "http://localhost:8080"

class AuthService {

    register(data) {
        return axios.post(BASE_URL + "/api/auth/register", data);
    }

    login(data) {
        return axios.post(BASE_URL + '/api/auth/login', data)
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('authUser', JSON.stringify(response.data));
                    console.log('localstorage: ' + JSON.parse(localStorage.getItem('authUser')));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('authUser');
    }
}
export default new AuthService();