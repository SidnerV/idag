import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    url: "http://localhost:8180",
    realm: "idag",
    clientId: "idag-frontend",
});

export default keycloak;