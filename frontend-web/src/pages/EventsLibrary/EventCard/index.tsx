import "./styles.css"


interface Props {
    appEvent: {
        id: {
            event: {
                id: number,
                name: string,
                code: number,
                jurorCorde: number,
                description: string
            },
            user: {
                id: number,
                username: string
            }
        },
        eventRole: {
            id: number,
            name: string
        },
        team: null
    },
    onClick: () => void
}

const EventCard = ({ appEvent, onClick }: Props,) => {
    return (
        <div className="event--card--container" onClick={onClick} >
            <div className="event--card--header">
                <h1>{appEvent.id.event.name}</h1>
                <hr></hr>
            </div>
            <span>{
                appEvent.id.event.description.length < 65
                    ? appEvent.id.event.description
                    : appEvent.id.event.description.substring(0, 60) + '...'
            }</span>
        </div>
    )
}

export default EventCard;