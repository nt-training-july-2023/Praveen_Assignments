import React, { useState } from 'react';

import { useNavigate } from 'react-router-dom';
import './ManagerDashboard.css';
import EmployeeCarddd from './EmployeeCard';
import ManagerList from '../Admin/AdminCards/AllMangerCards';
import ProjectCard from '../Admin/AdminCards/Project';
import Button from '../../components/Button';
import UnauthorizedPage from '../Unauthorized';


function ManagerDashboard() {
  const [activeTab, setActiveTab] = useState('Employee');
  const navigate = useNavigate();
  const name=localStorage.getItem('name');

  const switchToEmployeeTab = () => {
    setActiveTab('Employee');
  };

  const switchToManagerTab = () => {
    setActiveTab('Manager');
  };

  const switchToProjectTab = () => {
    setActiveTab('Project');
  };

  const logout = () => {
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('id');
    localStorage.removeItem('name');
    navigate('/');
  };

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'Manager') {
    return <UnauthorizedPage/>;
  }

  return (
    <div className="MD-body-container">
      <div className="MD-nav-bar">
        <div className="sticky-navbar">
            <div>
          <h4>Welcome {name}</h4>
          </div>
          <div className="MD-tabs">
            <div
              className={`employee-tab ${activeTab === 'Employee' ? 'active' : ''}`}
              onClick={switchToEmployeeTab}
            >
              EMPLOYEE
            </div>
            <div
              className={`manager-tab ${activeTab === 'Manager' ? 'active' : ''}`}
              onClick={switchToManagerTab}
            >
              MANAGER
            </div>
            <div
              className={`project-tab ${activeTab === 'Project' ? 'active' : ''}`}
              onClick={switchToProjectTab}
            >
              PROJECT
            </div>
          </div>
          <div className="MD-logout-button">
            <Button
             className={"MD-logout-btn"}
             onClick={logout}
             text = {" Logout"}
             />
   
          </div>
        </div>
      </div>
      <div className="MD-card-container">
        {activeTab === 'Employee' && (
          <div className='p'>
            <EmployeeCarddd />
          </div>
        )}
        {activeTab === 'Manager' && (
          <div>
            <ManagerList/>
          </div>
        )}
        {activeTab === 'Project' && (
          <div>
            <ProjectCard />
          </div>
        )}
      </div>
    </div>
  );
}

export default ManagerDashboard;
