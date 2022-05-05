import "./styles.css";
import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";

const RatePopUp = () => {
    return (
        <div className="rate--popup--container isHidden"
            id="ratePopupContainer"
            onClick={({ target }) => {
                console.log(target)
                if (document.getElementById('ratePopupContainer') === target) {
                    document.querySelector('.rate--popup--container')?.classList.remove('isShow');
                    document.querySelector('.rate--popup--container')?.classList.add('isHidden');
                }
            }}
        >
            <div className="rate--popup--card">
                <div className="rate--popup-user-container">
                    <h1>Kim</h1>
                    <ProfileIcon />
                </div>
                <h1>Digite a nota da equipe</h1>
                <div className="equipe--area">
                    <h1>Nome da Equipe</h1>
                    <input type='text' />
                </div>
                <button>Avaliar</button>
            </div>
        </div>
    );
}

export default RatePopUp;