import loadingGif from "assets/images/loading.gif";
import { useContext, useState } from 'react';
import { Context } from "context/AppContextProvider";
import { usePresenter } from 'hooks/usePresenter';
import "./styles.css";

interface IProps {
   setIsAddPopupOpen: Function,
   handleParticipations: Function
}

const AddEventPopup = ({ setIsAddPopupOpen, handleParticipations }: IProps) => {
   const [joinCode, setJoinCode] = useState<string>("");
   const [jurorCode, setJurorCode] = useState<string>("");
   const [loading, setLoading] = useState<boolean>(false);

   const { user } = useContext(Context);
   const { addJurorParticipation } = usePresenter();

   function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
      if (document.querySelector('.join--event--main') === target) {
         setIsAddPopupOpen(false);
      }
   }

   async function addParticipation() {
      setLoading(true);
      await addJurorParticipation(user!.id, joinCode, jurorCode);
      await handleParticipations();
      setIsAddPopupOpen(false);
   }

   return (
      <div
         className="join--event--main"
         onClick={checkOutClick}
      >
         <div className="join--card--container">
            {loading ?
               <div className="loading--add">
                  <h1>Presenter</h1>

                  <img src={loadingGif} alt="loading-gif" ></img>
               </div>
               :
               <>
                  <div className="form--container">
                     <h1>Digite o código do evento:</h1>

                     <input
                        type="text"
                        value={joinCode}
                        onChange={(event) => {
                           setJoinCode(event.target.value);
                        }}
                     />
                  </div>

                  <div className="form--container">
                     <h1>É jurado?<br />Digite a senha de jurado:</h1>

                     <input type="text"
                        value={jurorCode}
                        onChange={(event) => {
                           setJurorCode(event.target.value);
                        }}
                        onKeyUp={async ({ key }) => {
                           if (key === 'Enter') {
                              await addParticipation();
                           }
                        }}
                     />
                  </div>

                  <button onClick={addParticipation}>
                     Entrar
                  </button>
               </>
            }
         </div>
      </div >
   );
}

export default AddEventPopup;