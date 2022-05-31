import React, { createContext, useState } from 'react';
import { api } from "services";

interface IUserAndJWT {
    access_token: string,
    refresh_token: string,
    user: String,
}

interface IContext {
    authenticated: boolean,
    userAndJWT: {
        access_token: string,
        refresh_token: string,
        user: String
    } | null,
    event: object | null,
    handleLogin: Function,
    handleEvent: Function
}

interface Props {
    children: React.ReactNode
}

const inicialValue = {
    authenticated: false,
    userAndJWT: null,
    event: null,
    handleLogin: () => { },
    handleEvent: () => { }
}

const Context = createContext<IContext>(inicialValue);


const AppContextProvider = ({ children }: Props) => {
    const [authenticated, setAuthenticated] = useState<boolean>(false);
    const [userAndJWT, setUserAndJWT] = useState<IUserAndJWT | null>(null);
    const [event, setEvent] = useState(null);

    const handleLogin = async (username: string, password: string) => {
        const { status, data } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`);
        if (status === 200) {
            setAuthenticated(true);
            setUserAndJWT(data);
        }
    }

    const handleEvent = async (eventIp: number) => {
        const { status, data } = await api.get(`/api/events/${eventIp}`, {
            headers: {
                'Authorization': `Bearer ${userAndJWT?.access_token}`
            }
        });
        if (status === 200) {
            setEvent(data);
        }
    }

    return (
        <Context.Provider value={{
            authenticated,
            userAndJWT,
            event,
            handleLogin,
            handleEvent,
        }}>
            {children}
        </Context.Provider>
    );
}

export { Context, AppContextProvider };