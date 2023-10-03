import React, { useState } from "react";
import axios from "axios";
import "./AddEmployee.css";
import { useNavigate } from "react-router-dom";
import designations from "../../../components/Dropdowns/Designations";
import locations from "../../../components/Dropdowns/Locations";
import Roles from "../../../components/Dropdowns/Roles";
import bcrypt from "bcryptjs";
import { Slide, toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css";
import SkillsDropDown from "../../../components/Dropdowns/Skills";
import MultipleSelectDropdown from "../../../components/MultipleSelectDropdown/MultipleSelectDropdown";
import adminService from "../../../Service/AdminService";
import Button from "../../../components/Button";

function AddEmployee() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [empId, setEmpId] = useState("");
  const [dob, setDOB] = useState("");
  const [doj, setDOJ] = useState("");
  const [location, setLocation] = useState("");
  const [designation, setDesignation] = useState("");
  const [role, setRole] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [skills, setSkills] = useState([]);
  const [selectedSkills] = useState([]);

  const [nameError, setNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [employeeIdError, setEmployeeIdError] = useState("");
  const [dobError, setDobError] = useState("");
  const [dojError, setDojError] = useState("");
  const [locationError, setLocationError] = useState("");
  const [designationError, setDesignationError] = useState("");
  const [roleError, setRoleError] = useState("");
  const [contactError, setContactError] = useState("");
  const [skillsError, setSkillsError] = useState("");

  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setSkills(selectedSkillsValues);
    // console.log(skills)
  };

  const navigate = useNavigate();
  const today = new Date();
  const minDate = new Date(
    today.getFullYear() - 18,
    today.getMonth(),
    today.getDate()
  );

  const redirectToAdminDashboard = () => {
    navigate("/adminDashboard");
  };

  const validateName = () => {
    if (name === "") {
      setNameError("Name cannot be empty");
    } else if (name.match(/\d/)) {
      setNameError("Name cannot contain digits");
    } else {
      setNameError("");
    }
  };

  const validateEmail = () => {
    if (!email.endsWith("@nucleusteq.com")) {
      setEmailError("Email must end with @nucleusteq.com");
    } else {
      setEmailError("");
    }
  };

  const validateEmployeeId = () => {
    const Employee_ID = /^N\d{4}$/;
    if (empId === "" || !Employee_ID.test(empId) || empId == "N0000") {
      setEmployeeIdError(
        "Employee ID should be in the pattern NXXXX (X should be numbers)"
      );
    } else {
      setEmployeeIdError("");
    }
  };

  const validateDOB = () => {
    if (dob === "" || new Date(dob) > minDate) {
      setDobError("User must be at least 18 years old.");
    } else {
      setDobError("");
    }
  };

  const validateDOJ = () => {
    if (doj === "" || new Date(doj) > today) {
      setDojError("Enter date of joining");
    } else {
      setDojError("");
    }
  };

  const validateLocation = () => {
    if (location === "") {
      setLocationError("select location");
    } else {
      setLocationError("");
    }
  };

  const validateDesignation = () => {
    if (designation === "") {
      setDesignationError("select designation");
    } else {
      setDesignationError("");
    }
  };

  const validateRole = () => {
    if (role === "") {
      setRoleError("select role");
    } else {
      setRoleError("");
    }
  };

  const validateContactNo = () => {
    const pattern = /^\d{10}$/;
    if (contactNo.length !== 10 || !pattern.test(contactNo)) {
      setContactError("Contact No should have 10 digits.");
    } else {
      setContactError("");
    }
  };
  const validateSkills = () => {
    if (skills.length === 0) {
      setSkillsError("select skills");
    } else {
      setSkillsError("");
    }
  };

  const save = async (event) => {
    const toastId = "save";

    event.preventDefault();

    validateName();
    validateEmail();
    validateEmployeeId();
    validateDOB();
    validateDOJ();
    validateLocation();
    validateDesignation();
    validateRole();
    validateContactNo();
    validateSkills();

    function reverseDateFormat(inputDate) {
      // Split the input date using the '-' separator
      const dateParts = inputDate.split("-");

      // Check if the input has three parts (year, month, day)
      if (dateParts.length === 3) {
        // Reverse the parts and join them with '-' separator
        const reversedDate =
          dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
        return reversedDate;
      } else {
        // Handle invalid input format
        return "Invalid Date Format";
      }
    }

    const reversedDate = reverseDateFormat(dob);
    // console.log(reversedDate);

    const dobInput = dob;
    const resultDob = reversedDate.replaceAll("-", "");
    // console.log(resultDob);

    const DefaultPassword = empId + "@" + resultDob;
    console.log(DefaultPassword);
    const hashedpassword = bcrypt.hashSync(DefaultPassword, 10);
    console.log(contactError);

    if (
      nameError ||
      emailError ||
      employeeIdError ||
      dobError ||
      dojError ||
      locationError ||
      designationError ||
      roleError ||
      contactError ||
      skillsError ||
      name === "" ||
      email === "" ||
      empId === "" ||
      dob === "" ||
      doj === "" ||
      location === "" ||
      designation === "" ||
      role === "" ||
      contactNo === "" ||
      skills === ""
    ) {
      toast.error("Please fill every field.", {
        position: "top-right",
        autoClose: 3000,
        toastId,
      });
      return; // Exit the function if there are errors
    } else {
      const employeeData = {
        name: name,
        email: email,
        empId: empId,
        dob: dob,
        doj: doj,
        location: location,
        designation: designation,
        role: role,
        contactNo: contactNo,
        skills: skills,
        password: hashedpassword,
      };

      try {
        const response = await adminService.addEmployee(employeeData);
        if (response.status === 200) {
          toast.success("Employee Registered", {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            toastId,
            transition: Slide,
          });
          navigate("/adminDashboard");
          // Reset form fields after successful registration
          setName("");
          setEmail("");
          setEmpId("");
          setDOB("");
          setDOJ("");
          setLocation("");
          setDesignation("");
          setContactNo("");
          setRole("");
          setSkills([]);
        } else if (response.data.statusCode === 400) {
          console.log(response.data.statusCode);
          alert("Backend validations failed");
        } else if (response.data.statusCode === 500) {
          alert(response.data.message);
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
  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Admin") {
    return <h1>Unauthorized access</h1>;
  }
  return (
    <div className="AddEmployee-container">
      <div className="AddEmployee-form">
        <div className="AddEmployee-form-header">
          {/* <div className="RegistrationFormContainer" > */}
          <h2>ADD EMPLOYEE</h2>
        </div>

        <div className="AddEmployee-form-body">
          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">Name</label>
            <input
              className="AddEmployee-input"
              type="text"
              placeholder="Enter Name"
              value={name}
              onChange={(event) => setName(event.target.value)}
              onBlur={validateName}
            />
          </div>
          {nameError && (
            <span style={{ fontSize: "12px", color: "red" }}>{nameError}</span>
          )}

          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">Email</label>
            <input
              className="AddEmployee-input"
              type="email"
              placeholder="example abc@Nucleusteq.com"
              value={email}
              onChange={(event) => {
                setEmail(event.target.value);
              }}
              onBlur={validateEmail}
            />
          </div>
          {emailError && (
            <span style={{ fontSize: "12px", color: "red" }}>{emailError}</span>
          )}

          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">EmployeeId</label>
            <input
              className="AddEmployee-input"
              type="text"
              placeholder="Enter Employee ID"
              value={empId}
              onChange={(event) => {
                setEmpId(event.target.value);
              }}
              onBlur={validateEmployeeId}
            />
          </div>
          {employeeIdError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {employeeIdError}
            </span>
          )}
          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">DOB</label>
            <input
              className="AddEmployee-input"
              type="date"
              placeholder="Enter DOB"
              value={dob}
              onChange={(event) => {
                setDOB(event.target.value);
              }}
              onBlur={validateDOB}
            />
          </div>
          {dobError && (
            <span style={{ fontSize: "12px", color: "red" }}>{dobError}</span>
          )}

          <div className="AddEmployee-form-group">
            <label className="form-label">DOJ</label>
            <input
              className="AddEmployee-input"
              type="date"
              placeholder="Enter DOJ"
              value={doj}
              onChange={(event) => {
                setDOJ(event.target.value);
              }}
              onBlur={validateDOJ}
            />
          </div>
          {dojError && (
            <span style={{ fontSize: "12px", color: "red" }}>{dojError}</span>
          )}

          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">location</label>
            <select
              className="AddEmployee-form-dropdown"
              value={location}
              onChange={(event) => setLocation(event.target.value)}
              onBlur={validateLocation}
            >
              <option value="">Select Location</option>
              {locations.map((option, index) => (
                <option key={index} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </div>
          {locationError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {locationError}
            </span>
          )}

          <div class="AddEmployee-form-group">
            <label className="AddEmployee-form-label">designation</label>
            <select
              className="AddEmployee-form-dropdown"
              value={designation}
              onChange={(event) => setDesignation(event.target.value)}
              onBlur={validateDesignation}
            >
              <option value="">Select Designation</option>
              {designations.map((option, index) => (
                <option key={index} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </div>
          {designationError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {designationError}
            </span>
          )}

          <div class="AddEmployee-form-group">
            <label className="AddEmployee-form-label">role</label>
            <select
              className="AddEmployee-form-dropdown"
              value={role}
              onChange={(event) => setRole(event.target.value)}
              onBlur={validateRole}
            >
              <option value="">Select Role</option>
              {Roles.map((option, index) => (
                <option key={index} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </div>
          {roleError && (
            <span style={{ fontSize: "12px", color: "red" }}>{roleError}</span>
          )}
          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">Skills</label>
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
              onBlur={validateSkills}
            />
          </div>
          {skillsError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {skillsError}
            </span>
          )}

          <div className="AddEmployee-form-group">
            <label className="AddEmployee-form-label">contactNo</label>
            <input
              className="AddEmployee-input"
              type="text"
              placeholder="Enter Contact_Number"
              value={contactNo}
              onChange={(event) => {
                setContactNo(event.target.value);
              }}
              onBlur={validateContactNo}
            />
          </div>
          {contactError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {contactError}
            </span>
          )}
        </div>
        <div className="AddEmployee-buttons">
          <Button
            type="submit"
            className={"AddEmployee-button-cancel"}
            onClick={redirectToAdminDashboard}
            text={"Back to home"}
          />
          <Button
            type="submit"
            className={"AddEmployee-button-submit"}
            onClick={save}
            text={"ADD"}
          />
        </div>
      </div>
    </div>
  );
}

export default AddEmployee;
