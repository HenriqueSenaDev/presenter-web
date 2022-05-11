import Navbar from "../../components/Navbar";
import EventCard from "./EventCard";
import { ReactComponent as AddIcon } from "assets/images/add-Icon.svg";
import "./styles.css";
import AddEventPopup from "./AddEventPopup";
import { useState } from "react";

const EventsLibrary = () => {
    const [addPopupOpen, setAddPopupOpen] = useState<boolean>(false);

    document.addEventListener('click', ({ target }) => {
        if (document.querySelector('.join--event--main') === target) {
            setAddPopupOpen(false);
        }
    })

    return (
        <>
            <div>
                {
                    addPopupOpen && <AddEventPopup />
                }
                <Navbar />
                <div className="join--event--wrapper">
                    <div className="join--event--container">
                        <h1>Biblioteca de Eventos</h1>
                        <div className="events--manager--container">
                            <div className="events--container">
                                <EventCard />
                                <EventCard />
                                <EventCard />
                            </div>
                            <div
                                className="addEventButton"
                                onClick={() => {
                                    setAddPopupOpen(true);
                                }}
                            >
                                <AddIcon className="addIcon" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default EventsLibrary;