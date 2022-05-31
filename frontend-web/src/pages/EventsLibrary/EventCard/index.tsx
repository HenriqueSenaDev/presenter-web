import "./styles.css"


interface Props {
    appEvent: {
        id: {
            event: {
                id: number,
                name: string,
                code: number,
                jurorCorde: number
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
            <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quasi nulla deserunt!</span>
        </div>
    )
}

export default EventCard;