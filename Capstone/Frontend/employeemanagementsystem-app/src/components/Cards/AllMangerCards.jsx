import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import SingleManagerCard from "./SingleManager";
import "./Manager.css";

export default function ManagerList() {
  const navigate = useNavigate();
  const [employees, setEmployees] = useState([]);

  const getAllManager = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/all/Manager");
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  useEffect(() => {
    getAllManager();
  }, []);
//   console.log(employees)
  return (
    <div className="card-container">
      {employees.map((employee) => {
        // console.log(employee);
        // console.log(employee.id);
        return <SingleManagerCard employee={employee} />;
      })}
    </div>
  );
}
