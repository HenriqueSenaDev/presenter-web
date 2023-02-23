import { useContext, useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { ProfileContext } from "context/ProfileContext";
import { PresenterContext } from "context/PresenterContext";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import Navbar from "components/Navbar";
import RatePopUp from "./RatePopUp";
import "./styles.css";

const columns: GridColDef[] = [
    {
        field: 'id',
        headerName: 'Id',
        type: 'number',
        width: 50
    },
    {
        field: 'equipe',
        headerName: 'Equipe',
        type: 'string',
        width: 220
    },
    {
        field: 'projeto',
        headerName: 'Projeto',
        type: 'string',
        sortable: false,
        width: 220
    },
    {
        field: 'turma',
        headerName: 'Turma',
        type: "string",
        sortable: false,
        width: 170
    },
    {
        field: 'avaliacoes',
        headerName: 'Avaliações',
        type: 'number',
        sortable: false,
        width: 115,
    },
    {
        field: 'media',
        headerName: 'Média Geral',
        type: "number",
        width: 160,
    },
];

interface IRow {
    id: number,
    equipe: string,
    projeto: string,
    turma: string,
    avaliacoes: number,
    media: string
}

interface ITeamInfo {
    id: number,
    name: string
}

const Event = () => {
    const [rows, setRows] = useState<IRow[] | []>([]);
    const [team, setTeam] = useState<ITeamInfo | null>(null);

    const { authenticated } = useContext(ProfileContext);
    const { event } = useContext(PresenterContext);

    useEffect(() => {
        const newRows: IRow[] = event!.teams.map(team => (
            {
                id: team.id,
                equipe: team.name,
                projeto: team.project,
                turma: team.classroom,
                avaliacoes: team.avaliationsQuantity,
                media: team.average.toFixed(2)
            }
        ));
        setRows(newRows);
    }, [event]);

    if (!authenticated) {
        return <Navigate replace to="/" />
    }

    return (
        <div className="event--container">
            {team &&
                <RatePopUp
                    team={team}
                    setTeam={setTeam}
                />
            }

            <Navbar />

            <div className="event--info--container">
                <h1>Clique na equipe para adicionar avaliação.</h1>

                <div className="event--tab--card">
                    <DataGrid
                        sx={{
                            fontSize: "18px",
                            width: 700,
                            color: '#FFFFFF',
                            cursor: 'pointer'
                        }}
                        initialState={{
                            sorting: {
                                sortModel: [{ field: 'media', sort: 'desc' }],
                            },
                        }}
                        hideFooter={true}
                        rows={rows}
                        columns={columns}
                        pageSize={100}
                        rowsPerPageOptions={[100]}
                        onRowClick={(props) => {
                            setTeam({
                                id: Number(props.id),
                                name: props.row.equipe
                            });
                        }}
                    />
                </div>
            </div>
        </div>
    );
}

export default Event;