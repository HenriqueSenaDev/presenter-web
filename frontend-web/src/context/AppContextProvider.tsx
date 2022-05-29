import React, { createContext, useState } from 'react';
import { api } from "services";

interface IContext {
    authenticated: boolean,
    JWT: {
        access_token: string,
        refresh_token: string
    } | null,
    handleLogin: Function
}

const inicialValue = {
    authenticated: false,
    JWT: null,
    handleLogin: () => { }
}

const Context = createContext<IContext>(inicialValue);

interface Props {
    children: React.ReactNode
}

const AppContextProvider = ({ children }: Props) => {
    const [authenticated, setAuthenticated] = useState(false);
    const [JWT, setJWT] = useState(null);

    const handleLogin = async (username: string, password: string) => {
        const { status, data } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`);
        console.log(data);
        if (status == 200) {
            setAuthenticated(true);
            setJWT(data);
        }
    }

    return (
        <Context.Provider value={{
            authenticated,
            JWT,
            handleLogin
        }}>
            {children}
        </Context.Provider>
    );
}

export { Context, AppContextProvider };