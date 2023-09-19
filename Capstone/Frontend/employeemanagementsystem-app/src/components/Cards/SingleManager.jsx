import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";
// import "./Manager.css";

function SingleManagerCard({employee}) {
  // console.log(employee.id);
  const [projectList, setProjectList] = useState([]);
  const [selectedProject, setSelectedProject] = useState("");

  async function fetchProjectList() {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/projectCards/${employee.id}`
      );

      setProjectList(res.data);
    } catch (error) {
      console.log(error);
    }
  }
  useEffect(() =>{
    fetchProjectList();
},[])
  function handleChange(event) {
    setSelectedProject(event.target.value);
  }

  
 
  return (
    <div className="container">
          <div className="card" key={employee.id}>
            <div className="column">
              <h2>{employee.name}</h2>
              <p>
                <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
                  {employee.designation}
                </span>
              </p>
              <div>
                Project:

                <select
                  name="projectName"
                  className="select"
                  onChange={handleChange}
                  value={selectedProject}
                >
                  <option value="">Select Project</option>
                  {projectList.map((project) => (
                    <option key={project.id} value={project.id}>
                      {project.projectName}
                    </option>
                  ))}
                </select>
              </div>

              <p>
                <span style={{ fontWeight: "bold" }}>L
                ocation : </span>
                {employee.location}
              </p>
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
              <p style={{ fontSize: "15px", marginTop: "-1rem" }}>
                <span style={{ fontWeight: "bold" }}>Employee id : </span>
                {employee.empId}
              </p>
              <br></br>
              <p style={{ marginTop: "1rem" }}>
                <span style={{ fontWeight: "bold" }}>Project Skills : </span>{" "}
                {projectList
                  .filter(
                    (project) => project.id.toString() === selectedProject
                  )
                  .map((project) =>
                    project.requiredSkills.map((skill, index) => (
                      <span key={index}>
                        {index === 0 ? "" : ", "}
                        {skill}
                      </span>
                    ))
                  )}
              </p>
              <p style={{ marginTop: "1rem" }}>
                <span style={{ fontWeight: "bold" }}>Team : </span>{" "}
                {projectList
                  .filter(
                    (project) => project.id.toString() === selectedProject
                  )
                  .map((project) =>
                    project.team.map((skill, index) => (
                      <span key={index}>
                        {index === 0 ? "" : ", "}
                        {skill}
                      </span>
                    ))
                  )}
              </p>
            </div>
          </div>
        
      </div>
  );
}

export default SingleManagerCard;
