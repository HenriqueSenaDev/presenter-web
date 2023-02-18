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
   user: IUserProfile | null,
   event: IEvent | null,
   participations: IParticipation[] | [],
   teams: ITeam[] | null,
   handleLogin: Function,
   handleEvent: Function,
   setEvent: Function,
   setJWT: Function,
   handleLogout: Function,
   handleTeams: Function,
   handleParticipations: Function,
   handleAddJurorParticipation: Function,
   handleRemoveParticipation: Function,
   handleAddAvaliation: Function
}

const Context = createContext({} as IContext);

const AppContextProvider = ({ children }: { children: React.ReactNode }) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUserProfile | null>(null);
   const [event, setEvent] = useState<IEvent | null>(null);
   const [participations, setParticipations] = useState<IParticipation[]>([]);
   const [teams, setTeams] = useState<ITeam[] | null>(null);

   const { signIn } = usePresenter();

   const handleLogin = async (username: string, password: string) => {
      try {
         const userCredentials = (await signIn(username, password)).data;
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

   const handleParticipations = async () => {
      // try {
      //    if (user && JWT) {
      //       const { data } = await api.get(`/api/appusers/participations/${user?.id}`) as IParticipationsResponse;
      //       setParticipations(data);
      //       // console.log(data);
      //    }
      // } catch (error) {
      //    console.log('handleParticipations:', error);
      // }
   }

   const handleAddJurorParticipation = async (eventCode: string, jurorCode: string) => {
      // try {
      //    const data = await api.post(`/api/events/participations/add/juror?eventCode=${eventCode}&jurorCode=${jurorCode}&userId=${user?.id}`) as IAddParticipationResponse;
      //    console.log(data);
      //    // console.log(status);
      //    await handleParticipations();
      // } catch (error) {
      //    console.log('handleAddJurorParticipation:', error);
      //    return alert('Código ou senha do evento estão incorretos.');
      // }
   }

   const handleRemoveParticipation = async (eventId: number) => {
      // try {
      //    const data = await api.delete(`/api/events/participations?userId=${user?.id}&eventId=${eventId}`);
      //    // console.log(data);
      //    await handleParticipations();
      // } catch (error) {
      //    console.log('handleRemoveParticipation:', error);
      // }
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
         participations,
         teams,
         handleLogin,
         handleEvent,
         setEvent,
         setJWT,
         handleLogout,
         handleTeams,
         handleParticipations,
         handleAddJurorParticipation,
         handleRemoveParticipation,
         handleAddAvaliation
      }}>
         {children}
      </Context.Provider>
   );
}

export { Context, AppContextProvider };