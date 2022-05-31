import React, { createContext, useState } from 'react';
import { api } from "services";

interface IContext {
    authenticated: boolean,
    userAndJWT: {
        access_token: string,
        refresh_token: string,
        user: String
    } | null,
    handleLogin: Function
}

interface Props {
    children: React.ReactNode
}

const inicialValue = {
    authenticated: false,
    userAndJWT: null,
    handleLogin: () => { }
}

const Context = createContext<IContext>(inicialValue);


const AppContextProvider = ({ children }: Props) => {
    const [authenticated, setAuthenticated] = useState<boolean>(false);
    const [userAndJWT, setUserAndJWT] = useState(null);

    const handleLogin = async (username: string, password: string) => {
        const { status, data } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`);
        if (status === 200) {
            setAuthenticated(true);
            setUserAndJWT(data);
        }
    }

    return (
        <Context.Provider value={{
            authenticated,
            userAndJWT,
            handleLogin
        }}>
            {children}
        </Context.Provider>
    );
}

export { Context, AppContextProvider };