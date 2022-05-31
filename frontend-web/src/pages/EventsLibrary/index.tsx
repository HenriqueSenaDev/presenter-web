import Navbar from "../../components/Navbar";
import EventCard from "./EventCard";
import { ReactComponent as AddIcon } from "assets/images/add-Icon.svg";
import "./styles.css";
import AddEventPopup from "./AddEventPopup";
import { useState, useContext, useEffect } from "react";
import { Context } from "context/AppContextProvider";
import { api } from "services";
import { Navigate } from "react-router-dom";

interface IUserData {
   id: number,
   username: string
}

interface IParticipation {
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
}

const EventsLibrary = () => {
   const [addPopupOpen, setAddPopupOpen] = useState<boolean>(false);
   const [userData, setUserData] = useState<IUserData>();
   const [participations, setParticipations] = useState<IParticipation[]>([]);

   const { authenticated, userAndJWT, event, handleEvent } = useContext(Context);

   document.addEventListener('click', ({ target }) => {
      if (document.querySelector('.join--event--main') === target) {
         setAddPopupOpen(false);
      }
   })

   useEffect(() => {
      (async () => {
         if (userAndJWT) {
            const { data } = await api.get(`/api/appusers?username=${userAndJWT?.user}`, {
               headers: {
                  'Authorization': `Bearer ${userAndJWT.access_token}`
               }
            })
            setUserData(data);
         }
      })()
   }, [])

   useEffect(() => {
      (async () => {
         if (userData) {
            const { data: eventsData } = await api.get(`/api/appusers/participations/${userData.id}`, {
               headers: {
                  'Authorization': `Bearer ${userAndJWT?.access_token}`
               }
            })
            setParticipations(eventsData);
         }
      })()
   }, [userData])

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
               addPopupOpen && <AddEventPopup />
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
                                    onClick={async () => {
                                       await handleEvent(part.id.event.id);
                                    }}
                                 />
                              );
                           })
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