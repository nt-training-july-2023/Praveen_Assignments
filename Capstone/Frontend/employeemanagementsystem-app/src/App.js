import './App.css';
import React from 'react';
import AdminHome from './components/AdminHome/AdminHome';
import AdminRegistration from './components/AdminRegistration/AdminRegistration';
import Login from './components/Login/Login';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


function App() {
  return (
    <div className="App">
      {/* <AdminHome/> */}
    <Router>
      <Routes>
      <Route path="/" element={<Login/>} />
      <Route path="/register" element={<AdminRegistration/>} />
      </Routes>
    </Router>
    <ToastContainer/>
    </div>

    
  );
}

export default App;
