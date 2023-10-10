import React, { useState, useEffect } from "react";
import managerService from "../../../Service/ManagerService";

function SingleManagerCard({ employee }) {
  const [projectList, setProjectList] = useState([]);
  const [selectedProject, setSelectedProject] = useState("");
  const [selectedProjectData, setSelectedProjectData] = useState(null);
  const [showDropdown, setShowDropdown] = useState(true);

  async function fetchProjectList() {
    try {
      const response = await managerService.projectByManagerId(employee.id);
      const projectData = response.data;

      if (projectData.length > 0) {
        const firstProject = projectData[0];
        setSelectedProjectData(firstProject);
        setSelectedProject(firstProject.id);
        setShowDropdown(projectData.length > 1);
      }

      setProjectList(projectData);
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    fetchProjectList();
  }, []);

  function handleChange(event) {
    const selectedProjectId = event.target.value;
    setSelectedProject(selectedProjectId);
    const projectData = projectList.find(
      (project) => project.id.toString() === selectedProjectId
    );

    if (projectData) {
      setSelectedProjectData(projectData);
    } else {
      setSelectedProjectData(null);
    }
  }

  const noProjects = projectList.length === 0;

  return (
    <div className="AC-SM-container">
      <div className="AC-SM-card" key={employee.id}>
        <div className="AC-SM-column1">
          <h2 className="AC-SM-h2">{employee.name}</h2>
          <p>
            <span className="AC-SM-DesignationSpan">
              {employee.designation}
            </span>
          </p>
          <div>
            <strong>Project : </strong>
            {noProjects ? (
              <span>N/A</span>
            ) : showDropdown ? (
              <select
                style={{ marginTop: "0.5rem" }}
                name="projectName"
                className="select"
                onChange={handleChange}
                value={selectedProject}
              >
                {projectList.map((project) => (
                  <option key={project.id} value={project.id}>
                    {project.projectName}
                  </option>
                ))}
              </select>
            ) : (
              <span>{selectedProjectData?.projectName}</span>
            )}
          </div>
          <p>
            <span className="AC-SM-LocationSpan">Location : </span>
            {employee.location}
          </p>
          <p>
            <span className="AC-SM-ConatctSpan">Contact : </span>
            {employee.contactNo}
          </p>
          <p>
            <span className="AC-SM-EmailSpan">Email : </span>
            {employee.email}
          </p>
        </div>
        <div className="AC-SM-column2">
          <br></br>
          <p className="AC-SM-EmployeeIdPara">
            <span className="AC-SM-EmployeeIdSpan">Employee id : </span>
            {employee.empId}
          </p>
          <br></br>
          <p className="AC-SM-ProjectSkillsPara">
            <span className="AC-SM-ProjectSkillsSpan">Project Skills : </span>{" "}
            {selectedProjectData?.requiredSkills
              ? selectedProjectData.requiredSkills.join(", ")
              : "N/A"}
          </p>
          <p className="AC-SM-TeamPara">
            <span className="AC-SM-TeamSpan">Team : </span>
            {selectedProjectData?.team
              ? selectedProjectData.team.join(", ")
              : "N/A"}
          </p>
        </div>
      </div>
    </div>
  );
}

export default SingleManagerCard;
