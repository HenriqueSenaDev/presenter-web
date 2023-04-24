import { IAvaliation, IEvent, IParticipation } from "common/@types/presenter.types";
import { IUserProfile, IUserCredentials } from "common/@types/profile.types";
import { useAxios } from "hooks/useAxios";

export function usePresenter() {
    const axios = useAxios();

    return {
        register: async (username: string, password: string) => {
            const res = await axios.post<IUserProfile>('/auth/register', {
                username, password
            });
            return res.data;
        },
        signIn: async (username: string, password: string) => {
            const res = await axios.post<IUserCredentials>('/auth/authenticate', {
                username, password
            });
            return res.data;
        },
        findUserParticipations: async () => {
            return (await axios.get<IParticipation[]>(`/user/participations`)).data;
        },
        addJurorParticipation: async (joinCode: string, jurorCode: string) => {
            const res = await axios.put<IParticipation>(`/user/participations/juror`, {
                joinCode, jurorCode
            });
            return res.data;
        },
        addSpectatorParticipation: async (joinCode: string) => {
            const res = await axios.put<IParticipation>(`/user/participations/spectator`, {
                joinCode
            });
            return res.data;
        },
        removeParticipation: async (eventId: number) => {
            await axios.delete(`/user/participations/${eventId}`);
        },
        findEvent: async (eventId: number) => {
            return (await axios.get<IEvent>(`/user/events/${eventId}`)).data;
        },
        addAvaliationToTeam: async (teamId: number, value: number) => {
            const res = await axios.put<IAvaliation>('/user/avaliations', {
                teamId, value
            });
            return res.data;
        }
    }
}