import { ReactComponent as PeopleIcon } from "assets/images/people-Icon.svg";
import { ReactComponent as TimerIcon } from "assets/images/timer-Icon.svg";
import { ReactComponent as ComputerIcon } from "assets/images/computer-Icon.svg";
import { ReactComponent as RankingIcon } from "assets/images/leaderboard-Icon.svg";
import { useState, useContext } from "react";
import { Navigate } from "react-router-dom";
import { Context } from "context/AppContextProvider";
import Navbar from "components/Navbar";
import Loading from "pages/Loading";
import "./styles.css";

const Landing = () => {
   const [username, setUsername] = useState<string>("");
   const [password, setPassword] = useState<string>("");
   const [loading, setLoading] = useState<boolean>(false);

   const { handleLogin, authenticated } = useContext(Context);

   async function signIn() {
      setLoading(true);
      await handleLogin(username.trim(), password.trim());
      setLoading(false);
   }

   if (authenticated) return <Navigate replace to="/library" />

   if (loading) return <Loading />;

   return (
      <>
         <div className="landing--wrapper">
            <Navbar />

            <div className="landing--content">
               <div className="landing--card">
                  <div className="landing--card--content">
                     <h1>Presenter</h1>

                     <p>Gerenciamento de competições baseadas em equipes com jurados e tabelas de ranking.</p>

                     <div className="landing--content--images">
                        <PeopleIcon />
                        <TimerIcon />
                        <ComputerIcon />
                        <RankingIcon />
                     </div>
                  </div>

                  <hr />

                  <div className="landing--card--form">
                     <h1>Entrar</h1>

                     <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        minLength={5}
                        onChange={(evt) => setUsername(evt.target.value)}
                     />

                     <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        minLength={5}
                        onChange={(evt) => setPassword(evt.target.value)}
                        onKeyUp={(event) => {
                           if (event.key === "Enter") signIn();
                        }}
                     />

                     <button onClick={() => signIn()}>
                        Login
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </>
   )
}

export default Landing;