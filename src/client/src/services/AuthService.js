import http from "../http-common";
class AuthService {

    register(data) {
        return http.post("/api/auth", data);
    }

    login(data) {
        return axios
            .post(API_URL + 'login', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }
}
export default new AuthService();