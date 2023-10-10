import React from "react";
import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import "./Employee.css";
import { useNavigate } from "react-router-dom";
import UnassignPopup from "../../../components/Popup/UnassignPopup";
import adminService from "../../../Service/AdminService";
import Button from "../../../components/Button";

function EmployeeCard() {
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
 
  const navigate = useNavigate();
  const openUnassignPopup = (employee) => {
    setSelectedEmployee(employee);
    setIsPopupOpen(true);
  };

  
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
  
 
  const handleUnassignEmployee = async () => {
    if (selectedEmployee) {
      try {
        const id = selectedEmployee.id
        const response = await adminService.unassignProject(id);
        getAllEmployees();
        closeUnassignPopup(); 
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
  };
  

  function reverseDateFormat(inputDate) {
    const dateParts = inputDate.split("-");
    if (dateParts.length === 3) {
      const reversedDate =
        dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
      return reversedDate;
    } else {
      return "Invalid Date Format";
    }
  }

  return (
    <div className="AC-Employee-container">
      <div className="AC-Employee-card-container">
        {employees.map((employee) => (
          <div className="AC-Employee-card" key={employee.id}>
            <div className="AC-Employee-column1">
              <h2 className="AC-Employee-h2">{employee.name}</h2>
              <p>
                <span className="AC-Employee-designationSpan">
                  {employee.designation}
                </span>
              </p>
              <p className="AC-Employee-projectNamePara">
                {employee.projectId ? (
                  <p>
                    <span className="AC-Employee-projectNameSpan">Project Name :</span>{" "}
                    {employee.projectName}
                  </p>
                ) : (
                  <p>
                    <span className="AC-Employee-projectNameSpan">Project Name :</span>{" "}
                    N/A
                  </p>
                )}
              </p>
              {employee.managerName ? (
                <p>
                  <span className="AC-Employee-managerNameSpan">Manager : </span>
                  {employee.managerName}
                </p>
              ) : (
                <p>
                  <span className="AC-Employee-managerNameSpan">Manager:</span> Ankita
                </p>
              )}
              <p>
                <span className="AC-Employee-contactSpan">Contact : </span>
                {employee.contactNo}
              </p>
              <p>
                <span className="AC-Employee-emailSpan">Email : </span>
                {employee.email}
              </p>
            </div>
            <div className="AC-Employee-column2">
             
              <p className="AC-Employee-EmployeeIdPara">
                <span className="AC-Employee-employeeIdSpan">Employee id : </span>
                {employee.empId}
              </p>
              <br></br>
              <p className="AC-Employee-DOB-Para">
                <span className="AC-Employee-DOBSpan">DOB : </span>
                {reverseDateFormat(employee.dob)}
              </p>
              <p>
                <span className="AC-Employee-DOJSpan">DOJ: </span>
                {reverseDateFormat(employee.doj)}
              </p>
              <p>
                <span className="AC-Employee-locationSpan">Location : </span>
                {employee.location}
              </p>
              {employee.projectId ? (
                <p>
                  <Button
                    className={"AC-Employee-Unassign-button"}
                    onClick={() => openUnassignPopup(employee)}
                    text={"Unassign"}
                  />
                </p>
              ) : (
                <p>
                  <Button
                    className={"AC-Employee-assign-button"}
                    onClick={() => {
                      navigate(`/AssignProject/${employee.id}`, {
                        state: { empName: employee.name },
                      });
                    }}
                    text={"Assign Project"}
                  />
                </p>
              )}
            </div>
          </div>
        ))}
      </div>
      {isPopupOpen && selectedEmployee && (
        <UnassignPopup
          description={`Do you really want to unassign project to ${selectedEmployee.name}?`}
          onClose={closeUnassignPopup} 
          onUnassign={handleUnassignEmployee} 
        />
      )}
    </div>
  );
}
export default EmployeeCard;
