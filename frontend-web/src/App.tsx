import Landing from 'pages/Landing';
import './App.css';

import { AppContextProvider } from "context/AppContextProvider";

function App() {

  return (
    <AppContextProvider>
      <Landing />
    </AppContextProvider>
  );
}

export default App;
