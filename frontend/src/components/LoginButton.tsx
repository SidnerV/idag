import keycloak from "./keycloak";

export function LoginButton() {
    return (
        <button onClick={() => keycloak.login()}>
            Log in
        </button>
    );
}

// add to a logout window:
//keycloak.logout({
//    redirectUri: window.location.origin,
//});
