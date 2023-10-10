import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Organization.css";
import { toast } from "react-toastify";
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
      if (error.response) {
        console.log(error);
        toast.error(error.response.data.message, {
          position: "top-right",
          autoClose: 3000,
         
        });
      } else {
        toast.error("oops !! server down", {
          position: "top-center",
          autoClose: 3000,
         
        });
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
    <div className="EDcard-container">
      <div className="EDcard-card-container">
        {employees.map((employee) => (
          <div className="EDcard-card" key={employee.id}>
            <div className="EDcard-column">
              <h2 className="EDcard-h2">{employee.name}</h2>
              <p>
                <span className="EDcard-designationSpan">
                  {employee.designation}
                </span>
              </p>

              {employee.managerName ? (
                <p>
                  <span className="EDcard-ManagerNameSpan">Manager : </span>
                  {employee.managerName}
                </p>
              ) : (
                <p>
                  <span className="EDcard-ManagerNameSpan">Manager:</span>{" "}
                  Ankita
                </p>
              )}
              <p>
                <span className="EDcard-ContactSpan">Contact : </span>
                {employee.contactNo}
              </p>
              <p>
                <span className="EDcard-EmailSpan">Email : </span>
                {employee.email}
              </p>
            </div>
            <div className="EDcard-column">
              <br></br>
              <p className="EDcard-EmployeeIdPara">
                <span className="EDcard-EmployeeIdSpan">Employee id : </span>
                {employee.empId}
              </p>
              <br></br>
              <p className="EDcard-DOBPara">
                <span className="EDcard-DOBSpan">DOB : </span>
                {reverseDateFormat(employee.dob)}
              </p>
              <p>
                <span className="EDcard-DOJSpan">DOJ: </span>
                {reverseDateFormat(employee.doj)}
              </p>
              <p>
                <span className="EDcard-LocationSpan">Location : </span>
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
