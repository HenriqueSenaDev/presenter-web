import "./styles.css";

const AddEventPopup = () => {
    return (
        <div className="join--event--main">
            <div className="join--card--container">
                <div className="form--container">
                    <h1>Digite o código do evento:</h1>
                    <input type="text"></input>
                </div>
                <div className="form--container">
                    <h1>Digite a senha do evento:</h1>
                    <input type="text"></input>
                </div>
                <button>
                    Entrar
                </button>
            </div>
        </div>
    );
}

export default AddEventPopup;