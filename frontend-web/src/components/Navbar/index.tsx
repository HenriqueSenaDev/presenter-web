import "./styles.css";
import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";
import { useContext } from "react";
import { Context } from "context/AppContextProvider";

const Navbar = () => {
    const { authenticated, userAndJWT } = useContext(Context);

    return (
        <div className="nav--container">
            <h1>Presenter</h1>
            {
                authenticated &&
                <div className="nav--profile--container">
                    <h1>{userAndJWT?.user}</h1>
                    <ProfileIcon className="profile--margin" />
                </div>

            }
        </div>
    );
}

export default Navbar;