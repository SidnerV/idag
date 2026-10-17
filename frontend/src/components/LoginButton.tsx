import keycloak from "./keycloak";

export function LoginButton() {
    return (
        <button onClick={() => keycloak.login()}>
            Log in
        </button>
    );
}
