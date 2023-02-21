import { ProfileContextProvider } from "context/ProfileContext";
import { PresenterContextProvider } from "context/PresenterContext";
import AppRoutes from 'routes';
import './App.css';

function App() {
  return (
    <ProfileContextProvider>
      <PresenterContextProvider>
        <AppRoutes />
      </PresenterContextProvider>
    </ProfileContextProvider>
  );
}

export default App;
