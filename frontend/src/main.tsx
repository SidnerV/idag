import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import keycloak from './components/keycloak.tsx';

async function startApp() {
    const authenticated = await keycloak.init({
        onLoad: "check-sso",
    });

    createRoot(document.getElementById('root')!).render(
        <StrictMode>
            <App />
        </StrictMode>,
    )
}

startApp().catch(console.error);
