import './App.css';
import React from 'react';
import AdminRegistration from './components/AdminRegistration/AdminRegistration';
import Login from './components/Login/Login';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AddEmployee from './components/AddEmployee/AddEmployee';
import AdminDashboard from './components/AdminDashboard/AdminDashboard';
import AddProject from './components/AddProject/AddProject';
import EmployeeDashboard from './Pages/EmployeeDashboard/EmployeeDashboard';
import Main_Header from './components/Header/Main_Header';
import { Navigate } from 'react-router-dom';
import ManagerDashboard from './Pages/ManagerDashboard/ManagerDashboard';
import AssignProject from './components/AssignProject/AssignProject';
import organization from './Pages/EmployeeDashboard/Cards/Organization';
import UpdateSkills from './Pages/EmployeeDashboard/UpdateSkills';



function App() {
  return (
    <div className="App">
    <Router>
      <Routes>
     
      <Route path="/" element={<Login/>} />
      <Route path="/register" element={<AdminRegistration/>} />
      <Route path="/addEmployee" element={ <AddEmployee/>}/>
      <Route path="/addProject" element={<AddProject/>}/>
      <Route exact path="/adminDashboard" element={<PrivateRoute Component={AdminDashboard} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>} />
      <Route path="/employeeDashboard" element={<EmployeeDashboard/>}/>
      <Route path='/managerDashboard' element={<ManagerDashboard/>}/>
      <Route path='/assignProject/:employeeId' element={<AssignProject/>}/>
      <Route path='/updateSkill/:employeeId' element={<UpdateSkills/>}/>

      </Routes>
    </Router>
    <ToastContainer/>
    </div>

    
  );
}

const PrivateRoute =({Component})=> {
  const isLoggegIn = localStorage.getItem('IsLoggedIn')
  return isLoggegIn? <Component /> : <Navigate to = "/" replace />;
}

export default App;
