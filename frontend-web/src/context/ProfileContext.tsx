import { IJWT, IUserCredentials, IUserProfile } from 'common/@types/profile.types';
import React, { createContext, useEffect, useState } from 'react';
import { usePresenter } from 'hooks/usePresenter';

interface IProfileContext {
   authenticated: boolean, setAuthenticated: Function,
   JWT: IJWT | null, setJWT: Function,
   user: IUserProfile | null,
   handleLogin: Function,
   handleLogout: Function,
}

const ProfileContext = createContext<IProfileContext>({} as IProfileContext);

const ProfileContextProvider = ({ children }: { children: React.ReactNode }) => {
   const [authenticated, setAuthenticated] = useState<boolean>(false);
   const [JWT, setJWT] = useState<IJWT | null>(null);
   const [user, setUser] = useState<IUserProfile | null>(null);

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

   const handleLogout = () => {
      localStorage.removeItem('presenter_session');
      setAuthenticated(false);
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
      <ProfileContext.Provider
         value={{
            authenticated, setAuthenticated,
            JWT, setJWT,
            user,
            handleLogin,
            handleLogout,
         }}>
         {children}
      </ProfileContext.Provider>
   );
}

export { ProfileContext, ProfileContextProvider };