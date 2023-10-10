import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import SkillsDropDown from "../../components/Dropdowns/Skills";
import { useParams } from "react-router-dom";
import MultipleSelectDropdown from "../../components/MultipleSelectDropdown/MultipleSelectDropdown";
import axios from "axios";
import "./UpdateSkills.css";
import employeeService from "../../Service/EmployeeService";
import Button from "../../components/Button";
import UnauthorizedPage from "../Unauthorized";

function UpdateSkills() {
  const [skills, setSkills] = useState([]);
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

  const handleUpdate = async () => {
    const toastId = "handleUpdate";
    if (skills.length === 0) {
      toast.error("Please Update Skills", {
        position: "top-right",
        autoClose: 1000,
        toastId,
      });
      return;
    } else {
      try {
        const response = await employeeService.updateSkills(id, skills);
        console.log("Employee updated:", response.data);
        navigate("/EmployeeDashboard");
      } catch (error) {
        if (error.response) {
          console.log(error);
          toast.error(error.response.data.message, {
            position: "top-right",
            autoClose: 3000,
            toastId,
          });
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
    navigate("/EmployeeDashboard");
  };

  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Employee") {
    return <UnauthorizedPage />;
  }

  return (
    <div>
      <div>
        <h1 className="US">Update Skills</h1>
      </div>
      <br />
      <div className="US-container">
        <h2 style={{ fontWeight: "bold", marginBottom: "1rem" }}>
          {stateData.empName}
        </h2>

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
          <Button type="button" onClick={handleUpdate} text={"Update"} />

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

export default UpdateSkills;
