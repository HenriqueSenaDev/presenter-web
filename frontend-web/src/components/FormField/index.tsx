import "./styles.css";

interface IProps {
    label: string,
    type: 'text' | 'password',
    setState: Function,
    onEnter?: Function
}

function FormField({ label, type, setState, onEnter }: IProps) {
    return (
        <div className="form-field-container">
            <div>
                <span>{label}</span>
            </div>

            <input 
                type={type} 
                name={label} 
                id={label} 
                onChange={(e) => setState(e.target.value)}
                onKeyUp={(e) => {
                    if (e.code === 'Enter' && onEnter) onEnter();
                }}
            />
        </div>
    );
}

export default FormField;