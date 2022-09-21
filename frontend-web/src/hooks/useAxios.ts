import axios from 'axios';
import { Context } from 'context/AppContextProvider';
import { useContext } from 'react';

const useAxios = () => {

    const { setJWT } = useContext(Context);

    const api = axios.create({
        baseURL: "https://presenter-api.herokuapp.com"
    });

    api.interceptors.request.use(async req => {
        const tokens = JSON.parse(localStorage.getItem('presenter_tokens') as string);
        if (tokens) {
            req.headers!.Authorization = `Bearer ${tokens.access_token}`
        }

        return req;
    });

    api.interceptors.response.use(res => res, async (error) => {
        const originalRequest = error.config;

        if (error.response.status === 403 && !originalRequest._retry) {
            originalRequest._retry = true;

            const tokens = JSON.parse(localStorage.getItem('presenter_tokens') as string);

            const response = await axios.get('https://presenter-api.herokuapp.com/api/refreshtoken', {
                headers: {
                    'Authorization': `Bearer ${tokens.refresh_token}`
                }
            });

            originalRequest.headers.Authorization = `Bearer ${response.data.access_token}`;

            localStorage.setItem('presenter_tokens', JSON.stringify(response.data));

            return axios(originalRequest);
        }
        return Promise.reject(error);
    });

    return api;
}

export { useAxios };
