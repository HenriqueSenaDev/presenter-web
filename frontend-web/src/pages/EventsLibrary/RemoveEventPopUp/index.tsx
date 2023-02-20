import loadingGif from "assets/images/loading.gif";
import { useContext, useState } from 'react';
import { Context } from 'context/AppContextProvider';
import { usePresenter } from 'hooks/usePresenter';
import './styles.css';

interface Props {
    eventToRemoveId: number | null,
    setEventToRemoveId: Function,
    handleParticipations: Function
}

const RemoveEventPopUp = ({ eventToRemoveId, setEventToRemoveId, handleParticipations }: Props) => {
    const [loading, setLoading] = useState<boolean>(false);

    const { user } = useContext(Context);
    const { removeParticipation } = usePresenter();

    async function removeUserParticipation() {
        setLoading(true);
        await removeParticipation(user!.id, eventToRemoveId as number);
        await handleParticipations();
        setEventToRemoveId(null);
    }

    function closePopUp() {
        setEventToRemoveId(null);
    }

    function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
        if (document.querySelector('.remove-event-main') === target) {
            closePopUp();
        }
    }

    return (
        <div
            className="remove-event-main"
            onClick={checkOutClick}
        >
            <div className="remove-card-container">
                {loading ? (
                    <div className="loading--add">
                        <h1>Presenter</h1>

                        <img src={loadingGif} alt="loading-gif" />
                    </div>
                ) : (
                    <>
                        <h1>Remover evento?</h1>

                        <div className="remove-event-buttons">
                            <button onClick={removeUserParticipation}>
                                Sim
                            </button>

                            <button onClick={closePopUp}>
                                Cancelar
                            </button>
                        </div>
                    </>
                )}
            </div>
        </div>
    );
}

export default RemoveEventPopUp;