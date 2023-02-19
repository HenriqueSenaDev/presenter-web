import { IParticipation, IUserCredentials } from "common/@Interfaces";
import { useAxios } from "hooks/useAxios";

export function usePresenter() {
    const axios = useAxios();

    return {
        signIn: async (username: string, password: string) => {
            const res = await axios.post<IUserCredentials>('/auth/authenticate', {
                username, password
            });
            return res.data;
        },
        findUserParticipations: async (userId: number) => {
            const res = await axios.get<IParticipation[]>(`/users/participations/${userId}`);
            return res.data;
        }
    }
}