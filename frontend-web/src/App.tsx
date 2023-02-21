import { AppContextProvider } from "context/AppContextProvider";
import { PresenterContextProvider } from "context/PresenterContext";
import AppRoutes from 'routes';

import './App.css';

function App() {
  return (
    <AppContextProvider>
      <PresenterContextProvider>
        <AppRoutes />
      </PresenterContextProvider>
    </AppContextProvider>
  );
}

export default App;
