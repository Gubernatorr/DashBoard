import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import AdvertisementDashboard from './components/AdvertisementDashboard';
import Home from './components/Home';
import About from './components/About';
import Header from './components/Header'; 
import './App.css'; 

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <Header /> 
        <nav className="navbar"> 
          <ul>
            <li><Link to="/" className="nav-link">Home</Link></li>
            <li><Link to="/about" className="nav-link">About</Link></li>
            <li><Link to="/dashboard" className="nav-link">Dashboard</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/dashboard" element={<AdvertisementDashboard />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
