import React from "react";
import "./Unauthorized.css";
import Button from "../components/Button";
import { useNavigate } from "react-router-dom";

const UnauthorizedPage = () => {
  const navigate = useNavigate();
  const redirectToHome = () => {
    navigate("/");
  };
  return (
    <div className="unauthorized-div">
    <div className="unauthorized-container">
      <div class="warning-symbol">&#9888;</div>

      <h1>Unauthorized Access</h1>
      <p>You do not have permission to access this page.</p>
      <p>
        <Button
          onClick={redirectToHome}
          className={"Unauthorized-button"}
          text={"Back to home"}
          >
          
        </Button>
        {/* <a href="/">Return to the homepage</a> */}
      </p>
    </div>
    </div>
  );
};

export default UnauthorizedPage;