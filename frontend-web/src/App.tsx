import { AppContextProvider } from "context/AppContextProvider";
import AppRoutes from 'routes';

import './App.css';

function App() {
  return (
    <AppContextProvider>
      <AppRoutes />
    </AppContextProvider>
  );
}

export default App;
