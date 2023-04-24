import { useState } from "react";
import Button from "components/Button";
import FormField from "components/FormField";
import registerImg from "../../../../assets/images/register.svg";
import loadingGif from "../../../../assets/images/loading.gif";
import "./styles.css";
import { usePresenter } from "hooks/usePresenter";

interface IProps {
    setIsPopupOpen: Function,
    setIsLoginPopupOpen: Function
}

function RegisterPopUp({ setIsPopupOpen, setIsLoginPopupOpen }: IProps) {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [repeatedPassword, setRepeatedPassword] = useState<string>('');
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const { register } = usePresenter();

    function handleRegister() {
        if (password != repeatedPassword) return alert('As senhas não são iguais.');

        try {
            setIsLoading(true);
            register(username, password);
            setIsPopupOpen(false);
            setIsLoginPopupOpen(true);
            return alert('Registrado com sucesso.');
        }
        finally {
            setIsLoading(false);
        }
    }

    function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
        if (document.querySelector('.register-popup-wrapper') === target) {
            setIsPopupOpen(false);
        }
    }

    return (
        <div
            className="register-popup-wrapper"
            onClick={checkOutClick}
        >
            <div className="register-popup-card">
                {isLoading && <img className='loading-gif' src={loadingGif} alt="loading-spiral" />}

                <img className='register-img' src={registerImg} alt="man-with-like" />

                <form className="register-form">
                    <div className="register-form-fields-area">
                        <FormField
                            label="Usuário"
                            type="text"
                            setState={setUsername}
                        />

                        <FormField
                            label="Senha"
                            type="password"
                            setState={setPassword}
                        />

                        <FormField
                            label="Confirmar senha"
                            type="password"
                            setState={setRepeatedPassword}
                            onEnter={handleRegister}
                        />
                    </div>

                    <Button text="Registrar" onClick={handleRegister} />
                </form>
            </div>
        </div>
    );
}

export default RegisterPopUp;