import { useContext, useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import { PresenterContext } from "context/PresenterContext";
import { ITeamInfo } from "./common/@types";
import RatePopUp from "./components/RatePopUp";
import Menu from "components/Menu";
import medalIcon from "../../assets/images/medal.svg";
import roleCard from "../../assets/images/role-card.svg";
import menuIcon from "../../assets/images/menu.svg";
import "./styles.css";

const Event = () => {
    const [isMenuOpen, setIsMenuOpen] = useState<boolean>(document.body.clientWidth > 992);
    const [teamToRateInfo, setTeamToRateInfo] = useState<ITeamInfo | null>(null);
    const [userEventRole, setUserEventRole] = useState<'Jurado' | 'Espectador'>('Espectador');

    const { authenticated, user } = useContext(ProfileContext);
    const { participations, event } = useContext(PresenterContext);

    function getUserEventRole() {
        const part = participations.find(part => part.event.id === event!.id);
        const eventRole = part!.eventRole;

        if (eventRole === 'JUROR') return 'Jurado';
        else return 'Espectador';
    }

    function getSortedTeams() {
        return event!.teams.sort((a, b) => b.average - a.average);
    }

    useEffect(() => {
        setUserEventRole(getUserEventRole());
    }, []);

    if (!authenticated || !event) {
        return <Navigate replace to="/" />
    }

    return (
        <div className="event-wrapper">
            {isMenuOpen && <Menu setIsMenuOpen={setIsMenuOpen} />}

            {teamToRateInfo && <RatePopUp teamInfo={teamToRateInfo} setTeamInfo={setTeamToRateInfo} />}

            <div className="event-container">
                <div
                    className="event-toogle-menu"
                    onClick={() => setIsMenuOpen(true)}
                >
                    <img src={menuIcon} alt="hamburguer-menu-icon" />
                </div>

                <div className="event-header">
                    <img src={medalIcon} alt="medal-icon" />

                    <h1>{event!.name}</h1>
                </div>

                <div className="event-table-slider">
                    <table className="event-table">
                        <thead>
                            <tr>
                                <th className="table-header">
                                    <span>Equipe</span>
                                    <hr />
                                </th>

                                <th className="table-header">
                                    <span>Projeto</span>
                                    <hr />
                                </th>

                                <th className="table-header">
                                    <span>Turma</span>
                                    <hr />
                                </th>

                                <th className="table-header">
                                    <span>Avaliações</span>
                                    <hr />
                                </th>

                                <th className="table-header">
                                    <span>Média</span>
                                    <hr />
                                </th>
                            </tr>
                        </thead>

                        <tbody>
                            {getSortedTeams().map(team => {
                                const { id, name, average, avaliationsQuantity, classroom, project } = team;

                                return (
                                    <tr
                                        key={id}
                                        onClick={() => {
                                            if (userEventRole === 'Jurado') setTeamToRateInfo({ id, name });
                                        }}
                                    >
                                        <td>{name}</td>

                                        <td>{project}</td>

                                        <td>{classroom}</td>

                                        <td>{avaliationsQuantity}</td>

                                        <td>{average.toFixed(2)}</td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>

                <div className="event-role-container">
                    <div className="role-info">
                        <img src={roleCard} alt="role-card" />

                        <span>{user!.username}: {userEventRole}</span>
                    </div>

                    {userEventRole === 'Jurado' && <p>Clique na equipe para avaliá-la!</p>}
                </div>
            </div>
        </div>
    );
}

export default Event;