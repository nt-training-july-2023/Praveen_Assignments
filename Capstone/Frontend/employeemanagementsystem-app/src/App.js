import './App.css';
import React from 'react';
import Admin from './components/Admin/Admin';
import Login from './components/Login/Login';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom'


function App() {
  return (
    <div className="App">
    <Router>
      <Routes>
      <Route path="/" element={<Login/>} />
      <Route path="/register" element={<Admin/>} />
      </Routes>
    </Router>
    </div>

    
  );
}

export default App;
