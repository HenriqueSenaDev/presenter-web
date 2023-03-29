import { MouseEventHandler } from "react";
import "./styles.css";

interface IProps {
    text: string,
    type?: 'confirm' | 'cancel',
    onClick: MouseEventHandler<HTMLDivElement>
}

function Button({ text, type, onClick }: IProps) {
    return (
        <div 
            className="button"
            onClick={onClick}
            style={type === 'cancel' ? { backgroundColor: '#76AFDA' } : {}}
        >
            {text}
        </div>
    );
}

export default Button;