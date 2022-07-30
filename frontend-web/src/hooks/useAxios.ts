import axios from 'axios';

const useAxios = () => {

    const api = axios.create({
        baseURL: "http://localhost:8080"
    });
    
    api.interceptors.request.use(async req => {
        const tokens = JSON.parse(localStorage.getItem('presenter_tokens') as string);
        if (tokens) {
            req.headers!.Authorization = `Bearer ${tokens.access_token}`
        }

        return req;
    });

    return api;
}

export { useAxios };
