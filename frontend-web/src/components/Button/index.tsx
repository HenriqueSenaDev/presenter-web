import { MouseEventHandler } from "react";
import "./styles.css";

interface IProps {
    text: string,
    onClick: MouseEventHandler<HTMLDivElement>
}

function Button({ text, onClick }: IProps) {
    return (
        <div 
            className="button"
            onClick={onClick}
        >
            {text}
        </div>
    );
}

export default Button;