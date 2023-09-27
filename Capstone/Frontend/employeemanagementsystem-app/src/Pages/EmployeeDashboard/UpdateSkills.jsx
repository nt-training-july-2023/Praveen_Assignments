import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import SkillsDropDown from "../../components/Dropdowns/Skills";
import { useParams } from "react-router-dom";
import MultipleSelectDropdown from "../../components/MultipleSelectDropdown/MultipleSelectDropdown";
import axios from "axios";
import "./UpdateSkills.css";

function UpdateSkills() {
  const [skills, setSkills] = useState([]);
  const [updateSkillsError, setUpdateSkillsError] = useState("");
  const { employeeId } = useParams();
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [fetchData, setFetchData] = useState(false);
  const id = localStorage.getItem("id");

  const navigate = useNavigate();

  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setSkills(selectedSkillsValues);
  };
  const location = useLocation();
  const stateData = location.state;

  useEffect(() => {
    if (stateData.empSkills) {
      setSelectedSkills(stateData.empSkills);
      setFetchData(true);
    }
  }, []);

  const ValidateSkill = () => {
    if (skills.length === 0) {
      setUpdateSkillsError("please select");
    } else {
      setUpdateSkillsError("");
    }
  };

  const handleUpdate = async () => {
    ValidateSkill();
    const toastId = "handleUpdate";
    if (updateSkillsError) {
      toast.error("Please Update Skills", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
      return;
    } else {
      try {
        const response = await axios.put(
          `http://localhost:8080/api/updateSkill/${employeeId}`,
          {
            skills: skills,
          }
        );

        console.log("Employee updated:", response.data);
        navigate("/EmployeeDashboard");
      } catch (error) {
        console.error("Error updating employee:", error);
      }
    }
  };

  const handleCancel = (e) => {
    navigate("/EmployeeDashboard");
  };

  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Employee") {
    return <h1>Unauthorized access</h1>;
  }

  return (
    <div>
      <div>
        <h1 className="US">Update Skills</h1>
      </div>
      <br />
      <div className="US-container">
        <h2 style={{ fontWeight: "bold" }}>{stateData.empName}</h2>

        <div className="US-form-group">
          <label className="US-form-label">Skills</label>
          {fetchData && (
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
            />
          )}
        </div>

        <div className="US-button-container">
          <button type="button" onClick={handleUpdate}>
            Update
          </button>
          <button type="button" className="secondary" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default UpdateSkills;
