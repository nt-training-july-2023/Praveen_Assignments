import React from 'react'
import axios from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';
import "./Employee.css";

function EmployeeCard() {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
      getAllEmployees();
    }, []);
  
    const getAllEmployees = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/all/Employee"
        );
        console.log(response.data);
        setEmployees(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
  return (
    <div className="container">
    {/* <h2 className="title">List Employees</h2> */}
    {/* <Link to="/addEmployee" className="btn-addEmployee">
      Add Employee
    </Link> */}
    <div className="card-container">
      {employees.map((employee) => (
        <div className="card" key={employee.id}>
          <div className="column">
            <h2>{employee.name}</h2>
            <p><span style={{fontSize:"0.7rem", marginTop:"-0.2rem"}}>{employee.designation}</span></p>
            <p style={{marginTop:"1rem"}}>
              {employee.project ? (
                <p><span style={{fontWeight:"bold"}}>Project Name :</span> {employee.project}</p>
              ) : (
                <p><span style={{fontWeight:"bold"}}>Project Name :</span> N/A</p>
              )}
            </p>
            <p><span  style={{fontWeight:"bold"}}>Manager : </span>ankita</p>
            <p><span  style={{fontWeight:"bold"}}>Contact : </span>{employee.contactNo}</p>
            <p><span  style={{fontWeight:"bold"}}>Email : </span>{employee.email}</p>
            {!employee.project && (
              <p>
                <button className="assign-button">Assign Project</button>
              </p>
            )}
          </div>
          <div className="column">
            <br></br>
            <p style={{ fontSize: "15px",marginTop:"-0.9rem"}}><span style={{fontWeight:"bold"}}>Employee id : </span>{employee.empId}</p>
            <br></br>
            <p style={{marginTop:"0.8rem"}}><span  style={{fontWeight:"bold"}}>DOB : </span>{employee.dob}</p>
            <p><span  style={{fontWeight:"bold"}}>DOJ: </span>{employee.doj}</p>
            <p><span  style={{fontWeight:"bold"}}>Location : </span>{employee.location}</p>
          </div>
        </div>
      ))}
    </div>
  </div>
    
  )
}
export default EmployeeCard


