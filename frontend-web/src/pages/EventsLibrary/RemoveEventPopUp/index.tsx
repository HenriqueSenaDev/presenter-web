import { Context } from 'context/AppContextProvider';
import { useContext, useState } from 'react';
import WhiteGif from "assets/images/white-Gif.gif";
import './styles.css';

interface Props {
    setRemovePopupOpen: Function,
    eventToRemoveId: number | null
}

const RemoveEventPopUp = ({ setRemovePopupOpen, eventToRemoveId }: Props) => {
    const [loading, setLoading] = useState<boolean>(false);

    const { handleRemoveParticipation, handleParticipations } = useContext(Context);

    async function removeParticipation() {
        setLoading(true);
        await handleRemoveParticipation(eventToRemoveId);
        await handleParticipations();
        setRemovePopupOpen(false);
        setLoading(false);
    }

    return (
        <div className="remove-event-main">
            <div className="remove-card-container">
                {loading ? (
                    <div className="loading--add">
                        <h1>Presenter</h1>
                        <img src={WhiteGif} alt="loading-gif" ></img>
                    </div>
                ) : (
                    <>
                        <h1>Remover evento?</h1>
                        <div className="remove-event-buttons">
                            <button onClick={removeParticipation}>
                                Sim
                            </button>
                            <button onClick={() => {
                                setRemovePopupOpen(false);
                            }}>Cancelar</button>
                        </div>
                    </>
                )}
            </div>
        </div>
    );
}

export default RemoveEventPopUp;