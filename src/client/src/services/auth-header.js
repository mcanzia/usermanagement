export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('authUser'));

    // If there is a logged in user with an access token it will return bearer
    // Else return nothing
    if (user && user.token) {
        return 'Bearer ' + user.token;
    } else {
        return '';
    }
}