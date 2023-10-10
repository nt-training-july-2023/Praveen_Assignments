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
import UnauthorizedPage from './Pages/Unauthorized';




function App() {
  return (
    <div className="App">
    <Router>
      <Routes>
     
      <Route path="/" element={<Login/>} />
      {/* <Route path="/" element={<LoginPrivateRoute Component={Login} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>} /> */}
      <Route path="/register" element={<AdminRegistration/>} />
      <Route path="/addEmployee" element={ <PrivateRoute Component={AddEmployee} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path="/addProject" element={<PrivateRoute Component={AddProject} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route exact path="/adminDashboard" element={<PrivateRoute Component={AdminDashboard} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>} />
      <Route path="/employeeDashboard" element={<PrivateRoute Component={EmployeeDashboard} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/managerDashboard' element={<PrivateRoute Component={ManagerDashboard} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/assignProject/:employeeId' element={<PrivateRoute Component={AssignProject} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/updateSkill/:employeeId' element={<PrivateRoute Component={UpdateSkills} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/requestResource/:employeeId' element={<PrivateRoute Component={RequestResource} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/resourceRequestedList' element={<PrivateRoute Component={RequestedResourcesList} isLoggegIn={localStorage.getItem('IsLoggedIn')}/>}/>
      <Route path='/unauthorized' element={<UnauthorizedPage/>}/>

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
