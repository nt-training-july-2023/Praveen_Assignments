import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import SingleManagerCard from "./SingleManager";
import "./Manager.css";
import adminService from "../../../Service/AdminService";

export default function ManagerList() {
  const [employees, setEmployees] = useState([]);

  // const getAllManager = async () => {
  //   try {
  //     // const response = await axios.get("http://localhost:8080/api/all/Manager");
  //     // setEmployees(response.data);
  //     adminService.getAllManagers().then((response) => {
  //       setEmployees(response.data);
  //     });
  //   } catch (error) {
  //     console.error("Error fetching data:", error);
  //   }
  // };
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
