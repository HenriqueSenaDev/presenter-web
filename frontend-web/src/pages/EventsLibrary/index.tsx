import { useState, useContext, useEffect } from "react";
import { Context } from "context/AppContextProvider";
import { Navigate } from "react-router-dom";
import Navbar from "../../components/Navbar";
import EventCard from "./EventCard";
import AddEventPopup from "./AddEventPopup";
import RemoveEventPopUp from "./RemoveEventPopUp";
import { ReactComponent as AddIcon } from "assets/images/add-Icon.svg";
import "./styles.css";

const EventsLibrary = () => {
   const [addPopupOpen, setAddPopupOpen] = useState<boolean>(false);
   const [removePopupOpen, setRemovePopupOpen] = useState<boolean>(false);
   const [eventToRemoveId, setEventToRemoveId] = useState<number | null>(null);

   const {
      authenticated,
      user,
      event,
      participations,
      handleParticipations
   } = useContext(Context);

   document.addEventListener('click', ({ target }) => {
      if (document.querySelector('.join--event--main') === target) {
         setAddPopupOpen(false);
      }
      if (document.querySelector('.remove-event-main') === target) {
         setRemovePopupOpen(false);
      }
   });

   useEffect(() => {
      (async () => {
         await handleParticipations();
      })();
   }, [user]);

   if (!authenticated) {
      return <Navigate replace to="/" />
   }

   if (event) {
      return <Navigate replace to="/event" />
   }

   return (
      <>
         <div>
            {
               addPopupOpen &&
               <AddEventPopup
                  setAddPopupOpen={setAddPopupOpen}
               />
            }
            {
               removePopupOpen &&
               <RemoveEventPopUp
                  setRemovePopupOpen={setRemovePopupOpen}
                  eventToRemoveId={eventToRemoveId}
               />
            }
            <Navbar />
            <div className="join--event--wrapper">
               <div className="join--event--container">
                  <h1>Biblioteca de Eventos</h1>
                  <div className="events--manager--container">
                     <div className="events--container">
                        {
                           participations && participations.map(part => {
                              return (
                                 <EventCard
                                    appEvent={part}
                                    key={part.id.event.id}
                                    setRemoveOpen={setRemovePopupOpen}
                                    setEventToRemoveId={setEventToRemoveId}
                                 />
                              );
                           })
                        }
                        {
                           !participations.length &&
                           <span>Adicione um evento para participar.</span>
                        }
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