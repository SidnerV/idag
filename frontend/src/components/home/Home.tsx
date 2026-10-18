import GroupList from "./GroupList.tsx";
import {LogoutButton} from "./LogoutButton.tsx";

export function Home() {
    return (<>
        <p>
            You are logged in!
        </p>
        <GroupList></GroupList>
        <LogoutButton/>
    </>)
}
