import Navbar from "components/Navbar";
import "./styles.css";
import { DataGrid, GridColDef } from '@mui/x-data-grid';

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
        width: 200
    },
    {
        field: 'projeto',
        headerName: 'Projeto',
        type: 'string',
        sortable: false,
        width: 240
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
        sortable: false,
        width: 135,
    },
];

const rows = [
    {
        id: 1,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 2,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 3,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 4,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 5,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 6,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
    {
        id: 7,
        equipe: 'Procariontes',
        projeto: 'Presenter',
        turma: '2º Informática',
        avaliacoes: 3,
        media: 10.00
    },
];

const Event = () => {
    return (
        <div className="event--container">
            <Navbar />
            <div className="event--info--container">
                <h1>Clique na equipe para adicionar avaliação.</h1>
                <div className="event--tab--card">
                    <DataGrid
                        sx={{
                            fontSize: "18px",
                            width: 700,
                            color: '#FFFFFF',
                        }}
                        hideFooter={true}
                        rows={rows}
                        columns={columns}
                        pageSize={20}
                        rowsPerPageOptions={[20]}
                        onRowClick={(params) => {
                            console.log("params: ", params)
                        }}
                    />
                </div>
            </div>
        </div>
    );
}

export default Event;