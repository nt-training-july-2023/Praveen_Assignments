import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./AssignProject.css";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css";
import { useLocation } from "react-router-dom";
import adminService from "../../../Service/AdminService";
import Button from "../../../components/Button";

function AssignProject() {
  const [projects, setProjects] = useState([]);
  const [employeeDetails, setEmployeeDetails] = useState([]);
  const [managerId, setManagerId] = useState(0);
  const [projectId, setProjectId] = useState(0);
  const { employeeId } = useParams();

  const navigate = useNavigate();

  const location = useLocation();
  const stateData = location.state;

  useEffect(() => {
    getAllProjects();
  }, []);

  const getAllProjects = async () => {
    try {
      const response = await adminService.getProjects();
      setProjects(response.data);
    } catch (error) {
      console.error("Error fetching projects data:", error);
    }
  };

  const handleUpdate = async () => {
    const toastId = "handleUpdate";
    if (projectId === 0 || managerId === 0) {
      toast.error("Please Select Project", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
      return;
    } else {
      try {
        const data = {
          projectId: projectId,
          managerId: managerId,
        };

        const response = await adminService.assignProject(employeeId, data);
        console.log("Employee updated:", response.data);
        navigate("/AdminDashboard");
      } catch (error) {
        if (error.response) {
          console.error("Error updating employee:", error);
          // toast.error(error.response.data.message, {
          //   position: "top-right",
          //   autoClose: 3000,
          //   toastId,
          // });
        } else {
          toast.error("oops !! server down", {
            position: "top-center",
            autoClose: 3000,
            toastId,
          });
        }
      }
    }
  };
  const handleCancel = (e) => {
    navigate("/AdminDashboard");
  };

  const handleSelectChange = (e) => {
    const selectedOption = e.target.options[e.target.selectedIndex];
    const selectedProjectId = e.target.value;
    const selectedManagerId = selectedOption.getAttribute("data-managerid");
    setProjectId(selectedProjectId);
    setManagerId(selectedManagerId);
  };

  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Admin") {
    return <h1>Unauthorized access</h1>;
  }

  return (
    <div>
      <div>
        <h1 className="AP">Assign Project</h1>
      </div>
      <br />
      <div className="AP-container">
        <h2 style={{ fontWeight: "bold" }}>{stateData.empName}</h2>
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
          <Button
            type="button"
            onClick={handleUpdate}
            text={"Assign Project"}
          />
          <Button
            type="button"
            className={"secondary"}
            onClick={handleCancel}
            text={"Cancel"}
          />
        </div>
      </div>
    </div>
  );
}

export default AssignProject;
