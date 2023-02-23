import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";
import { useContext, useState } from "react";
import { ProfileContext } from "context/ProfileContext";
import { PresenterContext } from "context/PresenterContext";
import { usePresenter } from "hooks/usePresenter";
import "./styles.css";

interface ITeamInfo {
    id: number,
    name: string
}

interface IProps {
    team: ITeamInfo | null,
    setTeam: Function
}

const RatePopUp = ({ team, setTeam }: IProps) => {
    const [avaliationValue, setAvaliationValue] = useState<number | null>(null);

    const { user } = useContext(ProfileContext);
    const { event, handleEvent } = useContext(PresenterContext);
    const { addAvaliationToTeam } = usePresenter();

    function closePopUp() {
        setTeam(null);
    }

    function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
        if (document.getElementById('ratePopupContainer') === target) {
            closePopUp();
        }
    }

    async function handleAddAvaliation() {
        if (!avaliationValue) return alert("Insira um valor para avaliar.");
        if (avaliationValue > 10 || avaliationValue < 0) return alert("Insira um valor de 0 a 10.");

        await addAvaliationToTeam(user!.id, team!.id, avaliationValue);
        await handleEvent(event!.id);
        closePopUp();
    }

    return (
        <div
            className="rate--popup--container"
            id="ratePopupContainer"
            onClick={checkOutClick}
        >
            <div className="rate--popup--card">
                <div className="rate--popup-user-container">
                    <ProfileIcon />

                    <h1>{user?.username}</h1>
                </div>

                <h1>Digite a nota da equipe</h1>

                <div className="equipe--area">
                    <h1>{team?.name}</h1>

                    <input type='number'
                        onChange={(event) => {
                            setAvaliationValue(Number(event.target.value));
                        }}
                        onKeyUp={async (event) => {
                            if (event.key === 'Enter') handleAddAvaliation()
                        }}
                    />
                </div>
                <button
                    onClick={async () => handleAddAvaliation()}
                >
                    Avaliar
                </button>
            </div>
        </div>
    );
}

export default RatePopUp;