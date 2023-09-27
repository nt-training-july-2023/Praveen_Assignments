// import React from "react";
// import { useState, useEffect } from "react";
// import axios from "axios";
// // import "./Manager.css";

// function SingleManagerCard({ employee }) {
//   // console.log(employee.id);
//   const [projectList, setProjectList] = useState([]);
//   const [selectedProject, setSelectedProject] = useState("");
//   const [showDropdown, setShowDropdown] = useState(true);
//   const [selectedProjectData, setSelectedProjectData] = useState(null);

//   async function fetchProjectList() {
//     try {
//       const res = await axios.get(
//         `http://localhost:8080/api/projectCards/${employee.id}`
//       );
//       const projectData = res.data;
//       if (projectData.length > 0) {
//         const firstProject = projectData[0];
//         setSelectedProjectData(firstProject);
//         setSelectedProject(firstProject.id);
//         setShowDropdown(projectData.length > 1);
//       }

//       setProjectList(projectData);
//     } catch (error) {
//       console.log(error);
//     }
//   }
//   useEffect(() => {
//     fetchProjectList();
//   }, []);

//   // function handleChange(event) {
//   //   const selectedProjectId = event.target.value;
//   //   setSelectedProject(selectedProjectId);
//   //   const projectData = projectList.find(
//   //     (project) => project.projectID.toString() === selectedProjectId
//   //   );
//   //   console.log(projectData);
//   //   if (projectData) {
//   //     setSelectedProjectData(projectData);
//   //   } else {
//   //     setSelectedProjectData(null);
//   //   }
//   // }
//   function handleChange(event) {
//     const selectedProjectId = event.target.value;
//     setSelectedProject(selectedProjectId);
//     const projectData = projectList.find(
//       (project) => project.id.toString() === selectedProjectId
//     );

//     if (projectData) {
//       console.log(projectData);
//       setSelectedProjectData(projectData);
//     } else {
//       // Handle the case where the projectData is not found
//       setSelectedProjectData(null); // or provide a default value
//     }
//   }

//   return (
//     <div className="container">
//       <div className="card" key={employee.id}>
//         <div className="column">
//           <h2>{employee.name}</h2>
//           <p>
//             <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
//               {employee.designation}
//             </span>
//           </p>
//           <div>
//             Project:
//             {/* <select
//               name="projectName"
//               className="select"
//               onChange={handleChange}
//               value={selectedProject}
//             >
//               <option value="">Select </option>
//               {projectList.map((project) => (
//                 <option key={project.id} value={project.id}>
//                   {project.projectName}
//                 </option>
//               ))}
//             </select> */}
//             {showDropdown ? (
//               <select
//                 style={{ marginTop: "0.5rem" }}
//                 name="projectName"
//                 className="select"
//                 onChange={handleChange}
//                 value={selectedProject}
//               >
//                 <option value="" disabled>
//                   Select Project
//                 </option>
//                 {projectList.map((project) => {
//                   return (
//                     <option key={project.id} value={project.id}>
//                       {project.projectName}
//                     </option>
//                   );
//                 })}
//               </select>
//             ) : (
//               <span>{selectedProjectData?.projectName}</span>
//             )}
//           </div>

//           <p>
//             <span style={{ fontWeight: "bold" }}>Location : </span>
//             {employee.location}
//           </p>
//           <p>
//             <span style={{ fontWeight: "bold" }}>Contact : </span>
//             {employee.contactNo}
//           </p>
//           <p>
//             <span style={{ fontWeight: "bold" }}>Email : </span>
//             {employee.email}
//           </p>
//         </div>
//         <div className="column">
//           <br></br>
//           <p style={{ fontSize: "15px", marginTop: "-1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Employee id : </span>
//             {employee.empId}
//           </p>
//           <br></br>
//           {/* <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Project Skills : </span>{" "}
//             {projectList
//               .filter((project) => project.id.toString() === selectedProject)
//               .map((project) =>
//                 project.requiredSkills.map((skill, index) => (
//                   <span key={index}>
//                     {index === 0 ? "" : ", "}
//                     {skill}
//                   </span>
//                 ))
//               )}
//           </p> */}
//           {/* <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Team : </span>{" "}
//             {projectList
//               .filter((project) => project.id.toString() === selectedProject)
//               .map((project) =>
//                 project.team
//                   ? project.team.map((skill, index) => (
//                       <span key={index}>
//                         {index === 0 ? "" : ", "}
//                         {skill}
//                       </span>
//                     ))
//                   : "Not Assigned"
//               )}
//           </p> */}
//           {/* <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Project Skills:</span>{" "}
//             {selectedProjectData?.requiredSkills.join(", ")}
//           </p>
//           <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Team : </span>
//             {selectedProjectData?.team.join(", ")}
//           </p> */}
//           <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Project Skills:</span>{" "}
//             {selectedProjectData?.requiredSkills
//               ? selectedProjectData.requiredSkills.join(", ")
//               : "N/A"}
//           </p>
//           <p style={{ marginTop: "1rem" }}>
//             <span style={{ fontWeight: "bold" }}>Team : </span>
//             {selectedProjectData?.team
//               ? selectedProjectData.team.join(", ")
//               : "N/A"}
//           </p>
//         </div>
//       </div>
//     </div>
//   );
// }

// export default SingleManagerCard;

import React, { useState, useEffect } from "react";
import axios from "axios";

function SingleManagerCard({ employee }) {
  const [projectList, setProjectList] = useState([]);
  const [selectedProject, setSelectedProject] = useState("");
  const [selectedProjectData, setSelectedProjectData] = useState(null);
  const [showDropdown, setShowDropdown] = useState(true);

  async function fetchProjectList() {
    try {
      const res = await axios.get(
        `http://localhost:8080/api/projectCards/${employee.id}`
      );
      const projectData = res.data;
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
    <div className="container">
      <div className="card" key={employee.id}>
        <div className="column">
          <h2>{employee.name}</h2>
          <p>
            <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
              {employee.designation}
            </span>
          </p>
          {/* <div>
            Project:
            {noProjects?  (
              <span>N/A</span>
            ) : (
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
            )}
          </div> */}

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
            <span style={{ fontWeight: "bold" }}>Location : </span>
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
            {selectedProjectData?.requiredSkills
              ? selectedProjectData.requiredSkills.join(", ")
              : "N/A"}
          </p>
          <p style={{ marginTop: "1rem" }}>
            <span style={{ fontWeight: "bold" }}>Team : </span>
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
