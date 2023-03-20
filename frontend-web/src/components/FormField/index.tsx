import "./styles.css";

interface IProps {
    label: string,
    setState: Function
}

function FormField({ label, setState }: IProps) {
    return (
        <div className="form-field-container">
            <div>
                <span>{label}</span>
            </div>

            <input 
                type="text" 
                name={label} 
                id={label} 
                onChange={(e) => setState(e.target.value)}
            />
        </div>
    );
}

export default FormField;