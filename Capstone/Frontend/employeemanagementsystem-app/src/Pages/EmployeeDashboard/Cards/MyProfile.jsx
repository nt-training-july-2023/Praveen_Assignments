import axios from 'axios';
import React, { useEffect, useState } from 'react'
import "./MyProfile.css"
import { useNavigate } from 'react-router-dom';

const MyProfile = () => {
  const [employeeData,setEmployeeData] = useState({});
  const  email = localStorage.getItem("email");    
  useEffect(()=>{
    getEmployeeData();
  },[])
  const navigate = useNavigate();
  const updateSkill=(employeeId)=>{
    // navigate(`/assignProject?employeeId/${employeeId}`);
    navigate(`/updateSkill/${employeeId}`);
  }

  const getEmployeeData = async() =>{
    try{
        const response = await axios.get(`http://localhost:8080/api/employee/email/${email}`);
        setEmployeeData(response.data);
    }
    catch(error){
        console.log(error);
    }
  }

  function reverseDateFormat(inputDate) {
   if(inputDate)
    { const dateParts = inputDate.split("-");
    if (dateParts.length === 3) {
      const reversedDate =
        dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
      return reversedDate;
    }}
  }

  return (
    <div className="main">
    
      <div>
      <h3 className='ID'>Employee ID: {employeeData.empId}</h3>
        <div className="details-container">
          <div className="column01 grid-container">

          <div className="column01">

            <strong>Name</strong>
            <p className="field_input">{employeeData.name}</p>

            <strong>Email</strong>
            <p className="field_input">{employeeData.email}</p>

            <strong>DOB</strong>
            <p className="field_input">{reverseDateFormat(employeeData.dob)}</p>

            <strong>Skills</strong>
            <p className="skills_input">
            {employeeData.skills && employeeData.skills.length > 0
                  ? employeeData.skills.join(", ")
                  : "NA"}
                  </p>
            <p><button className='btn'onClick={() => updateSkill(employeeData.id)}>Update Skills</button></p>
          </div>

          <div className="column01">

            <strong>Contact Number</strong>
            <p className="field_input">{employeeData.contactNo}</p>

            <strong>Project Name</strong>
            <p className="field_input">{employeeData.projectName}</p>

            <strong>Manager</strong>
            <p className="field_input">{employeeData.managerName}</p>

            <strong>DOJ</strong>
            <p className="field_input">{reverseDateFormat(employeeData.doj)}</p>

            <strong>Location</strong>
            <p className="field_input">{employeeData.location}</p>
               </div>
            {/* </div> */}
         
          </div>
        </div>
      </div>
    
  </div>
  )
}

export default MyProfile