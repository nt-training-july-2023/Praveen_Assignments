import React from "react";

import { useState } from "react";
import { Link } from "react-router-dom";
import "./AdminDashboard.css";
import { useNavigate } from "react-router-dom";

import ManagerList from "./AdminCards/AllMangerCards";
import ProjectCard from "./AdminCards/Project";
import EmployeeCard from "./AdminCards/Employee";
import Button from "../../components/Button";

function AdminDashboard() {
  const [activeTab, setActiveTab] = useState("Employee");
  const navigate = useNavigate();
  const name = localStorage.getItem("name");
  const toAddEmployee = () => {
    navigate("/AddEmployee");
  };
  const toAddManager = () => {
    navigate("/AddEmployee");
  };
  const toAddProject = () => {
    navigate("/AddProject");
  };
  const switchToEmployeeTab = () => {
    setActiveTab("Employee");
  };

  const switchToManagerTab = () => {
    setActiveTab("Manager");
  };

  const switchToProjectTab = () => {
    setActiveTab("Project");
  };

  const logout = () => {
    localStorage.removeItem("IsLoggedIn");
    localStorage.removeItem("userRole");
    localStorage.removeItem("id");
    localStorage.removeItem("name");
    navigate("/");
  };

  const RR = () => {
    navigate("/resourceRequestedList");
  };

  const userRole = localStorage.getItem("userRole");
  if (userRole != "Admin") {
    return (
      // <div>UnauthrizedAccess</div>
      <h1>unauthorized access</h1>
    );
  }

  return (
    <div className="body-container">
      <div className="nav-bar">
        <div>
          <h4>Welcome {name}</h4>
          <Button 
          className={"RR-btn"}
           onClick={RR}
           text ={"Request List"}
           />
            
        </div>
        <div className="tabs">
          <div
            className={`employee-tab ${
              activeTab === "Employee" ? "active" : ""
            }`}
            onClick={switchToEmployeeTab}
          >
            {" "}
            EMPLOYEE
          </div>
          <div
            className={`manager-tab ${activeTab === "Manager" ? "active" : ""}`}
            onClick={switchToManagerTab}
          >
            {" "}
            MANAGER
          </div>
          <div
            className={`project-tab ${activeTab === "Project" ? "active" : ""}`}
            onClick={switchToProjectTab}
          >
            {" "}
            PROJECT
          </div>
        </div>
        <div className="empty-div">
          <div className="tab-buttons">
            <div className="logout-container">
              <Button 
              className={"logout-btn"}
               onClick={logout}
                text ={"Logout"}
              />
            </div>
            <div className="emp-container">
              <Button
                className={`add-employee-btn ${
                  activeTab === "Employee" ? "active" : ""
                }`}
                onClick={toAddEmployee}
                text ={"Add Employee"}
              />
                
             
              <Button
                className={`add-manager-btn ${
                  activeTab === "Manager" ? "active" : ""
                }`}
                // onClick={toAddManager}
                text ={"Add Manager"}
              />
               
             
              <Button
                className={`add-project-btn ${
                  activeTab === "Project" ? "active" : ""
                }`}
                onClick={toAddProject}
                text=" Add Project"
              />
            </div>
          </div>
        </div>
      </div>
      <div className="card-container">
        {activeTab === "Employee" && (
          <div>
            <EmployeeCard />
          </div>
        )}
        {activeTab === "Manager" && (
          <div>
            <ManagerList />
          </div>
        )}
        {activeTab === "Project" && (
          <div>
            <ProjectCard />
          </div>
        )}
      </div>
    </div>
  );
}

export default AdminDashboard;
