import { useState, useContext, useEffect } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import { PresenterContext } from "context/PresenterContext";
import { IEventToRemoveInfo } from "./common/@types";
import EventCard from "./components/EventCard";
import AddEventPopup from "./components/AddEventPopup";
import RemoveEventPopUp from "./components/RemoveEventPopUp";
import DualButton from "components/DualButton";
import Menu from "components/Menu";
import eventsLibraryImg from "../../assets/images/events-library.svg";
import menuIcon from "../../assets/images/menu.svg";
import "./styles.css";

const EventsLibrary = () => {
   const [isMenuOpen, setIsMenuOpen] = useState<boolean>(document.body.clientWidth > 992);
   const [isAddPopupOpen, setIsAddPopupOpen] = useState<boolean>(false);
   const [eventToRemoveInfo, setEventToRemoveInfo] = useState<IEventToRemoveInfo | null>(null);

   const { authenticated } = useContext(ProfileContext);
   const { participations, handleParticipations } = useContext(PresenterContext);

   window.addEventListener('resize', () => {
      if (document.body.clientWidth > 992) return setIsMenuOpen(true);
   });

   useEffect(() => {
      handleParticipations();
   }, []);

   if (!authenticated) {
      return <Navigate replace to="/" />
   }

   return (
      <div className="library-wrapper">
         {isMenuOpen && <Menu setIsMenuOpen={setIsMenuOpen} />}

         {eventToRemoveInfo &&
            <RemoveEventPopUp
               eventToRemoveInfo={eventToRemoveInfo}
               setEventToRemoveInfo={setEventToRemoveInfo}
            />
         }

         {isAddPopupOpen &&
            <AddEventPopup setIsAddPopupOpen={setIsAddPopupOpen} />
         }

         <div className="library-container">
            <div className="library-card">
               <div
                  className="library-toogle-menu"
                  onClick={() => setIsMenuOpen(true)}
               >
                  <img src={menuIcon} alt="hamburguer-menu-icon" />
               </div>

               <h1>Biblioteca de Eventos</h1>

               <img
                  className="events-library-img"
                  src={eventsLibraryImg}
                  alt="girl-looking-to-scheduled-events"
               />

               <DualButton text="Adicionar evento" onClick={() => setIsAddPopupOpen(true)} />
            </div>

            <div className="events-container">
               {participations.length ? (
                  participations.map(part => {
                     return (
                        <EventCard
                           event={part.event}
                           key={part.event.id}
                           setEventToRemoveInfo={setEventToRemoveInfo}
                        />
                     );
                  })
               ) : (
                  <span>Adicione um evento para participar.</span>
               )}
            </div>
         </div>
      </div>
   );
}

export default EventsLibrary;