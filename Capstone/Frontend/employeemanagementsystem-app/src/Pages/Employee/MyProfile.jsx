import axios from "axios";
import React, { useEffect, useState } from "react";
import "./MyProfile.css";
import { useNavigate } from "react-router-dom";
import employeeService from "../../Service/EmployeeService";

const MyProfile = () => {
  const [employeeData, setEmployeeData] = useState({});
  const id = localStorage.getItem("id");
  useEffect(() => {
    getEmployeeData();
  }, []);
  const navigate = useNavigate();
  
  const getEmployeeData = async () => {
    try {
      const response = await employeeService.getMyProfile(id);
      setEmployeeData(response.data);
    } catch (error) {
      console.log(error);
    }
  };
  

  function reverseDateFormat(inputDate) {
    if (inputDate) {
      const dateParts = inputDate.split("-");
      if (dateParts.length === 3) {
        const reversedDate =
          dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
        return reversedDate;
      }
    }
  }

  return (
    <div className="main">
      <div>
        <h3 className="ID">Employee ID: {employeeData.empId}</h3>
        <div className="details-container">
          <div className="column01 grid-container">
            <div className="column01">
              <strong>Name</strong>
              <p className="field_input">{employeeData.name}</p>

              <strong>Email</strong>
              <p className="field_input">{employeeData.email}</p>

              <strong>DOB</strong>
              <p className="field_input">
                {reverseDateFormat(employeeData.dob)}
              </p>

              <strong>Skills</strong>
              <p className="skills_input">
                {employeeData.skills && employeeData.skills.length > 0
                  ? employeeData.skills.join(", ")
                  : "NA"}
              </p>
              <p>
                <button
                  className="btn"
                  
                  onClick={() =>{
                    navigate(`/updateSkill/${employeeData.id}`, {
                      
                      state: { empName: employeeData.name,
                               empSkills:employeeData.skills },
                    })
                  }}
                
                >
                  Update Skills
                </button>
              </p>
            </div>

            <div className="column01">
              <strong>Contact Number</strong>
              <p className="field_input">{employeeData.contactNo}</p>

              <strong>Project Name</strong>
              <p className="field_input">{employeeData.projectName}</p>

              <strong>Manager</strong>
              <p className="field_input">{employeeData.managerName}</p>

              <strong>DOJ</strong>
              <p className="field_input">
                {reverseDateFormat(employeeData.doj)}
              </p>

              <strong>Location</strong>
              <p className="field_input">{employeeData.location}</p>
            </div>
            {/* </div> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyProfile;
