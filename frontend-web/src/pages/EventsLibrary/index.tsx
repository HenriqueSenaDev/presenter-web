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
   const [participations, setParticipations] = useState<IParticipation[]>([]);
   const [isAddPopupOpen, setIsAddPopupOpen] = useState<boolean>(false);
   const [eventToRemoveId, setEventToRemoveId] = useState<number | null>(null);

   const { authenticated, user } = useContext(Context);
   const { findUserParticipations } = usePresenter();

   async function handleParticipations() {
      setParticipations(await findUserParticipations(user!.id));
   }

   useEffect(() => {
      handleParticipations();
   }, []);

   if (!authenticated) {
      return <Navigate replace to="/" />
   }

   return (
      <div>
         {
            isAddPopupOpen &&
            <AddEventPopup
               setIsAddPopupOpen={setIsAddPopupOpen}
               handleParticipations={handleParticipations}
            />
         }

         {/* {
            eventToRemoveId &&
            <RemoveEventPopUp
               eventToRemoveId={eventToRemoveId}
               setEventToRemoveId={setEventToRemoveId}
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
                     onClick={() => setIsAddPopupOpen(true)}
                  >
                     <AddIcon className="addIcon" />
                  </div>
               </div>
            </div>
         </div>
      </div>
   );
}

export default EventsLibrary;