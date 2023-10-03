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
    getAllProjects();
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
    <div className="container">
      <div className="card-container">
        {projects.map((project) => (
          <div className="card" key={project.id}>
            <div className="column1">
              <h2 style={{ fontWeight: "bold" }}>{project.projectName}</h2>
              <p>
                <span style={{ fontWeight: "bold" }}>Head :</span>
                {project.head}
              </p>
              <br></br>
              <p style={{ fontSize: "1rem" }}>
                <span
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "2rem",
                  }}
                >
                  Description :
                </span>
                {project.description.length > 40 ? (
                  <p>
                    {project.description.slice(0, 20)}{" "}
                    <span
                      style={{ color: "blue", textDecorationLine: "underline" }}
                      onClick={() => handleReadMoreClick(project.description)}
                    >
                      Read More
                    </span>
                  </p>
                ) : (
                  <p>{project.description}</p>
                )}
              </p>

              <p style={{ fontSize: "1rem" }}>
                <span
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "2rem",
                  }}
                >
                  Skills :{" "}
                </span>
                {project.requiredSkills && project.requiredSkills.length > 0
                  ? project.requiredSkills.join(", ")
                  : "NA"}
              </p>
            </div>
            <div className="column2">
              <p>
                <span
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "1rem",
                  }}
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
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "1rem",
                  }}
                >
                  Start Date :{" "}
                </span>
                {reverseDateFormat(project.startDate)}
              </p>
              <p>
                <span
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "1rem",
                  }}
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
