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

interface IParticipation {
   id: {
      event: {
         id: number,
         name: string,
         code: number,
         jurorCorde: number
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

interface IParticipationResponse {
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
   data: ITeam[]
}

interface IContext {
   authenticated: boolean,
   JWT: IJWT | null,
   user: IUser | null,
   event: IEvent | null,
   participations: IParticipation[] | [],
   teams: ITeam[] | null,
   handleLogin: Function,
   handleEvent: Function,
   handleLogout: Function,
   handleTeams: Function,
   handleParticipations: Function,
   handleAddParticipation: Function,
   handleAddAvaliation: Function
}

const Context = createContext<IContext>({
   authenticated: false,
   JWT: null,
   user: null,
   event: null,
   participations: [],
   teams: [],
   handleLogin: () => { },
   handleEvent: () => { },
   handleLogout: () => { },
   handleTeams: () => { },
   handleParticipations: () => { },
   handleAddParticipation: () => { },
   handleAddAvaliation: () => { }
});

const AppContextProvider = ({ children }: Props) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUser | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);
   const [participations, setParticipations] = useState<IParticipation[]>([]);
   const [teams, setTeams] = useState<ITeam[] | null>(null);

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

   const handleParticipations = async () => {
      if (user && JWT) {
         const { data: eventsData } = await api.get(`/api/appusers/participations/${user.id}`, {
            headers: {
               'Authorization': `Bearer ${JWT.access_token}`
            }
         });
         setParticipations(eventsData);
         // console.log(eventsData);
      }
   }

   const handleAddParticipation = async (eventCode: string, jurorCode: string) => {
      const { data, status } = await api.post(`/api/events/participations/add?eventCode=${eventCode}&jurorCode=${jurorCode}&userId=${user?.id}&teamId=1`, {}, {
         headers: {
            'Authorization': `Bearer ${JWT?.access_token}`
         }
      }) as IParticipationResponse;
      // console.log(data);
      // console.log(status);
      await handleParticipations();
   }

   const handleTeams = async () => {
      if (event && JWT) {
         const { data } = await api.get(`/api/events/teams/${event.id}`, {
            headers: {
               'Authorization': `Bearer ${JWT.access_token}`
            }
         }) as ITeamsResponse;
         setTeams(data);
         console.log(data);
      }
   }

   const handleAddAvaliation = async (teamId: number, userId: number, value: number) => {
      const { data, status } = await api.put(`/api/teams/avaliations/add?teamId=${teamId}&userId=${userId}&value=${value}`, {}, {
         headers: {
            'Authorization': `Bearer ${JWT?.access_token}`
         }
      });
      await handleTeams();
      console.log(data);
      // console.log(status);
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
         participations,
         teams,
         handleLogin,
         handleEvent,
         handleLogout,
         handleTeams,
         handleParticipations,
         handleAddParticipation,
         handleAddAvaliation
      }}>
         {children}
      </Context.Provider>
   );
}

export { Context, AppContextProvider };