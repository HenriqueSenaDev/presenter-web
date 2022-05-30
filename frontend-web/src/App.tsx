import './App.css';

import { AppContextProvider } from "context/AppContextProvider";
import AppRoutes from 'routes';

function App() {

  return (
    <AppContextProvider>
      <AppRoutes />
    </AppContextProvider>
  );
}

export default App;
