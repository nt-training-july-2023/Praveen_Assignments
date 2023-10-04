import React, { useState, useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css";
import "./RequestResource.css";
import managerService from "../../Service/ManagerService";
import Button from "../../components/Button";
import Select from "../../components/Select";

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

  const validateDescription = () => {
    if (description === "") {
      setDescriptionError("Description cannot be empty");
    } else {
      setDescriptionError("");
    }
  };

  const validateAssign = () => {
    if (projectId === "" || managerId == "") {
      setAssignError("select designation");
    } else {
      setAssignError("");
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const projectsResponse = await managerService.projectByManagerId(id);
        setProjects(projectsResponse.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [id]);

  const handleUpdate = async () => {
    const toastId = "handleUpdate";
    validateAssign();
    validateDescription();
    if (projectId === "" || managerId === "" || description === "") {
      toast.error("Please fill all details", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
      return;
    } else {
      try {
        const data = {
          employeeId: employeeId,
          projectId: projectId,
          managerId: managerId,
          comment: description,
        };
        const response = await managerService.requestResource(data);
        console.log("Resource requested:", response.data);
        navigate("/ManagerDashboard");
      } catch (error) {
        console.error("Error requesting resource:", error);
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

  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Manager") {
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
        {/* <Select
          value={projectId}
          onChange={handleSelectChange}
          className="RR-project-input-box"
          onBlur={validateAssign}
          placeholder={"Select Project"}
          options={projects.map((item) => ({
            label: `${item.id} - ${item.projectName}`,
            value: item.id,
          }))}
        /> */}

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
          <Button type="button" onClick={handleUpdate} text={"Request"} />
          <Button
            type="button"
            className={"secondary"}
            onClick={handleCancel}
            text={" Cancel"}
          />
        </div>
      </div>
    </div>
  );
}

export default RequestResource;
