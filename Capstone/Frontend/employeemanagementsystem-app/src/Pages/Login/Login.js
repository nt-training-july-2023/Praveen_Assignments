import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./LoginStyle.css";
import { Base64 } from "js-base64";
import { toast } from "react-toastify"; // Import toast and ToastContainer from react-toastify
import "react-toastify/dist/ReactToastify.css"; // Import the default styles
import loginService from "../../Service/LoginService";
import Button from "../../components/Button";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [message1, setMessage1] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const navigate = useNavigate();
  const hashedpassword = Base64.encode(password);
  const isLoggedIn = localStorage.getItem("IsLoggedIn");
  const role = localStorage.getItem("userRole");

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
      const loginData = {
        email: email,
        password: hashedpassword,
      };

      const response = await loginService.login(loginData); // Use await here

      if (response.status === 200) {
        setMessage1("Login Successful");
        setEmail("");
        setPassword("");
        setMessage("");
        console.log(response.data.role);

        localStorage.setItem("IsLoggedIn", true);
        localStorage.setItem("userRole", response.data.role);
        localStorage.setItem("id", response.data.id);
        localStorage.setItem("name", response.data.name);

        if (response.data.role === "Admin") {
          navigate("/adminDashboard");
        } else if (response.data.role === "Employee") {
          navigate("/employeeDashboard");
        } else if (response.data.role === "Manager") {
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
  const page = () => {
    if (isLoggedIn) {
      console.log(isLoggedIn);
      if (role === "Admin") {
        navigate("/adminDashboard");
      } else if (role === "Manager") {
        navigate("/managerDashboard");
      } else if (role === "Admin") {
        navigate("/adminDashboard");
      }
    }
  };
  useEffect(() => {
    page();
  }, []);

  return (
    <>
      <div className="login-page">
        <div>
          <div className="main-header">
            <h1 className="lh1">Employee Management Portal</h1>
          </div>
          <div className="login-container">
            <h2>Login</h2>
            <br></br>
            <form onSubmit={handleLogin}>
              <div className="fields">
                <input
                  className="login-input"
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
                {emailError && <span className="span-error">{emailError}</span>}
                <br></br>
              </div>

              <div className="fields">
                <input
                  className="login-input"
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
                  <span className="span-error">{passwordError}</span>
                )}
                <br></br>
              </div>

              <Button 
              type="submit"
              text={"Login"}
              />
            </form>
            <br></br>
            <div className="Admin">
              Not a user? {""}
              <span
                style={{ cursor: "pointer", color: "blue" }}
                onClick={redirectToRegister}
              >
                Sign Up
              </span>
              <div className="message">
                {message} <br></br>
              </div>
              <div className="message1">
                {message1}
                <br></br>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
