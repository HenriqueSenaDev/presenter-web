import axios from 'axios';
import { IJWT } from 'common/@Interfaces';
import { Context } from 'context/AppContextProvider';
import { useContext } from 'react';

export const useAxios = () => {
    const { JWT, setJWT, user, setAuthenticated } = useContext(Context);

    const axiosInstance = axios.create({
        baseURL: `${import.meta.env.VITE_API_URL}/api`,
    });

    axiosInstance.interceptors.request.use(async req => {
        if (JWT) req.headers!.Authorization = `Bearer ${JWT?.access_token}`
        return req;
    });

    // handling token refreshing
    axiosInstance.interceptors.response.use(res => res, async (error) => {
        const originalRequest = error.config;
        const checkExpiredToken = error.response.data.message.includes('JWT expired');

        if (checkExpiredToken && !originalRequest._retry) {
            originalRequest._retry = true;

            try {
                const refreshRes = await axios.post<IJWT>(`${import.meta.env.VITE_API_URL}/api/auth/refresh`, {
                    refresh_token: JWT!.refresh_token
                });

                const { access_token, refresh_token } = refreshRes.data;
            
                originalRequest.headers.Authorization = `Bearer ${access_token}`;

                setJWT(refreshRes.data);
                localStorage.setItem('presenter_session', JSON.stringify(
                    { access_token, refresh_token, profile: user }
                ));

                return axios(originalRequest);
            }
            catch (error: any) {
                localStorage.removeItem('presenter_session');
                setAuthenticated(false);
            }
        }

        return Promise.reject(error);
    });

    return axiosInstance;
}
