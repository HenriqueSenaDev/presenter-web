import "./styles.css";
import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";
import { useContext, useState } from "react";
import { ProfileContext } from "context/ProfileContext";

interface ITeamInfo {
    id: number,
    name: string
}

interface IProps {
    team: ITeamInfo | null,
    setPopUp: Function
}

const RatePopUp = ({ team, setPopUp }: IProps) => {
    const [avaliationValue, setAvaliationValue] = useState<string>("");

    const { user } = useContext(ProfileContext);

    return (
        <div className="rate--popup--container"
            id="ratePopupContainer"
            onClick={({ target }) => {
                // console.log(target)
                if (document.getElementById('ratePopupContainer') === target) {
                    setPopUp(false);
                }
            }}
        >
            <div className="rate--popup--card">
                <div className="rate--popup-user-container">
                    <ProfileIcon />
                    <h1>{user?.username}</h1>
                </div>
                <h1>Digite a nota da equipe</h1>
                <div className="equipe--area">
                    <h1>{team?.name}</h1>
                    <input type='text'
                        onChange={(event) => {
                            setAvaliationValue(event.target.value.replace(',', '.'));
                        }}
                        onKeyUp={async (event) => {
                            if (event.key === 'Enter') {
                                // handleAddAvaliation(team?.id, user?.id, Number(avaliationValue));
                                setPopUp(false);
                            }
                        }}
                    />
                </div>
                <button
                    onClick={async () => {
                        // handleAddAvaliation(team?.id, user?.id, Number(avaliationValue));
                        setPopUp(false);
                    }}
                >Avaliar</button>
            </div>
        </div>
    );
}

export default RatePopUp;