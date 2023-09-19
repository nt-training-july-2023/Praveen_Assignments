import React from 'react'
import axios from 'axios';
import { useEffect } from 'react';
import { useState } from 'react';
import "./Employee.css";
import {  useNavigate } from 'react-router-dom';

function EmployeeCard() {
    const [employees, setEmployees] = useState([]);
    // const [projects, setProjects] = useState([]);
    const navigate = useNavigate();

    const Name='';
    const assign_project=(employeeId)=>{
      // navigate(`/assignProject?employeeId/${employeeId}`);
      navigate(`/assignProject/${employeeId}`);
    }

    useEffect(() => {
      getAllEmployees();
    }, []);
    // useEffect(() => {
    //   getAllProjects();
    // }, []);
  
    const getAllEmployees = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/all/Employee"
        );
        // console.log(response.data);
        setEmployees(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
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
    

      // const getAllProjects = async () => {
      //   try {
      //     const response = await axios.get("http://localhost:8080/api/projectCards");
      //     setProjects(response.data);
      //   } catch (error) {
      //     console.error("Error fetching projects data:", error);
      //   }
      // }
    
  return (
    <div className="container">
    {/* <h2 className="title">List Employees</h2> */}
    {/* <Link to="/addEmployee" className="btn-addEmployee">
      Add Employee
    </Link> */}
    <div className="card-container">
      {employees.map((employee) => (
        <div className="AD-card" key={employee.id}>
          <div className="column">
            <h2>{employee.name}</h2>
            <p><span style={{fontSize:"0.7rem", marginTop:"-0.2rem"}}>{employee.designation}</span></p>
            <p style={{marginTop:"1rem"}}>
              {employee.projectId ? (
                <p><span style={{fontWeight:"bold"}}>Project Name :</span> {employee.projectName}</p>
              ) : (
                <p><span style={{fontWeight:"bold"}}>Project Name :</span> N/A</p>
              )}
            </p>
            {employee.managerName ?(
            <p><span  style={{fontWeight:"bold"}}>Manager : </span>{employee.managerName}</p>
            ) : (
            <p><span style={{fontWeight:"bold"}}>Manager:</span> Ankita</p>

            )}
            <p><span  style={{fontWeight:"bold"}}>Contact : </span>{employee.contactNo}</p>
            <p><span  style={{fontWeight:"bold"}}>Email : </span>{employee.email}</p>
            {/* {!employee.projectId && (
              <p>
                <button className="MD-assign-button" onClick={() => assign_project(employee.id)}>Asign Project</button>
              </p>
            )} */}
          
                          {/* {!employee.project && (
                <p>
                  <select
                    onChange={(e) => {
                      const projectId = e.target.value;
                      // setSelectedProjects({
                      //   ...selectedProjects,
                      //   [employee.id]: projectId,
                      // });
                    }}
                  >
                    <option value="">Select Project</option>
                    {projects.map((project) => (
                      <option key={project.id} value={project.id}>
                        {project.projectName}
                      </option>
                    ))}
                  </select>
                  <button
                    className="assign-button"
                    // onClick={() =>
                    //   handleAssignProject(employee.id, selectedProjects[employee.id])
                    // }
                  >
                    Assign Project
                  </button>
                </p>
                          )} */}

          </div>
          <div className="column">
            <br></br>
            <p style={{ fontSize: "15px",marginTop:"-0.9rem"}}><span style={{fontWeight:"bold"}}>Employee id : </span>{employee.empId}</p>
            <br></br>
            <p style={{marginTop:"0.8rem"}}><span  style={{fontWeight:"bold"}}>DOB : </span>{reverseDateFormat(employee.dob)}</p>
            <p><span  style={{fontWeight:"bold"}}>DOJ: </span>{reverseDateFormat(employee.doj)}</p>
            <p><span  style={{fontWeight:"bold"}}>Location : </span>{employee.location}</p>
            {!employee.projectId && (
              <p>
                <button className="AD-assign-button" onClick={() => assign_project(employee.id)}>Asign Project</button>
              </p>
            )}
          </div>
        </div>
      ))}
    </div>
  </div>
    
  )
}
export default EmployeeCard


