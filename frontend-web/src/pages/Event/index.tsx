import { useContext, useState } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import { PresenterContext } from "context/PresenterContext";
import RatePopUp from "./components/RatePopUp";
import Menu from "components/Menu";
import medalIcon from "../../assets/images/medal.svg";
import roleCard from "../../assets/images/role-card.svg";
import menuIcon from "../../assets/images/menu.svg";
import "./styles.css";

interface ITeamInfo {
    id: number,
    name: string
}

const Event = () => {
    const [team, setTeam] = useState<ITeamInfo | null>(null);
    const [isDesktop, setIsDesktop] = useState<boolean>(document.body.clientWidth > 992);
    const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);

    const { authenticated, user } = useContext(ProfileContext);
    const { participations } = useContext(PresenterContext);
    const { event } = useContext(PresenterContext);

    if (!authenticated || !event) {
        return <Navigate replace to="/" />
    }

    function getUserEventRole() {
        const part = participations.find(part => part.event.id === event!.id);
        const eventRole = part!.eventRole;

        if (eventRole === 'JUROR') return 'Jurado';
        if (eventRole === 'Spectator') return 'Espectador';
    }

    const role = getUserEventRole();

    window.addEventListener('resize', () => {
        if (document.body.clientWidth > 992) return setIsDesktop(true);
        return setIsDesktop(false);
    });

    const menuConditional = isMenuOpen || isDesktop;

    return (
        <div className="event-wrapper">
            {(menuConditional) && <Menu setIsMenuOpen={setIsMenuOpen} />}

            <div className="event-container">
                <div
                    className="event-toogle-menu"
                    onClick={() => setIsMenuOpen(true)}
                >
                    <img src={menuIcon} alt="hamburguer-menu-icon" />
                </div>

                {team &&
                    <RatePopUp
                        team={team}
                        setTeam={setTeam}
                    />
                }

                <div className="event-header">
                    <img src={medalIcon} alt="medal-icon" />

                    <h1>{event!.name}</h1>
                </div>

                <div className="event-table-slider">
                    <table className="event-table">
                        <thead>
                            <tr>
                                <div className="table-header">
                                    <th>Equipe</th>
                                    <hr />
                                </div>

                                <div className="table-header">
                                    <th>Projeto</th>
                                    <hr />
                                </div>

                                <div className="table-header">
                                    <th>Turma</th>
                                    <hr />
                                </div>

                                <div className="table-header">
                                    <th>Avaliações</th>
                                    <hr />
                                </div>

                                <div className="table-header">
                                    <th>Média</th>
                                    <hr />
                                </div>
                            </tr>
                        </thead>

                        <tbody>
                            {event!.teams.map(team => (
                                <tr key={team.id}>
                                    <td>{team.name}</td>

                                    <td>{team.project}</td>

                                    <td>{team.classroom}</td>

                                    <td>{team.avaliationsQuantity}</td>

                                    <td>{team.average}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                <div className="event-role-container">
                    <div className="role-info">
                        <img src={roleCard} alt="role-card" />

                        <span>{user!.username}: {role}</span>
                    </div>

                    {role === 'Jurado' && <p>Clique na equipe para avaliá-la!</p>}
                </div>
            </div>
        </div>
    );
}

export default Event;