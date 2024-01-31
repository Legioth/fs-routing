import { useParams } from "react-router-dom"

const meta = {
    "title": "Optional Parameter",
    "rolesAllowed": ["ROLE_ADMIN"]
}

export default function OptionalParameter() {
    const params = useParams();
    return <div>Params: {JSON.stringify(params)}</div>
}