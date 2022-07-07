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
   user: IUser | null,
   event: IEvent | null,
   participations: IParticipation[] | [],
   teams: ITeam[] | null,
   handleLogin: Function,
   handleEvent: Function,
   setEvent: Function,
   handleLogout: Function,
   handleTeams: Function,
   handleParticipations: Function,
   handleAddJurorParticipation: Function,
   handleAddAvaliation: Function
}

const Context = createContext({} as IContext);

const AppContextProvider = ({ children }: Props) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUser | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);
   const [participations, setParticipations] = useState<IParticipation[]>([]);
   const [teams, setTeams] = useState<ITeam[] | null>(null);

   const handleLogin = async (username: string, password: string) => {
      try {
         // Getting JWT
         const { data: JWTData } = await api.post(`/login?username=${username.trim()}&password=${password.trim()}`) as ILoginResponse;
         setAuthenticated(true);
         setJWT({
            access_token: JWTData.access_token,
            refresh_token: JWTData.refresh_token
         });
         // Setting global JWT in axios config
         api.defaults.headers.common['Authorization'] = `Bearer ${JWTData.access_token}`;
         // Getting User info
         const { data: user } = await api.get(`/api/appusers?username=${JWTData.user}`) as IUserResponse;
         setUser(user);
      } catch (error) {
         console.log('handleLogin:', error);
      }
   }

   // const handleRefreshToken = async () => {
   //    try {
   //       const { data } = await api.get('/api/refreshtoken', {
   //          headers: {
   //             'Authorization': `Bearer ${JWT?.refresh_token}`
   //          }
   //       }) as IRefreshTokenResponse;
   //       setJWT(data);
   //       api.defaults.headers.common['Authorization'] = `Bearer ${data.access_token}`;
   //    } catch (error) {
   //       console.log('Refresh Error:', error);
   //    }
   // }

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
      setAuthenticated(false);
      setEvent(null);
      setUser(null);
      setJWT(null);
   }

   return (
      <Context.Provider value={{
         authenticated,
         user,
         event,
         participations,
         teams,
         handleLogin,
         handleEvent,
         setEvent,
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