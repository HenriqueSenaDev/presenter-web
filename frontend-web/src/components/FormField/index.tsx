import { useState } from "react";
import eyeIcon from '../../assets/images/eye.svg';
import eyeSlashIcon from '../../assets/images/eye-slash.svg';
import "./styles.css";

interface IProps {
    label: string,
    type: 'text' | 'password',
    setState: Function,
    onEnter?: Function
}

function FormField({ label, type: propsType, setState, onEnter }: IProps) {
    const [inputType, setInputType] = useState<'text' | 'password'>(propsType);

    function getEyePassImgSource() {
        if (inputType === 'password') return eyeIcon;
        else return eyeSlashIcon;
    }

    function tooglePasswordVisibility() {
        if (inputType === 'password') setInputType('text');
        else setInputType('password');
    }

    return (
        <div className="form-field-container">
            <div>
                <span>{label}</span>
            </div>

            <div className="form-input-area">
                <input 
                    type={inputType} 
                    name={label} 
                    id={label} 
                    onChange={(e) => setState(e.target.value)}
                    onKeyUp={(e) => {
                        if (e.code === 'Enter' && onEnter) onEnter();
                    }}
                />

                {(propsType === 'password') &&
                    <img 
                        src={getEyePassImgSource()} 
                        alt="password-eye"
                        onClick={tooglePasswordVisibility} 
                    />
                }
            </div>
        </div>
    );
}

export default FormField;