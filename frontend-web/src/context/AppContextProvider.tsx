import React, { createContext, useEffect, useState } from 'react';
import { useAxios } from "hooks/useAxios";

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
   roles: [{
      id: number,
      name: string
   }],
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

interface IParticipation {
   id: {
      event: {
         id: number,
         name: string,
         code: number,
         jurorCorde: number,
         description: string
      },
      user: {
         id: number,
         username: string
      }
   },
   eventRole: {
      id: number,
      name: string
   },
   team: null
}

interface IParticipationsResponse {
   data: IParticipation[],
   status: number
}

interface IAddParticipationResponse {
   data: IParticipation,
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
   user: IUser | null,
   event: IEvent | null,
   participations: IParticipation[] | [],
   teams: ITeam[] | null,
   handleLogin: Function,
   handleUser: Function,
   handleEvent: Function,
   setEvent: Function,
   setJWT: Function,
   handleLogout: Function,
   handleTeams: Function,
   handleParticipations: Function,
   handleAddJurorParticipation: Function,
   handleAddAvaliation: Function
}

const Context = createContext({} as IContext);

const AppContextProvider = ({ children }: Props) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);

   const [JWT, setJWT] = useState<IJWT | null>(() => {
      return localStorage.getItem('presenter_tokens')
         ? JSON.parse(localStorage.getItem('presenter_tokens') as string)
         : null;
   });

   const [user, setUser] = useState<IUser | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);
   const [participations, setParticipations] = useState<IParticipation[]>([]);
   const [teams, setTeams] = useState<ITeam[] | null>(null);

   const api = useAxios();

   const handleLogin = async (username: string, password: string) => {
      try {
         // Getting JWT
         const { data: JWTData } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`) as ILoginResponse;

         // Setting local storage tokens
         localStorage.setItem('presenter_tokens', JSON.stringify(JWTData));
         setJWT(JWTData);
      } catch (error) {
         console.log('handleLogin:', error);
      }
   }

   const handleUser = async () => {
      // Getting User info
      const { data: user } = await api.get(`/api/appusers/findByAccessToken`) as IUserResponse;
      setUser(user);
   }

   const handleEvent = async (eventIp: number) => {
      try {
         if (user?.roles[0].name === 'ROLE_ADMIN') {
            const { data } = await api.get(`/api/events/${eventIp}`) as IEventResponse;
            setEvent(data);
            // console.log(data);
         }
         else {
            alert("You don't have access to this page.");
         }
      } catch (error) {
         console.log('handleEvent:', error);
      }

   }

   const handleParticipations = async () => {
      try {
         if (user && JWT) {
            const { data } = await api.get(`/api/appusers/participations/${user?.id}`) as IParticipationsResponse;
            setParticipations(data);
            // console.log(data);
         }
      } catch (error) {
         console.log('handleParticipations:', error);
      }
   }

   const handleAddJurorParticipation = async (eventCode: string, jurorCode: string) => {
      await api.post(`/api/events/participations/add?eventCode=${eventCode}&jurorCode=${jurorCode}&userId=${user?.id}`) as IAddParticipationResponse;
      // console.log(data);
      // console.log(status);
      await handleParticipations();
   }

   const handleTeams = async () => {
      if (event && JWT) {
         const { data } = await api.get(`/api/events/teams/${event.id}`) as ITeamsResponse;
         setTeams(data);
         // console.log(data);
      }
   }

   const handleAddAvaliation = async (teamId: number, userId: number, value: number) => {
      await api.put(`/api/teams/avaliations/add?teamId=${teamId}&userId=${userId}&value=${value}`) as IAddAvaliationResponse;
      handleTeams();
      // console.log(data);
      // console.log(status);
   }

   const handleLogout = () => {
      localStorage.removeItem('presenter_tokens');
      setAuthenticated(false);
      setEvent(null);
      setUser(null);
      setJWT(null);
   }

   useEffect(() => {
      (async () => {
         if (JWT) {
            await handleUser();
            setAuthenticated(true);
         }
      })();
   }, [JWT]);

   return (
      <Context.Provider value={{
         authenticated,
         JWT,
         user,
         event,
         participations,
         teams,
         handleLogin,
         handleUser,
         handleEvent,
         setEvent,
         setJWT,
         handleLogout,
         handleTeams,
         handleParticipations,
         handleAddJurorParticipation,
         handleAddAvaliation
      }}>
         {children}
      </Context.Provider>
   );
}

export { Context, AppContextProvider };