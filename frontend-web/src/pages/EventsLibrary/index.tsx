import { ReactComponent as AddIcon } from "assets/images/add-Icon.svg";
import { useState, useContext, useEffect } from "react";
import { Navigate } from "react-router-dom";
import { Context } from "context/AppContextProvider";
import { IParticipation } from "../../common/@Interfaces";
import { usePresenter } from "hooks/usePresenter";
import Navbar from "../../components/Navbar";
import EventCard from "./EventCard";
import AddEventPopup from "./AddEventPopup";
import RemoveEventPopUp from "./RemoveEventPopUp";
import "./styles.css";

const EventsLibrary = () => {
   const [addPopupOpen, setAddPopupOpen] = useState<boolean>(false);
   const [eventToRemoveId, setEventToRemoveId] = useState<number | null>(null);
   const [participations, setParticipations] = useState<IParticipation[]>([]);

   const { findUserParticipations } = usePresenter();

   const { authenticated, user, event, } = useContext(Context);

   function handleClick(evt: React.MouseEvent<HTMLElement>) {
      const { target } = evt;

      if (document.querySelector('.join--event--main') === target) {
         setAddPopupOpen(false);
      }
      if (document.querySelector('.remove-event-main') === target) {
         setEventToRemoveId(null);
      }
   }

   useEffect(() => {
      (async () => setParticipations(await findUserParticipations(user!.id)))();
   }, []);

   if (!authenticated) {
      return <Navigate replace to="/" />
   }

   if (event) {
      return <Navigate replace to="/event" />
   }

   return (
      <>
         <div onClick={handleClick}>
            {/* {
               addPopupOpen &&
               <AddEventPopup
                  setAddPopupOpen={setAddPopupOpen}
               />
            }

            {
               eventToRemoveId &&
               <RemoveEventPopUp
                  setEventToRemoveId={setEventToRemoveId}
                  eventToRemoveId={eventToRemoveId}
               />
            } */}

            <Navbar />

            <div className="join--event--wrapper">
               <div className="join--event--container">
                  <h1>Biblioteca de Eventos</h1>

                  <div className="events--manager--container">
                     <div className="events--container">
                        {
                           participations.length ? (
                              participations.map(part => {
                                 return (
                                    <EventCard
                                       event={part.event}
                                       key={part.event.id}
                                       setEventToRemoveId={setEventToRemoveId}
                                    />
                                 );
                              })
                           ) : (
                              <span>Adicione um evento para participar.</span>
                           )
                        }
                     </div>

                     <div
                        className="addEventButton"
                        onClick={() => setAddPopupOpen(true)}
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