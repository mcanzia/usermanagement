export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('authUser'));

    // If there is a logged in user with an access token it will return bearer
    // Else return nothing
    if (user && user.accessToken) {
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        return {};
    }
}