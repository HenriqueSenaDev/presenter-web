import React, { createContext, useState } from 'react';
import { api } from "services";

interface Props {
   children: React.ReactNode
}

interface IJWT {
   access_token: string,
   refresh_token: string,
}

interface IUserAndJWT {
   access_token: string,
   refresh_token: string,
   user: string,
}

interface ILoginResponse {
   status: number,
   data: IUserAndJWT
}

interface IUser {
   id: number,
   roles: string[],
   username: string
}

interface IUserResponse {
   data: IUser
   status: number
}

interface IEvent {
   id: number,
   name: string
}

interface IEventResponse {
   data: IEvent,
   status: number
}

interface IContext {
   authenticated: boolean,
   JWT: IJWT | null,
   user: IUser | null,
   event: IEvent | null,
   handleLogin: Function,
   handleEvent: Function,
   handleLogout: Function,
}

const Context = createContext<IContext>({
   authenticated: false,
   JWT: null,
   user: null,
   event: null,
   handleLogin: () => { },
   handleEvent: () => { },
   handleLogout: () => { },
});

const AppContextProvider = ({ children }: Props) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUser | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);

   const handleLogin = async (username: string, password: string) => {
      const { data: loginResponse, status } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`) as ILoginResponse;
      if (status === 200) {
         setAuthenticated(true);
         setJWT({
            access_token: loginResponse.access_token,
            refresh_token: loginResponse.refresh_token
         });
         const { data: userResponse, status: userStatus } = await api.get(`/api/appusers?username=${loginResponse.user}`, {
            headers: {
               'Authorization': `Bearer ${loginResponse.access_token}`
            }
         }) as IUserResponse;
         if (userStatus === 200) {
            setUser(userResponse);
         }
         // console.log(userResponse);
         // console.log(loginResponse);
      }

   }

   const handleEvent = async (eventIp: number) => {
      const { status, data } = await api.get(`/api/events/${eventIp}`, {
         headers: {
            'Authorization': `Bearer ${JWT?.access_token}`
         }
      }) as IEventResponse;
      if (status === 200) {
         setEvent(data);
         // console.log(data);
      }
   }

   const handleLogout = () => {
      setAuthenticated(false);
      setEvent(null);
      setJWT(null);
      setUser(null);
   }

   return (
      <Context.Provider value={{
         authenticated,
         JWT,
         user,
         event,
         handleLogin,
         handleEvent,
         handleLogout
      }}>
         {children}
      </Context.Provider>
   );
}

export { Context, AppContextProvider };