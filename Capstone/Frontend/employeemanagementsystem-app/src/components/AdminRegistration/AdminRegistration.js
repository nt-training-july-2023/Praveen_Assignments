import React, { useState } from "react";
import axios from "axios";
import "./AdminRegistrationStyle.css";
import { useNavigate } from "react-router-dom";
import designations from "../Dropdowns/Designations";
import locations from "../Dropdowns/Locations";
import bcrypt from "bcryptjs";
import { Slide, toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css"; // Import the default styles

function AdminRegistration() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [empId, setEmpId] = useState("");
  const [dob, setDOB] = useState("");
  const [doj, setDOJ] = useState("");
  const [location, setLocation] = useState("");
  const [designation, setDesignation] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [nameError, setNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [employeeIdError, setEmployeeIdError] = useState("");
  const [dobError, setDobError] = useState("");
  const [dojError, setDojError] = useState("");
  const [locationError, setLocationError] = useState("");
  const [designationError, setDesignationError] = useState("");
  const [contactError, setContactError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");

  const navigate = useNavigate();
  const hashedpassword = bcrypt.hashSync(password, 10);

  const today = new Date();
  const minDate = new Date(
    today.getFullYear() - 18,
    today.getMonth(),
    today.getDate()
  );

  const redirectToLogin = () => {
    navigate("/");
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
    }
    // else if(email!=="ankita@nucleusteq.com"){
    //   setEmailError("Admin is already registered")
    //   toast.error("Admin is already registered", {
    //     position: "top-right",
    //     autoClose: 3000
    //   });
    // }
    else {
      setEmailError("");
    }
  };

  const validateEmployeeId = () => {
    const Employee_ID = /^N\d{4}$/;
    if (empId === "" || !Employee_ID.test(empId)) {
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

  const validateContactNo = () => {
    const pattern = /^[0-9]+$/;
    if (contactNo.length !== 10 || !pattern.test(contactNo)) {
      setContactError("Contact No should have 10 digits.");
    } else {
      setContactError("");
    }
  };

  const validatePassword = () => {
    const passwordRegex =
      /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    if (password === "" || !passwordRegex.test(password)) {
      setPasswordError(
        "Password should be at least 6 characters long, have a maximum length of 16, and must contain a mix of characters, digits, special characters"
      );
    } else {
      setPasswordError("");
    }
  };

  const validateConfirmPassword = () => {
    if (confirmPassword !== password) {
      setConfirmPasswordError("confirmPassword should match with password");
    } else {
      setConfirmPasswordError("");
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
    validateContactNo();
    validatePassword();
    validateConfirmPassword();

    // setIsSubmitted(true);
    if (
      (nameError ||
        emailError ||
        employeeIdError ||
        dobError ||
        dojError ||
        locationError ||
        designationError ||
        contactError ||
        passwordError ||
        confirmPasswordError,
      name === "" ||
        email === "" ||
        empId === "" ||
        dob === "" ||
        doj === "" ||
        location === "" ||
        designation === "" ||
        contactNo === "" ||
        password === "" ||
        confirmPassword === "")
    ) {
      toast.error("Please fill every field.", {
        position: "top-right",
        autoClose: 3000,
        toastId,
      });
      return; // Exit the function if there are errors
    }
    //  else if (email !== "ankita@nucleusteq.com") {
    //   toast.error("Admin is already registered", {
    //     position: "top-right",
    //     autoClose: 3000,
    //   });
    //   return;
    // }
     else {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/adminRegistration",
          {
            name: name,
            email: email,
            empId: empId,
            dob: dob,
            doj: doj,
            location: location,
            designation: designation,
            contactNo: contactNo,
            password: hashedpassword,
            // confirmPassword: hashedpassword,
          }
        );

        // console.log(response.data.statusCode)
        // if(response.data.statusCode === 409){

        //   return alert("admin is already registered")

        // }
        if (response.status === 200) {
          toast.success("Admin Registered", {
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
          setName("");
          setEmail("");
          setEmpId("");
          setDOB("");
          setDOJ("");
          setLocation("");
          setDesignation("");
          setContactNo("");
          setPassword("");
          setConfirmPassword("");
          // alert("Employee Registered successfully")
        } else if (response.data.statusCode === 400) {
          console.log(response.data.statusCode);
          alert("backend validations failed");
        } else if (response.data.statusCode === 500) {
          //  console.log("Admin is already registered")
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
  return (
    // <div className="container">
    <div className="form">
      <div className="form-header">
        {/* <div className="RegistrationFormContainer" > */}
        <h2>Registration Form</h2>
      </div>

      <div className="form-body">
        <div className="form-group">
          <label className="form-label">Name</label>
          <input
            className="form-input"
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

        <div className="form-group">
          <label className="form-label">Email</label>
          <input
            className="form-input"
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

        <div className="form-group">
          <label className="form-label">EmployeeId</label>
          <input
            className="form-input"
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
        <div className="form-group">
          <label className="form-label">DOB</label>
          <input
            className="form-input"
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

        <div className="form-group">
          <label className="form-label">DOJ</label>
          <input
            className="form-input"
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

        <div className="form-group">
          <label className="form-label">location</label>
          <select
            className="form-dropdown"
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

        <div class="form-group">
          <label className="form-label">designation</label>
          <select
            className="form-dropdown"
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

        <div className="form-group">
          <label className="form-label">contactNo</label>
          <input
            className="form-input"
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
          <span style={{ fontSize: "12px", color: "red" }}>{contactError}</span>
        )}

        <div className="form-group">
          <label className="form-label">password</label>
          <input
            className="form-input"
            type="password"
            placeholder="Enter Password"
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            onBlur={validatePassword}
          />
        </div>
        {passwordError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {passwordError}
          </span>
        )}
        <div className="form-group">
          <label className="form-label">Confirm Password</label>
          <input
            className="form-input"
            type="password"
            placeholder="Enter Confirm Password"
            value={confirmPassword}
            onChange={(event) => {
              setConfirmPassword(event.target.value);
            }}
            onBlur={validateConfirmPassword}
          />
        </div>
        {confirmPasswordError && (
          <span style={{ fontSize: "12px", color: "red" }}>
            {confirmPasswordError}
          </span>
        )}
      </div>
      <div className="buttons">
        <button
          type="submit"
          className="button-cancel"
          onClick={redirectToLogin}
        >
          Back to Login
        </button>
        <button type="submit" className="button-submit" onClick={save}>
          Register
        </button>
      </div>
    </div>
    //  </div>
  );
}

export default AdminRegistration;
