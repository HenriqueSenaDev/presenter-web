import { useContext, useState } from 'react';
import { PresenterContext } from "context/PresenterContext";
import { usePresenter } from 'hooks/usePresenter';
import { IEventToRemoveInfo } from "pages/EventsLibrary/common/@types";
import Button from "components/Button";
import loadingGif from "assets/images/loading.gif";
import quitEventImg from '../../../../assets/images/quit-event.svg';
import './styles.css';

interface Props {
    eventToRemoveInfo: IEventToRemoveInfo | null,
    setEventToRemoveInfo: Function
}

const RemoveEventPopUp = ({ eventToRemoveInfo, setEventToRemoveInfo }: Props) => {
    const [loading, setLoading] = useState<boolean>(false);

    const { handleParticipations } = useContext(PresenterContext);
    const { removeParticipation } = usePresenter();

    async function removeUserParticipation() {
        try {
            setLoading(true);
            await removeParticipation(eventToRemoveInfo!.id);
            await handleParticipations();

        }
        finally {
            closePopUp();
        }
    }

    function closePopUp() {
        setEventToRemoveInfo(null);
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
                <img src={quitEventImg} alt="man-leaving-from-door" />
                
                <div className="remove-event-area">
                    <h1>{eventToRemoveInfo!.name}</h1>

                    <span>Remover evento?</span>

                    {loading ? (
                        <img src={loadingGif} alt="loading-gif" />
                    ) : (
                        <div className="remove-event-buttons">
                            <Button text="Confirmar" onClick={removeUserParticipation} />

                            <Button text="Cancelar" onClick={closePopUp} type='cancel' />
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default RemoveEventPopUp;