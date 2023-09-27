import React, { useEffect, useState } from "react";
import axios from "axios";
import "./RequestedResourceList.css";
import { useNavigate } from "react-router-dom";
function RequestedResourcesList() {
  const [RRList, setRRList] = useState([]);
  const [deleteResponse, setDeleteResponse] = useState("");
  const [acceptResponse, setAcceptResponse] = useState("");

  useEffect(() => {
    getAllRequestedResources();
  }, []);
  const navigate = useNavigate();

  const getAllRequestedResources = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/RequestedResource",
        {
          // id:
          // employeeId,
          // employeeEmpId,
          // projectId,
          // managerId,
          // managerEmpId,
          // employeeName,
          // managerName,
          // projectName,
          // comment,
        }
      );
      // console.log(response.data);
      setRRList(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  console.log(RRList);

  const handleReject = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/Delete/RequestedResource/${id}`
      );
      setDeleteResponse(response.data);
      getAllRequestedResources();
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleAcceptClick = async (id) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/api/Accept/RequestedResource/${id}`
      );
      setAcceptResponse(response.data);
      getAllRequestedResources();
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };
  const handleCancel = (e) => {
    navigate("/AdminDashboard");
  };
  const userRole = localStorage.getItem("userRole");
  if (userRole !== "Admin") {
    return <h1>Unauthorized access</h1>;
  }
  return (
    <div>
      {/* <div><Link to="/adminDashboard">
        <button>Go to Main Page</button>
      </Link></div> */}
      <h1 className="RR-head">REQUESTED RESOURCES</h1>
      <button type="button" className="RR-closeee" onClick={handleCancel}>
        Back To home
      </button>

      <div className="table-container">
        <table className="custom-table">
          <thead>
            <tr>
              <th>Employee Name</th>
              <th>Project</th>
              <th>Manager Name</th>
              <th>Comment</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {RRList.map((requ) => (
              <tr key={requ.id}>
                <td>
                  {requ.employeeEmpId} - {requ.employeeName}
                </td>
                <td>{requ.projectName}</td>
                <td>
                  {requ.managerEmpId} - {requ.managerName}
                </td>
                <td>{requ.comment}</td>
                <td>
                  <button
                    className="accept"
                    onClick={() => handleAcceptClick(requ.id)}
                  >
                    Accept
                  </button>
                  <button
                    className="reject"
                    onClick={() => handleReject(requ.id)}
                  >
                    Reject
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default RequestedResourcesList;
