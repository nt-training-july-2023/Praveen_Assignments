import React, { useState } from 'react';
import EmployeeCard from '../../components/Cards/Employee';
import ManagerList from '../../components/Cards/AllMangerCards';
import ProjectCard from '../../components/Cards/Project';
import { useNavigate } from 'react-router-dom';
import './ManagerDashboard.css';
import EmployeeCarddd from './Cards/EmployeeCard';

function ManagerDashboard() {
  const [activeTab, setActiveTab] = useState('Employee');
  const navigate = useNavigate();

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
    localStorage.removeItem('email');
    navigate('/');
  };

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'Manager') {
    return <h1>Unauthorized access</h1>;
  }

  return (
    <div className="MD-body-container">
      <div className="MD-nav-bar">
        <div className="sticky-navbar">
            <div>
          <h4>Manager Dashboard</h4>
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
            <button className="MD-logout-btn" onClick={logout}>
              Logout
            </button>
          </div>
        </div>
      </div>
      <div className="MD-card-container">
        {activeTab === 'Employee' && (
          <div>
            <EmployeeCarddd />
          </div>
        )}
        {activeTab === 'Manager' && (
          <div>
            <ManagerList />
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
