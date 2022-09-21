import "./styles.css";
import { ReactComponent as ProfileIcon } from "assets/images/profile-Icon.svg";
import { useContext, useState } from "react";
import { Context } from "context/AppContextProvider";

import { ReactComponent as LogOutIcon } from "assets/images/logOut-Icon.svg";
import { ReactComponent as CloseIcon } from "assets/images/close-Icon.svg";
import { ReactComponent as EventIcon } from "assets/images/event-Icon.svg";

const Navbar = () => {
    const [logOutPop, setLogoutPop] = useState<boolean>(false);

    const { authenticated, user, handleLogout, setEvent } = useContext(Context);

    return (
        <>
            {logOutPop &&
                <div className="logOutPop" >
                    <div className="close--logout">
                        <CloseIcon onClick={() => {
                            setLogoutPop(false);
                        }} />
                    </div>
                    <div className="logout--container"
                        onClick={() => {
                            setEvent(null);
                        }}
                    >
                        <EventIcon />
                        <h2>Biblioteca</h2>
                    </div>
                    <div className="logout--container"
                        onClick={() => {
                            handleLogout();
                        }}
                    >
                        <LogOutIcon />
                        <h2>Sair</h2>
                    </div>
                </div>
            }
            <div className="nav--container">
                <h1>Presenter</h1>
                {authenticated &&
                    <>
                        <div className="nav--profile--container"
                            onClick={() => {
                                setLogoutPop(true);
                            }}
                        >
                            <h1>{user?.username}</h1>
                            <ProfileIcon className="profile--margin" />
                        </div>
                    </>
                }
            </div>
        </>
    );
}

export default Navbar;