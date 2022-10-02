import { ReactComponent as DeleteEvent } from "assets/images/close-Icon.svg";
import { Context } from "context/AppContextProvider";
import React, { useContext, useState } from "react";
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
    setRemoveOpen: Function,
    setEventToRemoveId: Function
}

const EventCard = ({ appEvent, setRemoveOpen, setEventToRemoveId }: Props,) => {
    const { handleEvent } = useContext(Context);

    async function selectEvent(event: React.MouseEvent<HTMLElement>) {
        await handleEvent(appEvent.id.event.id);
    }

    async function removeEvent(event: React.MouseEvent<HTMLElement>) {
        event.stopPropagation();
        setEventToRemoveId(appEvent.id.event.id);
        setRemoveOpen(true);
    }

    return (
        <>
            <div className="event--card--container" onClick={selectEvent} >
                <div
                    className="delete-event"
                    onClick={removeEvent}
                >
                    <DeleteEvent />
                </div>
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
        </>
    )
}

export default EventCard;