import { useContext, useState } from "react";
import { ProfileContext } from "context/ProfileContext";
import Button from "components/Button";
import FormField from "components/FormField";
import loginImg from "../../../../assets/images/login.svg";
import loadingGif from "../../../../assets/images/loading.gif";
import "./styles.css";

interface IProps {
    setIsPopupOpen: Function,
    setIsRegisterPopupOpen: Function
}

function LoginPopUp({ setIsPopupOpen, setIsRegisterPopupOpen }: IProps) {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const { handleLogin } = useContext(ProfileContext);

    async function signIn() {
        try {
            setIsLoading(true);
            await handleLogin(username.trim(), password.trim());
        }
        finally {
            setIsLoading(false);
        }
    }

    function openRegisterPopup() {
        setIsPopupOpen(false);
        setIsRegisterPopupOpen(true);
    }

    function checkOutClick({ target }: React.MouseEvent<HTMLElement>) {
        if (document.querySelector('.login-popup-wrapper') === target) {
            setIsPopupOpen(false);
        }
    }

    return (
        <div
            className="login-popup-wrapper"
            onClick={checkOutClick}
        >
            <div className="login-popup-card">
                {isLoading && <img className='loading-gif' src={loadingGif} alt="loading-spiral" />}

                <img className='login-img' src={loginImg} alt="girl-looking-to-form" />

                <div className="login-content-area">
                    <div className="register-link">
                        <p>Ainda não tem uma conta?</p>

                        <p onClick={openRegisterPopup}>
                            Cadastre-se aqui
                        </p>
                    </div>

                    <form className="login-form">
                        <div className="login-form-fields-area">
                            <FormField 
                                label="Usuário" 
                                type="text" 
                                setState={setUsername} 
                            />

                            <FormField 
                                label="Senha"  
                                type="password" 
                                setState={setPassword} 
                                onEnter={signIn}
                            />
                        </div>

                        <Button text="Login" onClick={signIn} />
                    </form>
                </div>
            </div>
        </div>
    );
}

export default LoginPopUp;