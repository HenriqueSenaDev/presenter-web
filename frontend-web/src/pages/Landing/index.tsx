import Navbar from "components/Navbar";
import "./styles.css";
import { Context } from "context/AppContextProvider";

import { ReactComponent as PeopleIcon } from "assets/images/people-Icon.svg";
import { ReactComponent as TimerIcon } from "assets/images/timer-Icon.svg";
import { ReactComponent as ComputerIcon } from "assets/images/computer-Icon.svg";
import { ReactComponent as RankingIcon } from "assets/images/leaderboard-Icon.svg";
import { useState, useContext } from "react";

const Landing = () => {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const { authenticated, JWT, handleLogin } = useContext(Context);

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
                        <hr></hr>
                        <div className="landing--card--form">
                            <h1>Entrar</h1>
                            <input
                                type="text"
                                placeholder="Username"
                                value={username}
                                onChange={(event) => {
                                    setUsername(event.target.value);
                                }}
                            ></input>
                            <input
                                type="text"
                                placeholder="Password"
                                value={password}
                                onChange={(event) => {
                                    setPassword(event.target.value);
                                }}
                                onKeyUp={(event) => {
                                    if (event.key === "Enter") {
                                        handleLogin(username, password);
                                    }
                                }}
                            ></input>
                            <button
                                onClick={() => {
                                    handleLogin(username, password);
                                }}
                            >
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