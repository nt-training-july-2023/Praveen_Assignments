import React, { useState, useEffect } from "react";
import axios from "axios";
import "./EmployeeCarddd.css";
import MultipleSelectDropdown from "../../components/MultipleSelectDropdown/MultipleSelectDropdown";
import SkillsDropDown from "../../components/Dropdowns/Skills";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import managerService from "../../Service/ManagerService";
import Button from "../../components/Button";

function EmployeeCarddd() {
  const [employees, setEmployees] = useState([]);
  const [showUnassigned, setShowUnassigned] = useState(false);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [isLoading, setIsLoading] = useState(true); // Add a loading state
  const navigate = useNavigate();
  const id = localStorage.getItem("id");

  useEffect(() => {
    getAllEmployees();
  }, [selectedSkills, showUnassigned]);

  const Request_Resource = (employeeId) => {
    navigate(`/requestResource/${employeeId}`);
  };

  const IsRequested = async (employeeId) => {
    try {
      const data = {
        employeeId: employeeId,
        id: id,
      };
      const response = await managerService.isRequested(data);
      const isRequested = response.data.requested;
      return isRequested;
    } catch (error) {
      console.error("Error fetching data:", error);
      return false; // Return false in case of an error
    }
  };

  const getAllEmployees = async () => {
    setIsLoading(true);
    try {
      const response = await managerService.getFilteredEmployees(
        selectedSkills,
        showUnassigned
      );

      const employeesWithIsRequested = await Promise.all(
        response.data.map(async (employee) => {
          const isRequested = await IsRequested(employee.id);
          return { ...employee, isRequested };
        })
      );

      setEmployees(employeesWithIsRequested);
      setIsLoading(false);
    } catch (error) {
      if (error.response) {
        console.log(error);
        toast.error(error.response.data.message, {
          position: "top-right",
          autoClose: 3000,
        });
      } else {
        toast.error("oops !! server down", {
          position: "top-center",
          autoClose: 3000,
          
        });
      }
    }
  };

  function reverseDateFormat(inputDate) {
    const dateParts = inputDate.split("-");

    if (dateParts.length === 3) {
      const reversedDate =
        dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
      return reversedDate;
    } else {
      return "Invalid Date Format";
    }
  }

  const toggleShowUnassigned = () => {
    setShowUnassigned((prevShowUnassigned) => !prevShowUnassigned);
  };

  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setSelectedSkills(selectedSkillsValues);
  };

  return (
    <div className="empty">
      <div className="EC-Component">
        <div className="MD-skill-filter">
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
            placeholder="Search Skills"
          />
        </div>
        <div className="MD-filter">
          <label>
            <input
              className="Radiobutton"
              type="checkbox"
              name="showUnassigned"
              checked={showUnassigned}
              onChange={toggleShowUnassigned}
            />
          </label>
          <span>Show Unassigned</span>
        </div>
      </div>

      <div className="MD-container">
        <div className="MD-card-container">
          {isLoading ? ( // Check if data is still loading
            <p>Loading...</p>
          ) : employees.length === 0 ? ( // Check if employees array is empty
            <div>
              <h1>No employees present</h1>
            </div>
          ) : (
            employees.map((employee) => (
              <div className="MD-card" key={employee.id}>
                <div className="MD-column1">
                  <h2>{employee.name}</h2>
                  <p>
                    <span className="MD-designationSpan">
                      {employee.designation}
                    </span>
                  </p>
                  <p className="MD-ProjectNamePara">
                    {employee.projectId ? (
                      <p>
                        <span className="MD-ProjectNameSpan">
                          Project Name :
                        </span>{" "}
                        {employee.projectName}
                      </p>
                    ) : (
                      <p>
                        <span className="MD-ProjectNameSpan">
                          Project Name :
                        </span>{" "}
                        N/A
                      </p>
                    )}
                  </p>
                  {employee.managerName ? (
                    <p>
                      <span className="MD-ProjectNameSpan">Manager : </span>
                      {employee.managerName}
                    </p>
                  ) : (
                    <p>
                      <span className="MD-ProjectNameSpan">Manager:</span>{" "}
                      Ankita
                    </p>
                  )}
                  <p>
                    <span className="MD-ProjectNameSpan">Contact : </span>
                    {employee.contactNo}
                  </p>
                  <p>
                    <span className="MD-ProjectNameSpan">Email : </span>
                    {employee.email}
                  </p>
                  <p>
                    <span className="MD-ProjectNameSpan">Skills : </span>
                    {employee.skills.join(",")}
                  </p>
                </div>
                <div className="MD-column2">
                  <br></br>
                  <p className="MD-ProjectId-para">
                    <span className="MD-ProjectNameSpan">Employee id : </span>
                    {employee.empId}
                  </p>
                  <br></br>
                  <p className="MD-DOBPara">
                    <span className="MD-ProjectNameSpan">DOB : </span>
                    {reverseDateFormat(employee.dob)}
                  </p>
                  <p>
                    <span className="MD-ProjectNameSpan">DOJ: </span>
                    {reverseDateFormat(employee.doj)}
                  </p>
                  <p>
                    <span className="MD-ProjectNameSpan">Location : </span>
                    {employee.location}
                  </p>
                  {!employee.projectId && (
                    <p>
                      {employee.isRequested ? (
                        <Button
                          className={"MD-requested-button"}
                          disabled
                          text={"Requested"}
                        />
                      ) : (
                        <Button
                          className={"MD-assign-button"}
                          onClick={() => {
                            navigate(`/requestResource/${employee.id}`, {
                              state: { empName: employee.name },
                            });
                          }}
                          text={"Request Resource"}
                        />
                      )}
                    </p>
                  )}
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default EmployeeCarddd;
