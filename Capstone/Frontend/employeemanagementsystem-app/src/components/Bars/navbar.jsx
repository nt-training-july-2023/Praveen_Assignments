import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "./navbar.css";
// import hamburger from "../../assets/hamburger.png";
const Navbar = () => {
  const [showNavbar, setShowNavbar] = useState(false);
  const handleShowNavbar = () => {
    setShowNavbar(!showNavbar);
  };
  return (
    <nav className="navbar">
      <div className="nav-container">
        <div className="logo">
          <p> Admin Dashboard</p>
        </div>
        <div className="menu-icon" onClick={handleShowNavbar}>
          {" "}
          {/* <img src={hamburger} alt="Menu" /> */}
        </div>
        <div className={`nav-elements ${showNavbar ? "active" : ""} `}>
          <ul>
            <li>
              <NavLink to="/" className="nav-link">
                 Home
              </NavLink>
            </li>
            <li>
              <NavLink to="/about" className="nav-link">
                 Employee
              </NavLink>
            </li>
            <li>
              <NavLink to="/categories/add" className="nav-link">
                Manager
              </NavLink>
            </li>
            {/* <li className="nav-item dropdown">
    <a
        className="nav-link dropdown-toggle"
        id="navbarDropdown"
        role="button"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
    >
        Categories
    </a>
    <div
        className="dropdown-menu dropdown-menu-right"
        aria-labelledby="navbarDropdown"
    >
        <NavLink className="dropdown-item" to="/categories/add">
            Add Category
        </NavLink>
        <NavLink className="dropdown-item" to="/categories/list">
            List Category
        </NavLink>
    </div>
</li> */}
            <li>
              <NavLink to="/login" className="nav-button" id="login-button">
                Login
              </NavLink>
            </li>
            <li>
              <NavLink to="/register" className="nav-button" id="reg-button">
                {" "}
                Register
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};
export default Navbar;
