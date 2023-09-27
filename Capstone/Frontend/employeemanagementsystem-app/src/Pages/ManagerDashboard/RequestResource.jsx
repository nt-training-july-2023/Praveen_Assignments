import React, { useState, useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css";
import "./RequestResource.css";

function RequestResource() {
  const [projects, setProjects] = useState([]);
  const [employeeName, setEmployeeName] = useState([]);
  const [managerId, setManagerId] = useState(0);
  const [projectId, setProjectId] = useState(0);
  const [assignError, setAssignError] = useState();
  const [managerData, setManagerData] = useState({});
  const [description, setDescription] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  const { employeeId } = useParams();
  const id = localStorage.getItem("id");

  const location = useLocation();
  const stateData = location.state;
  

  const navigate = useNavigate();
  console.log({ employeeId });

  const validateDescription = () => {
    if (description === "") {
      setDescriptionError("Description cannot be empty");
    } else {
      setDescriptionError("");
    }
  };

  const validateAssign = () => {
    if (projectId === "" || managerId == "") {
      console.log("praveen");
      setAssignError("select designation");
    } else {
      setAssignError("");
    }
  };
useEffect(() => {
    const fetchData = async () => {
      try {
        // First, fetch manager data
        // const managerResponse = await axios.get(
        //   `http://localhost:8080/api/manager/id/${id}`
        // );
        // setManagerData(managerResponse.data);
  
        // // Next, fetch employee name
        // const employeeResponse = await axios.get(
        //   `http://localhost:8080/api/employeeName/${employeeId}`
        // );
        // setEmployeeName(employeeResponse.data);
  
        // Finally, use managerData to fetch projects
        const projectsResponse = await axios.get(
          `http://localhost:8080/api/projectCards/${id}`
        );
        setProjects(projectsResponse.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    }
  
    fetchData(); // Call the fetchData function
  }, [id, employeeId]); // Make sure to include email and employeeId as dependencies
  
  const handleUpdate = async () => {
    const toastId = "handleUpdate";
    validateAssign();
    validateDescription();
    if (projectId === "" || managerId == ""||description==="") {
      toast.error("Please fill all details", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
      return;
    } else {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/requestResource",
          {
            employeeId: employeeId,
            projectId: projectId,
            managerId: managerId,
            comment: description
          }
        );
        console.log("Employee updated:", response.data);
        navigate("/ManagerDashboard");
      } catch (error) {
        console.error("Error updating employee:", error);
      }
    }
  };

  const handleCancel = (e) => {
    navigate("/ManagerDashboard");
  };

  const handleSelectChange = (e) => {
    const selectedOption = e.target.options[e.target.selectedIndex];
    const selectedProjectId = e.target.value;
    const selectedManagerId = selectedOption.getAttribute("data-managerid");
    setProjectId(selectedProjectId);
    setManagerId(selectedManagerId);
  };


  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'Manager') {
    return <h1>Unauthorized access</h1>;
  }

  
  return (
    <div>
      <div>
        <h1 className="RR">Request Resource</h1>
      </div>
      <br />
      <div className="RR-container">
        <h2 style={{ fontWeight: "bold" }}>{stateData.empName}</h2>
        <select
          type="text"
          name="managerId"
          className="RR-project-input-box"
          onChange={handleSelectChange}
        >
          <option value="">Select Project</option>
          {projects.map((item) => (
            <option
              key={item.id}
              value={item.id}
              data-managerid={item.managerId}
            >
              {item.id} - {item.projectName}
            </option>
          ))}
        </select>
        <div>
          {" "}
          <label className="form-label">Description</label>
        </div>
        <div>
          <textarea
            id="description"
            className="RR-description-box"
            placeholder="Enter your description here..."
            value={description}
            onChange={(event) => setDescription(event.target.value)}
            onBlur={validateDescription}
          ></textarea>
        </div>
        {descriptionError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {descriptionError}
          </span>
        )}

        <div className="RR-button-container">
          <button type="button" onClick={handleUpdate}>
            Request
          </button>
          <button type="button" className="secondary" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default RequestResource;
