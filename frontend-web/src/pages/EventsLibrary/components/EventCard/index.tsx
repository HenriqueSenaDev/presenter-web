import { ReactComponent as DeleteEvent } from "assets/images/close-Icon.svg";
import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { PresenterContext } from "context/PresenterContext";
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
    const { handleEvent } = useContext(PresenterContext);

    const navigate = useNavigate();

    async function selectEvent() {
        await handleEvent(event.id);
        navigate('/event');
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