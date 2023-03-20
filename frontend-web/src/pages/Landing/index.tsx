import { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import Footer from "components/Footer";
import DualButton from "components/DualButton";
import LoginPopUp from "./components/LoginPopUp";
import peopleImg from "../../assets/images/people.svg";
import managementImg from "../../assets/images/management.svg";
import "./styles.css";

const Landing = () => {
   const [isPopupOpen, setIsPopupOpen] = useState<boolean>(false);

   const { authenticated } = useContext(ProfileContext);

   if (authenticated) return <Navigate replace to="/library" />

   return (
      <div className="landing-container">
         {isPopupOpen && <LoginPopUp setIsPopupOpen={setIsPopupOpen} />}

         <div className="landing-card-container">
            <div className="landing-card">
               <div className="landing-card-header-container">
                  <div className="landing-card-header">
                     <h1>Presenter</h1>

                     <p>
                        Gerenciamento de competições baseadas em equipes com jurados e tabelas de ranking.
                     </p>
                  </div>

                  <img src={peopleImg} alt="people-image" />
               </div>

               <div className="landing-card-cta-container">
                  <DualButton text='Entrar' onClick={() => setIsPopupOpen(true)} />

                  <img className="management-image" src={managementImg} alt="people-managing" />
               </div>
            </div>
         </div>

         <Footer />
      </div>
   )
}

export default Landing;