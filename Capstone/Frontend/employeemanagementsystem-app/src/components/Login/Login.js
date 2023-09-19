import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./LoginStyle.css";
import { Base64 } from "js-base64";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css"; // Import the default styles
import Main_Header from "../Header/Main_Header";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [message1, setMessage1] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();
  const hashedpassword = Base64.encode(password);

  const validateEmail = () => {
    if (email === "") {
      setEmailError("Email should not be empty");
    }
  };
  const validatePassword = () => {
    if (password === "") {
      setPasswordError("password should not be empty");
    }
  };

  const handleLogin = async (event) => {
    event.preventDefault();
    const toastId = "handleLogin";
    validateEmail();
    validatePassword();
    if (email === "" || password === "") {
      toast.error("Please fill every field.", {
        position: "top-right",
        autoClose: 3000,
        toastId,
      });
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        email: email,
        password: hashedpassword,
      });

      if (response.status === 200) {
        setMessage1("Login Successful");
        setEmail("");
        setPassword("");
        setMessage("");
        console.log(response.data.role)


        localStorage.setItem('IsLoggedIn',true);
        localStorage.setItem('userRole',response.data.role);
        localStorage.setItem('email',response.data.email);

        if(response.data.role==="Admin"){
          navigate("/adminDashboard");
        }
        else if(response.data.role==="Employee"){
          navigate("/employeeDashboard");
        }
        else if (response.data.role==="Manager"){
          navigate("/managerDashboard");
        }
        
      }
    } catch (error) {
      setMessage(error.response.data.message);
      setMessage1("");
    }
  };

  const redirectToRegister = () => {
    navigate("/register");
  };




  return (
    <>
    
    {/* <Main_Header /> */}
    <div className="login-page">
      <div>
      <div className="main-header">
        <h1 className="lh1"> 
        Employee Management Portal
        </h1>
      </div>
      {/* <h2 className="login_heading">Employee Management Portal</h2> */}
      <div className="login-container">
        <h2>Login</h2>
        <br></br>
        <form onSubmit={handleLogin}>
          <div className="fields">
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                setEmailError("");
              }}
              onBlur={validateEmail}
            />
          </div>
         <div className="error-message">
          {/* {!emailError&&(<span></span>)} */}
          {emailError && (
            <span className="span-error">
              {emailError}
              </span>
          )}
          <br></br>
          </div>
        
          
          <div className="fields">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
                setPasswordError("");
              }}
              onBlur={validatePassword}
            />
          </div>
          <div className="error-message">
            
          {passwordError && (
            <span className="span-error">
              {passwordError}
            </span>
          )}
           <br></br>
          </div>
        
          <button type="submit">Login</button>
        </form>
        {/* <div className="message">{message} <br></br></div>
        <div className="message1">{message1}<br></br></div> */}
        <br></br>
        <div className="Admin">
          Not a user? {""}
          <span
            style={{ cursor: "pointer", color: "blue" }}
            onClick={redirectToRegister}
          >
            Sign Up
          </span>
          <div className="message">{message} <br></br></div>
        <div className="message1">{message1}<br></br></div>
        </div>
      </div>
      </div>
    </div>
    
    
    </>
  );
}

export default Login;
