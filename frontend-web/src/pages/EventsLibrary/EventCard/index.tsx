import { ReactComponent as DeleteEvent } from "assets/images/close-Icon.svg";
import React, { useContext } from "react";
import "./styles.css"

interface IProps {
    event: {
        id: number,
        name: string,
        description: string
    },
    setEventToRemoveId: Function
}

const EventCard = ({ event, setEventToRemoveId }: IProps,) => {
    async function selectEvent() {
        // await handleEvent(event.id);
    }

    async function removeEvent(mouseEvt: React.MouseEvent<HTMLElement>) {
        mouseEvt.stopPropagation();
        setEventToRemoveId(event.id);
    }

    return (
        <>
            <div
                className="event--card--container"
                onClick={selectEvent}
            >
                <div
                    className="delete-event"
                    onClick={removeEvent}
                >
                    <DeleteEvent />
                </div>

                <div className="event--card--header">
                    <h1>{event.name}</h1>

                    <hr />
                </div>

                <span>
                    {
                        event.description.length < 65
                            ? event.description
                            : event.description.substring(0, 60) + '...'
                    }
                </span>
            </div>
        </>
    )
}

export default EventCard;