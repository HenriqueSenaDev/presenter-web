import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { PresenterContext } from "context/PresenterContext";
import { IEventToRemoveInfo } from "pages/EventsLibrary/common/@types";
import closeIcon from "../../../../assets/images/close.svg";
import "./styles.css"

interface IProps {
    event: {
        id: number,
        name: string,
        description: string
    },
    setEventToRemoveInfo: Function
}

const EventCard = ({ event, setEventToRemoveInfo }: IProps,) => {
    const { handleEvent } = useContext(PresenterContext);

    const navigate = useNavigate();

    async function selectEvent() {
        await handleEvent(event.id);
        navigate('/event');
    }

    async function removeEvent(mouseEvt: React.MouseEvent<HTMLElement>) {
        mouseEvt.stopPropagation();
        setEventToRemoveInfo({
            id: event.id,
            name: event.name
        } as IEventToRemoveInfo);
    }

    return (
        <div
            className="event-card-container"
            onClick={selectEvent}
        >
            <div
                className="delete-event"
                onClick={removeEvent}
            >
                <img src={closeIcon} alt="close-icon" />
            </div>

            <div className="event-card-header">
                <h1>{event.name}</h1>

                <hr />
            </div>

            <p>{event.description}</p>
        </div>
    )
}

export default EventCard;