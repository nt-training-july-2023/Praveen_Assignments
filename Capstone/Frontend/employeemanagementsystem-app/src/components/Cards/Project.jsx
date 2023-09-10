import React from "react";
import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import "./Project.css";

function ProjectCard() {
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    getAllProjects();
  }, []);

  const getAllProjects = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/projectCards"
      );
      console.log(response.data);
      setProjects(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  return (
    // <div className="project-container">
    //   <div className="project-card-container">
    //     {projects.map((project) => (
    //       <div className="project-card" key={project.id}>
    //         <div className="column1">
    //           <h2 style={{ fontWeight: "bold" }}>{project.projectName}</h2>
    //           <p>
    //             <span style={{ fontWeight: "bold" }}>Head :</span>
    //             {project.head }
    //           </p>
    //           <br></br>
    //           <p style={{ fontSize: "1rem" }}>
    //             <span
    //               style={{
    //                 fontWeight: "bold",
    //                 fontSize: "1rem",
    //                 marginTop: "2rem",
    //               }}
    //             >
    //               Description :
    //             </span>
    //             {project.description}
    //           </p>
    //           <br></br>
    //           <p style={{ fontSize: "1rem" }}>
    //             <span
    //               style={{
    //                 fontWeight: "bold",
    //                 fontSize: "1rem",
    //                 marginTop: "2rem",
    //               }}
    //             >
    //               Skills :{" "}
    //             </span>
    //             {project.requiredSkills && project.requiredSkills.length > 0
    //               ? project.requiredSkills.join(", ")
    //               : "NA"}
    //           </p>
    //           <p style={{ fontSize: "1rem" }}>
    //             <span
    //               style={{
    //                 fontWeight: "bold",
    //                 fontSize: "1rem",
    //                 marginTop: "2rem",
    //               }}
    //             >
    //               Team :{" "}
    //             </span>
    //             {"None"}
    //           </p>
    //         </div>
    //         <div className="column2">
    //           <p>
    //             <span
    //               style={{
    //                 fontWeight: "bold",
    //                 fontSize: "1rem",
    //                 marginTop: "1rem",
    //               }}
    //             >
    //               project ID :{" "}
    //             </span>
    //             {project.id}
    //           </p>
    //         </div>
    //         <div className="column2">
    //           <p>
    //             <span
    //               style={{
    //                 fontWeight: "bold",
    //                 fontSize: "1rem",
    //                 marginTop: "1rem",
    //               }}
    //             >
    //               Start Date :{" "}
    //             </span>
    //             {project.startDate}
    //           </p>
    //         </div>
    //       </div>
    //     ))}
    //   </div>
    // </div>

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
                {project.description}
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
            {/* </div>
            <div className="column2"> */}
              <p>
                <span
                  style={{
                    fontWeight: "bold",
                    fontSize: "1rem",
                    marginTop: "1rem",
                  }}
                >
                  Start Date :{" "}
                </span>
                {project.startDate}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
export default ProjectCard;
