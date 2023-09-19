import React,{useState} from 'react';
import { useNavigate } from "react-router-dom";
import Organization from './Cards/Organization.jsx';
import './EmployeeDashboard.css';
import MyProfile from './Cards/MyProfile.jsx';



function EmployeeDashboard() {
  const [activeTab, setActiveTab] = useState("MyProfile");
  const navigate = useNavigate();

  const switchToMyProfile = () => {
    setActiveTab("MyProfile");
  };

  const switchToOrganizationTab = () => {
    setActiveTab("Organization");
  };


  const logout = () => {
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('email');
    navigate("/")
  };

  const userRole = localStorage.getItem('userRole');
  if(userRole!='Employee'){
    return(
      // <div>UnauthrizedAccess</div>
      <h1>unauthorized access</h1>
    )
  }
  return (
    <div className="ED-body-container">
      <div className="ED-nav-bar">
        <div>
          <h4>Employee Dashboard</h4>
        </div>
        <div className="ED-tabs">
          <div
            className={`myProfile-tab ${
              activeTab === "MyProfile" ? "active" : ""
            }`}
            onClick={switchToMyProfile}
          >
            {" "}
            MYPROFILE
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