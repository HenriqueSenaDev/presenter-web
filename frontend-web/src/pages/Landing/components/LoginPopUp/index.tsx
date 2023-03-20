import { useContext, useState } from "react";
import { ProfileContext } from "context/ProfileContext";
import Button from "components/Button";
import FormField from "components/FormField";
import loginImg from "../../../../assets/images/login.svg";
import loadingGif from "../../../../assets/images/loading.gif";
import "./styles.css";

interface IProps {
    setIsPopupOpen: Function
}

function LoginPopUp({ setIsPopupOpen }: IProps) {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const { handleLogin } = useContext(ProfileContext);

    async function signIn() {
        setIsLoading(true);
        await handleLogin(username.trim(), password.trim());
        setIsLoading(false);
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

                <form className="login-form">
                    <div className="login-form-fields-area">
                        <FormField label="Usuário" type="text" setState={setUsername} />

                        <FormField label="Senha"  type="password" setState={setPassword} />
                    </div>

                    <Button text="Login" onClick={signIn} />
                </form>
            </div>
        </div>
    );
}

export default LoginPopUp;