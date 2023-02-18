import { IUserCredentials } from "common/@Interfaces";
import { useAxios } from "hooks/useAxios";

export function usePresenter() {
    const axios = useAxios();

    return {
        signIn: async (username: string, password: string) => {
            return await axios.post<IUserCredentials>('/api/auth/authenticate', {
                username, password
            });
        }
    }
}