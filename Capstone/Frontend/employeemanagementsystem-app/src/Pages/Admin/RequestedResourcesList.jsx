import React, { useEffect, useState } from "react";
import axios from "axios";
import "./RequestedResourceList.css";
import { useNavigate } from "react-router-dom";
import adminService from "../../Service/AdminService";
import Button from "../../components/Button";
import UnauthorizedPage from "../Unauthorized";
import { toast } from "react-toastify";
function RequestedResourcesList() {
  const [RRList, setRRList] = useState([]);
  const [deleteResponse, setDeleteResponse] = useState("");
  const [acceptResponse, setAcceptResponse] = useState("");
  const [isLoading, setIsLoading] = useState(true); // Add a loading state

  useEffect(() => {
    getAllRequestedResources();
  }, []);
  const navigate = useNavigate();

  const getAllRequestedResources = async () => {
    try {
      const response = await adminService.requestedResource();
      setRRList(response.data);
      setIsLoading(false);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleReject = async (id) => {
    try {
      const response = await adminService.rejectRequestedResource(id);
      setDeleteResponse(response.data);
      getAllRequestedResources();
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleAcceptClick = async (id) => {
    try {
      const response = await adminService.acceptRequestedResource(id);
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
    return <UnauthorizedPage />;
  }
  return (
    <div>
      <h1 className="RR-head">REQUESTED RESOURCES</h1>
      <Button
        type="button"
        className={"RR-closeee"}
        onClick={handleCancel}
        text={"Back To home"}
      />

      {isLoading ? ( // Check if data is still loading
        <p>Loading...</p>
      ) : RRList.length === 0 ? ( // Check if RRList is empty
        <h1 className="RR-h1">No pending requests</h1>
      ) : (
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
                    <Button
                      className="accept"
                      onClick={() => handleAcceptClick(requ.id)}
                      text={"Accept"}
                    />
                    <Button
                      className={"reject"}
                      onClick={() => handleReject(requ.id)}
                      text={"Reject"}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default RequestedResourcesList;
