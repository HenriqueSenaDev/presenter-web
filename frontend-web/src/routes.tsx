import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Landing from 'pages/Landing';
import EventsLibrary from 'pages/EventsLibrary';
import Event from 'pages/Event';

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Landing />} />
        <Route path='/library' element={<EventsLibrary />} />
        <Route path='/event' element={<Event />} />
      </Routes>
    </Router>
  );
}

export default AppRoutes;