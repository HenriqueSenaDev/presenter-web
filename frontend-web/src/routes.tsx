import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import Landing from 'pages/Landing';
import EventsLibrary from 'pages/EventsLibrary';
import Event from 'pages/Event';
import { useContext } from 'react';
import { Context } from 'context/AppContextProvider';

const AppRoutes = () => {

  const { authenticated } = useContext(Context);

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