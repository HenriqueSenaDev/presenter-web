import "./styles.css";
import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";

const Navbar = () => {
    return (
        <div className="nav--container">
            <h1>Presenter</h1>
            <div className="nav--profile--container">
                <h1>Kim</h1>
                <ProfileIcon className="profile--margin" />
            </div>
        </div>
    );
}

export default Navbar;