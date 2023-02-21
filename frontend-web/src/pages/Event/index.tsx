import "./styles.css";

import Navbar from "components/Navbar";
import RatePopUp from "./RatePopUp";

import { ProfileContext } from "context/ProfileContext";
import { useContext, useEffect, useState } from "react";

import { Navigate } from "react-router-dom";

import { DataGrid, GridColDef } from "@mui/x-data-grid";

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
    const [ratePopUp, setRatePopUp] = useState<boolean>(false);

    const { authenticated } = useContext(ProfileContext);

    // useEffect(() => {
    //     (async () => {
    //         await handleTeams();
    //     })()
    // }, [event]);

    // useEffect(() => {
    //     if (teams) {
    //         const newRows: IRow[] = [];
    //         teams.forEach(team => {
    //             newRows.push({
    //                 id: team.id,
    //                 avaliacoes: team.avaliationsQuantity,
    //                 equipe: team.name,
    //                 media: team.average.toFixed(1),
    //                 projeto: team.project,
    //                 turma: team.classRoom
    //             } as IRow);
    //         });
    //         setRows(newRows);
    //     }
    // }, [teams])

    if (!authenticated) {
        return <Navigate replace to="/" />
    }

    return <h1>Hello World!</h1>;

    return (
        <div className="event--container">
            {ratePopUp && <RatePopUp team={team} setPopUp={setRatePopUp} />}
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
                            setRatePopUp(true);
                            setTeam({
                                id: Number(props.id),
                                name: props.row.equipe
                            });
                            // console.log(props);
                        }}
                    />
                </div>
            </div>
        </div>
    );
}

export default Event;