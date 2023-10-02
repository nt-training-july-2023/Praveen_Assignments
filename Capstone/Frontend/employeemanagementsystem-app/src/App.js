import './App.css';
import React from 'react';

import Login from './Pages/Login/Login';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


import { Navigate } from 'react-router-dom';
import AdminRegistration from './Pages/Admin/AdminRegistration/AdminRegistration';
import RequestedResourcesList from './Pages/Admin/RequestedResourcesList';
import AddProject from './Pages/Admin/AddProject/AddProject';
import AddEmployee from './Pages/Admin/AddEmployee/AddEmployee';
import AssignProject from './Pages/Admin/AssignProject/AssignProject';
import UpdateSkills from './Pages/Employee/UpdateSkills';
import AdminDashboard from './Pages/Admin/AdminDashboard';
import EmployeeDashboard from './Pages/Employee/EmployeeDashboard';
import ManagerDashboard from './Pages/Manager/ManagerDashboard';
import RequestResource from './Pages/Manager/RequestResource';




function App() {
  return (
    <div className="App">
    <Router>
      <Routes>
     
      <Route path="/" element={<Login/>} />
      {/* <Route path="/" element={<LoginPrivateRoute Component={Login} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>} /> */}
      <Route path="/register" element={<AdminRegistration/>} />
      <Route path="/addEmployee" element={ <AddEmployee/>}/>
      <Route path="/addProject" element={<AddProject/>}/>
      <Route exact path="/adminDashboard" element={<PrivateRoute Component={AdminDashboard} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>} />
      <Route path="/employeeDashboard" element={<EmployeeDashboard/>}/>
      <Route path='/managerDashboard' element={<ManagerDashboard/>}/>
      <Route path='/assignProject/:employeeId' element={<AssignProject/>}/>
      <Route path='/updateSkill/:employeeId' element={<UpdateSkills/>}/>
      <Route path='/requestResource/:employeeId' element={<RequestResource/>}/>
      <Route path='/resourceRequestedList' element={<RequestedResourcesList/>}/>

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
// const LoginPrivateRoute =({Component})=> {
//   const isLoggegIn = localStorage.getItem('IsLoggedIn')
//   return isLoggegIn?  <Navigate to = "/" replace /> : <Component />;
// }
// const LoginPrivateRoute =({Component})=> {
//   const isLoggegIn = localStorage.getItem('IsLoggedIn')
//   return isLoggegIn? <Component /> : <Navigate to = "/" replace />;
// }
export default App;
