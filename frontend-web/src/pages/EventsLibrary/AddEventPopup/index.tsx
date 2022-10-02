import "./styles.css";
import { useContext, useState } from 'react';
import { Context } from "context/AppContextProvider";
import WhiteGif from "assets/images/white-Gif.gif";

interface IProps {
   setAddPopupOpen: Function
}

const AddEventPopup = ({ setAddPopupOpen }: IProps) => {
   const [eventCode, setEventCode] = useState<string>("");
   const [jurorCode, setJurorCode] = useState<string>("");
   const [loading, setLoading] = useState<boolean>(false);

   const { handleAddJurorParticipation, handleParticipations } = useContext(Context);

   const addParticipation = async () => {
      setLoading(true);
      await handleAddJurorParticipation(eventCode, jurorCode);
      await handleParticipations();
      setLoading(false);
      setAddPopupOpen(false);
   }

   return (
      <div className="join--event--main">
         <div className="join--card--container">
            {loading ?
               <div className="loading--add">
                  <h1>Presenter</h1>
                  <img src={WhiteGif} alt="loading-gif" ></img>
               </div>
               :
               <>
                  <div className="form--container">
                     <h1>Digite o código do evento:</h1>
                     <input type="text"
                        value={eventCode}
                        onChange={(event) => {
                           setEventCode(event.target.value);
                        }}
                     ></input>
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
                     ></input>
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