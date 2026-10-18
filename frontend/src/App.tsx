import keycloak from "./components/keycloak.tsx";
import './App.css'
import {Welcome} from "./components/Welcome.tsx";
import {Home} from "./components/home/Home.tsx";

function App() {
  if (keycloak.authenticated) {
    return <Home></Home>
  } else {
    return <Welcome></Welcome>
  }
}

export default App
