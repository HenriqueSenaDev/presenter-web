import "./styles.css";

interface IProps {
    label: string,
    type: 'text' | 'password',
    setState: Function
}

function FormField({ label, type, setState }: IProps) {
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
            />
        </div>
    );
}

export default FormField;