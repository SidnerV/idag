import {useEffect, useState} from "react";
import keycloak from "../keycloak.tsx";

type Group = {
    id: number,
    name: string,
}

export default function GroupList() {
    const [groups, setGroups] = useState<Group[]>([]);

    useEffect(() => {
        async function fetchGroups() {
            const response = await fetch(
                "http://localhost:8080/api/users/me/groups",
                {headers: {Authorization: `bearer ${keycloak.token}`,},})

            const data:Group[] = await response.json();
            setGroups(data);
        }

        fetchGroups();
    }, []);

    return (<div>
        <h2>My groups:</h2>

        {groups.length === 0 ? (
            <p>No groups found</p>
        ) : (
            groups.map((group) => (<li key={group.id}>{group.name}</li>))
        )}
    </div>)
}