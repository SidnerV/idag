import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import keycloak from './keycloak';

async function startApp() {
    const authenticated = await keycloak.init({
        onLoad: "login-required",
    });

    createRoot(document.getElementById('root')!).render(
        <StrictMode>
            <App />
        </StrictMode>,
    )
}

startApp().catch(console.error);
