import React, { useEffect, useState } from "react";
import "./AddProject.css";
import { useNavigate } from "react-router-dom";
import { Slide, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";
import SkillsDropDown from "../Dropdowns/Skills";
import MultipleSelectDropdown from "../MultipleSelectDropdown/MultipleSelectDropdown";

function AddProject() {
  const [projectName, setProjectName] = useState("");
  const [managerId, setManagerId] = useState("");
  const [startDate, setStartDate] = useState("");
  const [requiredSkills, setRequiredSkills] = useState([]);
  const [description, setDescription] = useState("");
  const [managerList, setManagerList] = useState([]);
  const [selectedSkills]=useState([]);

  const [projectNameError, setProjectNameError] = useState("");
  const [managerIdError, setManagerIdError] = useState("");
  const [startDateError, setStartDateError] = useState("");
  const [requiredSkillsError, setRequiredSkillsError] = useState("");
  const [descriptionError, setDescriptionError] = useState("");
  
  //   const [skills,setSkills]=useState([
  //     "skill1",
  //     "skill2",
  //     "skill3"
  //   ]);

  //   const handleSkillSelect = (event) => {
  //     const selectedSkill = event.target.value;
  //     setSkillsRequired([...skillsRequired, selectedSkill]); // Add to selected skills
  //     setSkills(skills.filter((skill) => skill !== selectedSkill)); // Remove from available skills
  //   };
  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setRequiredSkills(selectedSkillsValues);
  };

  const navigate = useNavigate();
  const today = new Date();

  const redirectToAdminDashboard = () => {
    navigate("/adminDashboard");
  };

  const validateProjectName = () => {
    if (projectName === "") {
      setProjectNameError("ProjectName cannot be empty");
    } else {
      setProjectNameError("");
    }
  };

  const validateManagerId = () => {
    if (managerId === "") {
      setManagerIdError("Manager should not be empty");
    } else {
      setManagerIdError("");
    }
  };

  const validateStartDate = () => {
    // if (startDate === "" || new Date(startDate) > today) {
    if (startDate === "") {
      setStartDateError("Enter startDate of project");
    } else {
      setStartDateError("");
    }
  };

  const validateRequiredSkills = () => {
    if (requiredSkills.length === 0) {
      setRequiredSkillsError("select skillsRequired");
    } else {
      setRequiredSkillsError("");
    }
  };

  const validateDescription = () => {
    if (description === "") {
      setDescriptionError("Description cannot be empty");
    } else {
      setDescriptionError("");
    }
  };
  useEffect(() => {
    getAllManager();
  }, []);

  const getAllManager = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/all/Manager");
      console.log(response.data);
      setManagerList(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const save = async (event) => {
    const toastId = "save";

    event.preventDefault();

    validateProjectName();
    validateManagerId();
    validateStartDate();
    validateRequiredSkills();
    validateDescription();

    if (
      (projectNameError ||
        managerIdError ||
        startDateError ||
        requiredSkillsError ||
        descriptionError,
      projectName === "" ||
        managerId === "" ||
        startDate === "" ||
        requiredSkills === "" ||
        description === "")
    ) {
      toast.error("Please fill every field.", {
        position: "top-right",
        autoClose: 3000,
        toastId,
      });
      return; // Exit the function if there are errors
    } else {
      // When sending data to the server, split the skills string into an array if needed.
      // const skillsArray = skills.split(",");
      try {
        const response = await axios.post(
          "http://localhost:8080/api/addProject",
          {
            projectName: projectName,
            managerId: managerId,
            startDate: startDate,
            requiredSkills: requiredSkills,
            description: description,
          }
        );
        if (response.status === 200) {
          toast.success("Project Added", {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000, //3 seconds
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            toastId,
            transition: Slide,
          });
          // Reset form fields after successful registration
          setProjectName("");
          setManagerId("");
          setStartDate("");
          setRequiredSkills([]);
          setDescription("");
          setManagerList([]);
        }
      } catch (err) {
        toast.error(err.response.data.message, {
          position: "top-right",
          autoClose: 3000,
          toastId,
        });
      }
    }
  };
  return (

    <div className="form">
      <div className="form-header">
        <h2>ADD PROJECT</h2>
      </div>

      <div className="form-body">
        <div className="form-group">
          <label className="form-label">ProjectName</label>
          <input
            className="form-input"
            type="text"
            placeholder="Enter ProjectName"
            value={projectName}
            onChange={(event) => setProjectName(event.target.value)}
            onBlur={validateProjectName}
          />
        </div>
        {projectNameError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {projectNameError}
          </span>
        )}

        {/* <div className="form-group">
          <label className="form-label">ManagerId</label>
          <input
            className="form-input"
            type="text"
            placeholder="Select Manager"
            value={managerId}
            onChange={(event) => {
              setManagerId(event.target.value);
            }}
            onBlur={validateManagerId}
          />
        </div> */}
        <div className="form-group">
          <label className="form-label">ManagerId</label>
          <select
            type="text"
            name="managerId"
            className="abc"
            onChange={(e) => {
              setManagerId(e.target.value);
            }}
          >
            <option value="">Select Manager</option>
            {managerList.map((manager) => {
              return (
                <option key={manager.id} value={manager.id}>
                  {manager.empId} - {manager.name}
                </option>
              );
            })}
          </select>
        </div>
        {managerIdError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {managerIdError}
          </span>
        )}

        <div className="form-group">
          <label className="form-label">Start Date</label>
          <input
            className="form-input"
            type="date"
            placeholder="Enter Start Date"
            value={startDate}
            onChange={(event) => {
              setStartDate(event.target.value);
            }}
            onBlur={validateStartDate}
          />
        </div>
        {startDateError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {startDateError}
          </span>
        )}

        <div className="form-group">
          <label className="form-label">SkillsRequired</label>
          {/* <Select
            className="zzz"
            options={SkillsDropDown.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            isMulti={true}
            placeholder="Select skills"
            onChange={handleSkillChange}
            value={requiredSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            onBlur={validateRequiredSkills}
          /> */}
          <MultipleSelectDropdown
            options={SkillsDropDown.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            selectedOptions={selectedSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            onChange={handleSkillChange}
            placeholder="Select Skills"
            onBlur={validateRequiredSkills}
          />
        </div>
        {requiredSkillsError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {requiredSkillsError}
          </span>
        )}
        <div>
          {" "}
          <label className="form-label">Description</label>
        </div>
        <div>
          <textarea
            id="description"
            className="description-box"
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
      </div>

      <div className="buttons">
        <button
          type="submit"
          className="button-cancel"
          onClick={redirectToAdminDashboard}
        >
          Back to Home
        </button>
        <button type="submit" className="button-submit" onClick={save}>
          ADD Project
        </button>
      </div>
    </div>

  );
}

export default AddProject;
