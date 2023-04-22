import { useContext, useState } from "react";
import { PresenterContext } from "context/PresenterContext";
import { usePresenter } from "hooks/usePresenter";
import { ITeamInfo } from "pages/Event/common/@types";
import rateEvent from "../../../../assets/images/rate-event.svg";
import graphIcon from "../../../../assets/images/graph.svg";
import loadingGif from "../../../../assets/images/loading.gif";
import "./styles.css";

interface IProps {
    teamInfo: ITeamInfo | null,
    setTeamInfo: Function
}

const RatePopUp = ({ teamInfo, setTeamInfo }: IProps) => {
    const [avaliationValue, setAvaliationValue] = useState<number | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const { event, handleEvent } = useContext(PresenterContext);

    const { addAvaliationToTeam } = usePresenter();

    function closePopUp() {
        setTeamInfo(null);
    }

    function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
        if (document.querySelector('.rate-popup-container') === target) {
            closePopUp();
        }
    }

    async function handleAddAvaliation() {
        if (!avaliationValue) return alert("Insira um valor para avaliar.");
        if (avaliationValue > 10 || avaliationValue < 0) return alert("Insira um valor de 0 a 10.");

        setIsLoading(true);
        await addAvaliationToTeam(teamInfo!.id, avaliationValue);
        await handleEvent(event!.id);
        closePopUp();
    }

    return (
        <div
            className="rate-popup-container"
            onClick={checkOutClick}
        >
            <div className="rate-popup-card">
                {isLoading && <img className='rate-loading-gif' src={loadingGif} alt="loading-spiral" />}

                <img className="rate-image" src={rateEvent} alt="tablet-with-metrics-and-man" />

                <div className="rate-event-area">
                    <h1>{teamInfo!.name}</h1>

                    <div className="rate-input-area">
                        <h1>Nota:</h1>

                        <input type='number'
                            onChange={(event) => {
                                setAvaliationValue(Number(event.target.value));
                            }}
                            onKeyUp={(event) => {
                                if (event.key === 'Enter') handleAddAvaliation();
                            }}
                        />
                    </div>

                    <button onClick={() => handleAddAvaliation()}>
                        <img src={graphIcon} alt="graph" />

                        Avaliar
                    </button>
                </div>
            </div>
        </div>
    );
}

export default RatePopUp;