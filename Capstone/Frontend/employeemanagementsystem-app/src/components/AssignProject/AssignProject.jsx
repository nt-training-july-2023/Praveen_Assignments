import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./AssignProject.css";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css";

function AssignProject() {
  const [projects, setProjects] = useState([]);
  const [employeeDetails, setEmployeeDetails] = useState([]);
  const [managerId, setManagerId] = useState(0);
  const [projectId, setProjectId] = useState(0);
  const [assignError, setAssignError] = useState();
  const { employeeId } = useParams();

  const navigate = useNavigate();
  console.log({ employeeId });

  useEffect(() => {
    getAllProjects();
    getEmployeeName();
  }, []);

  const validateAssign = () => {
    if(projectId === "" || managerId=="") {
      console.log("praveen")
      setAssignError("select designation");
    } else {
      setAssignError("");
    }
  };

  const getAllProjects = async () => {
    
    try {
      const response = await axios.get(
        "http://localhost:8080/api/projectCards"
      );
      setProjects(response.data);
    } catch (error) {
      console.error("Error fetching projects data:", error);
    }
  };
  const getEmployeeName = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/employee/${employeeId}`
      );
      setEmployeeDetails(response.data);
    } catch (error) {
      console.error("Error fetching projects data:", error);
    }
  };
  const handleUpdate = async () => {
    const toastId ="handleUpdate";
    validateAssign();
    if(assignError){
      toast.error("Please Select Project", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
    }
    else{try {
      const response = await axios.put(
        `http://localhost:8080/api/updateEmployee/${employeeId}`,
        {
          projectId: projectId,
          managerId: managerId,
        }
      );
      console.log("Employee updated:", response.data);
      navigate("/AdminDashboard");
    } catch (error) {
      console.error("Error updating employee:", error);
    }
  }
  };

  const handleCancel =(e)=>{
    navigate("/AdminDashboard");
    
  }

  const handleSelectChange = (e) => {
    const selectedOption = e.target.options[e.target.selectedIndex];
    const selectedProjectId = e.target.value;
    const selectedManagerId = selectedOption.getAttribute("data-managerid");
    setProjectId(selectedProjectId);
    setManagerId(selectedManagerId);
  };

  return (
    <div>
      <div>
        <h1 className="AP">Assign Project</h1>
      </div>
      <br />
      <div className="AP-container">
        <h2 style={{ fontWeight: "bold" }}>{employeeDetails.name}</h2>
        <select
          type="text"
          name="managerId"
          className="AP-project-input-box"
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

        <div className="AP-button-container">
          <button type="button" onClick={handleUpdate}>
            Assign Project
          </button>
          <button type="button" className="secondary" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default AssignProject;
