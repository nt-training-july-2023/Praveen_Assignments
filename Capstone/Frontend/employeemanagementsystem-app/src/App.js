import './App.css';
import React from 'react';
import AdminRegistration from './components/AdminRegistration/AdminRegistration';
import Login from './components/Login/Login';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AddEmployee from './components/AddEmployee/AddEmployee';
import AdminDashboard from './components/AdminDashboard/AdminDashboard';
import EmployeeCard from './components/Cards/Employee';
import AddProject from './components/AddProject/AddProject';



function App() {
  return (
    <div className="App">
    <Router>
      <Routes>
      <Route path="/" element={<Login/>} />
      <Route path="/register" element={<AdminRegistration/>} />
      <Route path="/addEmployee" element={ <AddEmployee/>}/>
      <Route path="/addProject" element={<AddProject/>}/>
      <Route path="/adminDashBoard" element={<AdminDashboard/>}/>
      </Routes>
    </Router>
    <ToastContainer/>
    </div>

    
  );
}

export default App;
