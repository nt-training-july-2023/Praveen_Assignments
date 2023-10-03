import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Organization.css";
import employeeService from "../../Service/EmployeeService";

function Organization() {
  const [employees, setEmployees] = useState([]);
  // const [projects, setProjects] = useState([]);

  useEffect(() => {
    getAllEmployees();
  }, []);

  const getAllEmployees = async () => {
    try {
      const response = await employeeService.getAll();
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
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
    <div className="EDcard-container">
      <div className="EDcard-card-container">
        {employees.map((employee) => (
          <div className="EDcard-card" key={employee.id}>
            <div className="EDcard-column">
              <h2>{employee.name}</h2>
              <p>
                <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
                  {employee.designation}
                </span>
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
            <div className="EDcard-column">
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
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Organization;
