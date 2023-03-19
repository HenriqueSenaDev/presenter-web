import { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import Loading from "pages/Loading";
import Footer from "components/Footer";
import DualButton from "components/DualButton";
import peopleImg from "../../assets/images/people.svg";
import managementImg from "../../assets/images/management.svg";
import "./styles.css";

const Landing = () => {
   const [username, setUsername] = useState<string>("");
   const [password, setPassword] = useState<string>("");
   const [loading, setLoading] = useState<boolean>(false);

   const { handleLogin, authenticated } = useContext(ProfileContext);

   async function signIn() {
      setLoading(true);
      await handleLogin(username.trim(), password.trim());
      setLoading(false);
   }

   if (authenticated) return <Navigate replace to="/library" />

   if (loading) return <Loading />;

   return (
      <div className="landing-container">
         <div className="landing-card-container">
            <div className="landing-card">
               <div className="landing-card-header-container">
                  <div className="landing-card-header">
                     <h1>Presenter</h1>

                     <p>Gerenciamento de competições baseadas em equipes com jurados e tabelas de ranking.</p>
                  </div>

                  <img src={peopleImg} alt="people-image" />
               </div>

               <div className="landing-card-cta-container">
                  <DualButton text='Entrar' onClick={() => { }} />

                  <img className="management-image" src={managementImg} alt="people-managing" />
               </div>
            </div>
         </div>

         <Footer />
      </div>
   )
}

export default Landing;