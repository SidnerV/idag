import keycloak from "../keycloak.tsx";

export function LogoutButton() {
    return (
        <button onClick={() => keycloak.logout({
            redirectUri: window.location.origin,
        })}>
            Log out
        </button>
    );
}