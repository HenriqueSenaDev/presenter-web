import "./styles.css";

import Navbar from "components/Navbar";
import RatePopUp from "./RatePopUp";

import { Context } from "context/AppContextProvider";
import { useContext, useEffect, useState } from "react";

import { Navigate } from "react-router-dom";

import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { api } from "services";

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

interface ITeam {
    id: number,
    name: string,
    avaliations: number,
    average: number,
    classRoom: string,
    ponctuation: number,
    presented: boolean,
    project: string
}

interface ITeamsResponse {
    data: ITeam[]
}

interface IRow {
    id: number,
    equipe: string,
    projeto: string,
    turma: string,
    avaliacoes: number,
    media: number
}

const Event = () => {
    const [teams, setTeams] = useState<ITeam[] | null>(null);
    const [rows, setRows] = useState<IRow[] | []>([]);

    const { authenticated, event, JWT } = useContext(Context);

    useEffect(() => {
        (async () => {
            if (event && JWT) {
                const { data } = await api.get(`/api/events/teams/${event.id}`, {
                    headers: {
                        'Authorization': `Bearer ${JWT.access_token}`
                    }
                }) as ITeamsResponse;
                setTeams(data);
                // console.log(data);
            }
        })()
    }, [event]);

    useEffect(() => {
        if (teams) {
            const newRows: IRow[] = [];
            teams.forEach(team => {
                newRows.push({
                    id: team.id,
                    avaliacoes: team.avaliations,
                    equipe: team.name,
                    media: team.average,
                    projeto: team.project,
                    turma: team.classRoom
                } as IRow);
            });
            setRows(newRows);
        }
    }, [teams])

    if (!authenticated) {
        return <Navigate replace to="/" />
    }

    return (
        <div className="event--container">
            <RatePopUp />
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
                        pageSize={20}
                        rowsPerPageOptions={[20]}
                        onRowClick={(params) => {
                            document.querySelector(".rate--popup--container")?.classList.remove('isHidden');
                            document.querySelector(".rate--popup--container")?.classList.add('isShow');
                            // console.log("params: ", params)
                        }}
                    />
                </div>
            </div>
        </div>
    );
}

export default Event;