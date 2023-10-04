import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import SingleManagerCard from "./SingleManager";
import "./Manager.css";
import adminService from "../../../Service/AdminService";

export default function ManagerList() {
  const [employees, setEmployees] = useState([]);

  const getAllManagers = async () => {
    try {
      const response = await adminService.getAllManagers();
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  
  useEffect(() => {
    getAllManagers();
  }, []);
  //   console.log(employees)
  return (
    <div className="card-container">
      {employees.map((employee) => {
        return <SingleManagerCard employee={employee} />;
      })}
    </div>
  );
}
