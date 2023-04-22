import { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import profileIcon from "../../assets/images/profile.svg";
import closeIcon from "../../assets/images/close.svg";
import signOutIcon from "../../assets/images/sign-out.svg";
import "./styles.css";

interface IProps {
    setIsMenuOpen: Function
}

function Menu({ setIsMenuOpen }: IProps) {
    const { user, handleLogout } = useContext(ProfileContext);    
    const navigate = useNavigate();

    window.addEventListener('resize', () => {
        if (document.body.clientWidth > 992) return setIsMenuOpen(true);
    });

    return (
        <aside className="menu-wrapper">
            <div className="menu-container">
                <div className="menu-header">
                    <div className="profile-area">
                        <img src={profileIcon} alt="profile-icon" />

                        <span>{user!.username}</span>
                    </div>

                    <img 
                        className="menu-close-img" 
                        onClick={() => setIsMenuOpen(false)}
                        src={closeIcon} 
                        alt="x" 
                    />
                </div>

                <div className="nav-area">
                    <span onClick={() => navigate('/library')}>
                        Biblioteca
                    </span>
                </div>

                <div 
                    className="log-out-area"
                    onClick={() => handleLogout()}
                >
                    <img src={signOutIcon} alt="sign-out-icon" />

                    <span>Sair</span>
                </div>
            </div>
        </aside>
    );
}

export default Menu;