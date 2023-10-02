import React,{useState} from 'react';
import { useNavigate } from "react-router-dom";
import Organization from './Organization.jsx';
import './EmployeeDashboard.css';
import MyProfile from './MyProfile.jsx';



function EmployeeDashboard() {
  const [activeTab, setActiveTab] = useState("MyProfile");
  const navigate = useNavigate();
  const name=localStorage.getItem('name');

  const switchToMyProfile = () => {
    setActiveTab("MyProfile");
  };

  const switchToOrganizationTab = () => {
    setActiveTab("Organization");
  };


  const logout = () => {
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('id');
    localStorage.removeItem('name');
    navigate("/")
  };

  const userRole = localStorage.getItem('userRole');
  if(userRole!='Employee'){
    return(
      <h1>unauthorized access</h1>
    )
  }
  return (
    <div className="ED-body-container">
      <div className="ED-nav-bar">
        <div>
          <h4>Welcome {name}</h4>
        </div>
        <div className="ED-tabs">
          <div
            className={`myProfile-tab ${
              activeTab === "MyProfile" ? "active" : ""
            }`}
            onClick={switchToMyProfile}
          >
            {" "}
            MY PROFILE
          </div>
          <div
            className={`organization-tab ${activeTab === "Organization" ? "active" : ""}`}
            onClick={switchToOrganizationTab}
          >
            {" "}
            ORGANIZATION
          </div>

          </div>
     
        
         
            <div className= "ED-logout-button">
          <button className="ED-logout-btn" onClick={logout}>
            Logout
          </button>
         
         </div>
         
      </div>
      <div className="ED-card-container">
        {activeTab === "MyProfile" && (
          <div>
            <MyProfile/>
          </div>
        )}
        {activeTab === "Organization" && (
          <div>
            <Organization/>
          </div>
        )}
      </div>
    </div>
  
  )
}

export default EmployeeDashboard