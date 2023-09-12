import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./LoginStyle.css";
import { Base64 } from "js-base64";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css"; // Import the default styles

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
        navigate("/adminDashboard");
        
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
    <div className="login-page">
      {/* <header>Employee Management Portal</header> */}
      {/* <h2>Employee Management Portal</h2> */}
      <div className="login-container">
        <h2>Login</h2>
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

          {emailError && (
            <span style={{ fontSize: "12px", color: "red" }}>{emailError}</span>
          )}
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
          {passwordError && (
            <span style={{ fontSize: "12px", color: "red" }}>
              {passwordError}
            </span>
          )}
          <button type="submit">Login</button>
        </form>
        <div className="message">{message}</div>
        <div className="message1">{message1}</div>

        <div className="Admin">
          Not a user? {""}
          <span
            style={{ cursor: "pointer", color: "blue" }}
            onClick={redirectToRegister}
          >
            Sign Up
          </span>
        </div>
      </div>
    </div>
  );
}

export default Login;
