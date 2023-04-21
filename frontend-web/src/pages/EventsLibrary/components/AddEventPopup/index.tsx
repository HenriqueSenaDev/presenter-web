import { useContext, useState } from 'react';
import { PresenterContext } from "context/PresenterContext";
import { usePresenter } from 'hooks/usePresenter';
import FormField from "components/FormField";
import Button from 'components/Button';
import joinEventImg from "../../../../assets/images/join-event.svg";
import loadingGif from "assets/images/loading.gif";
import "./styles.css";

interface IProps {
   setIsAddPopupOpen: Function
}

const AddEventPopup = ({ setIsAddPopupOpen }: IProps) => {
   const [activeTab, setActiveTab] = useState<'juror' | 'spectator'>('juror');
   const [joinCode, setJoinCode] = useState<string>("");
   const [jurorCode, setJurorCode] = useState<string>("");
   const [loading, setLoading] = useState<boolean>(false);

   const { handleParticipations } = useContext(PresenterContext);

   const { addJurorParticipation, addSpectatorParticipation } = usePresenter();

   function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
      if (document.querySelector('.join-event-wrapper') === target) {
         setIsAddPopupOpen(false);
      }
   }

   function getTabColor(tabName: 'juror' | 'spectator') {
      return activeTab === tabName ? '#FFF' : 'rgba(255, 255, 255, 0.5)';
   }
   
   async function addParticipation() {
      if (activeTab === 'juror') await addJurorParticipation(joinCode, jurorCode);
      if (activeTab === 'spectator') await addSpectatorParticipation(joinCode);
   }

   async function joinEvent() {
      try {
         setLoading(true);
         await addParticipation();
         await handleParticipations();
      }
      finally {
         setIsAddPopupOpen(false);
      }
   }

   return (
      <div
         className="join-event-wrapper"
         onClick={checkOutClick}
      >
         <div className="join-card-container">
            <img className='join-event-img' src={joinEventImg} alt="man-and-screen-with-up-arrow" />

            <div className="event-code-area">
               <div className="code-tabs-headers">
                  <h1 
                     onClick={() => setActiveTab('juror')} 
                     style={{ borderColor: getTabColor('juror'), color: getTabColor('juror')}}
                  >
                     Jurado
                  </h1>

                  <h1 
                     onClick={() => setActiveTab('spectator')}
                     style={{ borderColor: getTabColor('spectator'), color: getTabColor('spectator')}}
                  >
                     Espectador
                  </h1>
               </div>

               <div className="event-code-inputs">
                  <FormField 
                     label="Código de entrada" 
                     type="text" 
                     setState={setJoinCode} 
                  />

                  {(activeTab === 'juror') &&
                     <FormField 
                        label="Código de jurado" 
                        type="text" 
                        setState={setJurorCode} 
                        onEnter={joinEvent}
                     />
                  }

                  {loading ? (
                     <img className='add-loading-gif' src={loadingGif} alt="loading-gif" />
                  ) : (
                     <Button text='Confirmar' onClick={joinEvent} />
                  )}
               </div>
            </div>

         </div> 
      </div>
   );
}

export default AddEventPopup;