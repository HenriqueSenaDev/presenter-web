import { MouseEventHandler } from "react";
import singInImg from "../../assets/images/sign-in.svg";
import plusImg from "../../assets/images/plus.svg";
import "./styles.css";

interface IProps {
    text: string;
    onClick: MouseEventHandler<HTMLButtonElement>
}

function DualButton({ onClick, text }: IProps) {
    function getIconSource() {
        if (text === 'Entrar') return singInImg;
        if (text === 'Adicionar evento') return plusImg;
        return undefined;
    }

    return (
        <div className="dual-button-wrapper">
            <button
                className="dual-button"
                onClick={onClick}
            >
                <div className="dual-button-text-area">
                    <span>{text}</span>
                </div>

                <div className="dual-button-icon-area">
                    <img src={getIconSource()} alt={`${text}-image`} />
                </div>
            </button>
        </div>
    );
}

export default DualButton;