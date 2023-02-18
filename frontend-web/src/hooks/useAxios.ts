import axios from 'axios';
import { IJWT } from 'common/@Interfaces';
import { Context } from 'context/AppContextProvider';
import { useContext } from 'react';

export const useAxios = () => {
    const { JWT, setJWT, user } = useContext(Context);

    const axiosInstance = axios.create({
        baseURL: import.meta.env.VITE_API_URL,
    });

    axiosInstance.interceptors.request.use(async req => {
        if (JWT) req.headers!.Authorization = `Bearer ${JWT?.access_token}`
        return req;
    });

    axiosInstance.interceptors.response.use(res => res, async (error) => {
        const originalRequest = error.config;

        if (error.response.status === 403 && !originalRequest._retry) {
            originalRequest._retry = true;

            const refreshRes = await axios.post<IJWT>(`${import.meta.env.VITE_API_URL}/api/auth/refresh`, {
                refresh_token: JWT!.refresh_token
            });
            const { access_token, refresh_token } = refreshRes.data;
            
            originalRequest.headers.Authorization = `Bearer ${access_token}`;

            setJWT(refreshRes.data);
            localStorage.setItem('presenter_session', JSON.stringify(
                { access_token, refresh_token, user }
            ));

            return axios(originalRequest);
        }
        return Promise.reject(error);
    });

    return axiosInstance;
}
