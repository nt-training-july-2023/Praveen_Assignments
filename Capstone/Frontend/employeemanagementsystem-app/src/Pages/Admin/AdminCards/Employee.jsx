import React from "react";
import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import "./Employee.css";
import { useNavigate } from "react-router-dom";
import UnassignPopup from "../../../components/Popup/UnassignPopup";
import adminService from "../../../Service/AdminService";

function EmployeeCard() {
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  // const [projects, setProjects] = useState([]);
  const navigate = useNavigate();

  // Function to open the unassign confirmation popup and select the employee for unassignment
  const openUnassignPopup = (employee) => {
    setSelectedEmployee(employee);
    setIsPopupOpen(true);
  };

  // Function to close the unassign confirmation popup
  const closeUnassignPopup = () => {
    setIsPopupOpen(false);
    setSelectedEmployee(null);
  };


  useEffect(() => {
    getAllEmployees();
  }, []);
  
  const getAllEmployees = async () => {
    try {
      const response = await adminService.getAllEmployees();
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  
  // Function to handle unassigning an employee
  const handleUnassignEmployee = async () => {
    if (selectedEmployee) {
      try {
        const id = selectedEmployee.id
        const response = await adminService.unassignProject(id);
        getAllEmployees();
        closeUnassignPopup(); // Close the popup after successful unassignment
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
  };
  

  function reverseDateFormat(inputDate) {
    // Split the input date using the '-' separator
    const dateParts = inputDate.split("-");

    // Check if the input has three parts (year, month, day)
    if (dateParts.length === 3) {
      // Reverse the parts and join them with '-' separator
      const reversedDate =
        dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
      return reversedDate;
    } else {
      // Handle invalid input format
      return "Invalid Date Format";
    }
  }

  return (
    <div className="container">
      <div className="card-container">
        {employees.map((employee) => (
          <div className="AD-card" key={employee.id}>
            <div className="column">
              <h2>{employee.name}</h2>
              <p>
                <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
                  {employee.designation}
                </span>
              </p>
              <p style={{ marginTop: "1rem" }}>
                {employee.projectId ? (
                  <p>
                    <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
                    {employee.projectName}
                  </p>
                ) : (
                  <p>
                    <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
                    N/A
                  </p>
                )}
              </p>
              {employee.managerName ? (
                <p>
                  <span style={{ fontWeight: "bold" }}>Manager : </span>
                  {employee.managerName}
                </p>
              ) : (
                <p>
                  <span style={{ fontWeight: "bold" }}>Manager:</span> Ankita
                </p>
              )}
              <p>
                <span style={{ fontWeight: "bold" }}>Contact : </span>
                {employee.contactNo}
              </p>
              <p>
                <span style={{ fontWeight: "bold" }}>Email : </span>
                {employee.email}
              </p>
            </div>
            <div className="column">
              <br></br>
              <p style={{ fontSize: "15px", marginTop: "-0.9rem" }}>
                <span style={{ fontWeight: "bold" }}>Employee id : </span>
                {employee.empId}
              </p>
              <br></br>
              <p style={{ marginTop: "0.8rem" }}>
                <span style={{ fontWeight: "bold" }}>DOB : </span>
                {reverseDateFormat(employee.dob)}
              </p>
              <p>
                <span style={{ fontWeight: "bold" }}>DOJ: </span>
                {reverseDateFormat(employee.doj)}
              </p>
              <p>
                <span style={{ fontWeight: "bold" }}>Location : </span>
                {employee.location}
              </p>
              {employee.projectId ? (
                <p>
                  <button
                    className="AD-assign-button"
                    onClick={() => openUnassignPopup(employee)} // Open the unassign confirmation popup
                  >
                    Unassign
                  </button>
                </p>
              ) : (
                <p>
                  <button
                    className="AD-assign-button"
                    onClick={() => {
                      navigate(`/AssignProject/${employee.id}`, {
                        state: { empName: employee.name },
                      });
                    }}
                  >
                    Assign Project
                  </button>
                </p>
              )}
            </div>
          </div>
        ))}
      </div>
      {isPopupOpen && selectedEmployee && (
        <UnassignPopup
          description={`Do you really want to unassign project to ${selectedEmployee.name}?`}
          onClose={closeUnassignPopup} // Close the unassign confirmation popup
          onUnassign={handleUnassignEmployee} // Handle unassignment when confirmed
        />
      )}
    </div>
  );
}
export default EmployeeCard;
