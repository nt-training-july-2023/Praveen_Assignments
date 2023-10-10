import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import "./Project.css";
import Popup from "../../../components/Popup/Popup";
import adminService from "../../../Service/AdminService";

function ProjectCard() {
  const [projects, setProjects] = useState([]);
  const [selectedDescription, setSelectedDescription] = useState("");
  const [showPopup, setShowPopup] = useState(false);

  useEffect(() => {
    // getAllProjects();
    return()=>{
      getAllProjects();
    }

  
  }, []);

  const getAllProjects = async () => {
    try {
      const response = await adminService.getProjects();
      setProjects(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  

  const handleReadMoreClick = (description) => {
    setSelectedDescription(description);
    setShowPopup(true);
  };

  const handlePopupClose = () => {
    setShowPopup(false);
    setSelectedDescription("");
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
    <div className="AC-Project-container">
      <div className="AC-Project-card-container">
        {projects.map((project) => (
          <div className="AC-Project-card" key={project.id}>
            <div className="AC-Project-column1">
              <h2 className="AC-Project-h2">{project.projectName}</h2>
              <p>
                <span className="AC-Project-HeadSpan" >Head : </span>
                {project.head}
              </p>
              <br></br>
              <p className="AC-Project-Description-Para">
                <span
                className="AC-Project-DescriptionSpan"
                >
                  Description :
                </span>
                {project.description.length > 40 ? (
                  <p>
                    {project.description.slice(0, 25)}{" "}
                    <span
                      className="AC-Project-ReadmoreSpan"
                      onClick={() => handleReadMoreClick(project.description)}
                    >
                      Read More
                    </span>
                  </p>
                ) : (
                  <p>{project.description}</p>
                )}
              </p>

              <p className="AC-Project-SkillsPara">
                <span
                className="AC-Project-SkillsSpan"
                >
                  Skills :{" "}
                </span>
                {project.requiredSkills && project.requiredSkills.length > 0
                  ? project.requiredSkills.join(", ")
                  : "NA"}
              </p>
            </div>
            <div className="AC-Project-column2">
              <p>
                <span
                className="AC-Project-ProjectIdSpan"
                >
                  Project ID :{" "}
                </span>
                {project.id}
              </p>
              <p>
                <br />
                <br />
                <br />
                <span
                className="AC-Project-StartDateSpan"
                >
                  Start Date :{" "}
                </span>
                {reverseDateFormat(project.startDate)}
              </p>
              <p>
                <span
           className="AC-Project-TeamSpan"
                >
                  Team :{" "}
                </span>
                {project.team ? project.team.join(", ") : "Not Assigned"}
              </p>
              {showPopup && (
                <Popup
                  description={selectedDescription}
                  onClose={handlePopupClose}
                />
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
export default ProjectCard;
