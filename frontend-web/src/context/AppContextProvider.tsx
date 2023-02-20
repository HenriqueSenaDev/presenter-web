import React, { createContext, useEffect, useState } from 'react';
import { usePresenter } from 'hooks/usePresenter';
import { IJWT, IUserCredentials, IUserProfile } from 'common/@Interfaces';

interface IEvent {
   id: number,
   name: string
}

interface IEventResponse {
   data: IEvent,
   status: number
}

interface ITeam {
   id: number,
   name: string,
   avaliationsQuantity: number,
   average: number,
   classRoom: string,
   ponctuation: number,
   presented: boolean,
   project: string
}

interface ITeamsResponse {
   data: ITeam[],
   status: number
}

interface IAvaliation {
   id: {
      user: {
         id: number,
         username: string
      },
      team: {
         id: number,
         name: string
      }
   },
   value: number
}

interface IAddAvaliationResponse {
   data: IAvaliation,
   status: number
}

interface IContext {
   authenticated: boolean,
   JWT: IJWT | null,
   user: IUserProfile | null,
   event: IEvent | null,
   teams: ITeam[] | null,
   handleLogin: Function,
   handleEvent: Function,
   setEvent: Function,
   setJWT: Function,
   setAuthenticated: Function,
   handleLogout: Function,
   handleTeams: Function,
   handleAddAvaliation: Function
}

const Context = createContext({} as IContext);

const AppContextProvider = ({ children }: { children: React.ReactNode }) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUserProfile | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);
   const [teams, setTeams] = useState<ITeam[] | null>(null);

   const { signIn } = usePresenter();

   const handleLogin = async (username: string, password: string) => {
      try {
         const userCredentials = (await signIn(username, password));
         const { access_token, refresh_token, profile } = userCredentials;

         localStorage.setItem('presenter_session', JSON.stringify(userCredentials));

         setJWT({ access_token, refresh_token });
         setUser(profile);
         setAuthenticated(true);
      }
      catch (error) {
         console.log('handleLogin:', error);
         return alert('Credenciais de usuário incorretas.');
      }
   }

   const handleEvent = async (eventIp: number) => {
      try {
         // if (user?.roles[0].name === 'ROLE_ADMIN') {
         //    const { data } = await api.get(`/api/events/${eventIp}`) as IEventResponse;
         //    setEvent(data);
         //    // console.log(data);
         // }
         // else {
         //    alert("You don't have access to this page.");
         // }
      } catch (error) {
         console.log('handleEvent:', error);
      }

   }

   const handleTeams = async () => {
      // if (event && JWT) {
      //    const { data } = await api.get(`/api/events/teams/${event.id}`) as ITeamsResponse;
      //    setTeams(data);
      //    // console.log(data);
      // }
   }

   const handleAddAvaliation = async (teamId: number, userId: number, value: number) => {
      // await api.put(`/api/teams/avaliations/add?teamId=${teamId}&userId=${userId}&value=${value}`) as IAddAvaliationResponse;
      // handleTeams();
      // console.log(data);
      // console.log(status);
   }

   const handleLogout = () => {
      localStorage.removeItem('presenter_session');
      setAuthenticated(false);
      setEvent(null);
      setUser(null);
      setJWT(null);
   }

   useEffect(() => {
      const storedCredentials = localStorage.getItem('presenter_session') as string;
      if (storedCredentials) {
         const { access_token, refresh_token, profile } = JSON.parse(storedCredentials) as IUserCredentials;
         setJWT({ access_token, refresh_token });
         setUser(profile);
         setAuthenticated(true);
      }
   }, []);

   return (
      <Context.Provider value={{
         authenticated,
         JWT,
         user,
         event,
         teams,
         setAuthenticated,
         handleLogin,
         handleEvent,
         setEvent,
         setJWT,
         handleLogout,
         handleTeams,
         handleAddAvaliation
      }}>
         {children}
      </Context.Provider>
   );
}

export { Context, AppContextProvider };