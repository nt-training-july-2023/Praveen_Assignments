import React, { useState, useEffect } from "react";
import axios from "axios";
import "./EmployeeCarddd.css";
import MultipleSelectDropdown from "../../../components/MultipleSelectDropdown/MultipleSelectDropdown";
import SkillsDropDown from "../../../components/Dropdowns/Skills";
import { useNavigate } from "react-router-dom";
import {  toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function EmployeeCarddd() {
  const [employees, setEmployees] = useState([]);
  const [showUnassigned, setShowUnassigned] = useState(false);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const navigate = useNavigate();
  const id = localStorage.getItem("id");

  useEffect(() => {
    getAllEmployees();
  }, [selectedSkills, showUnassigned]);

  // const notify=()=>{
   
  //   toast.error("Project Already Requested", {
  //     position: "top-right",
  //     autoClose: 1000,
  //     toastId: "unique-toast-id" // Specify the unique toastId here
  //   });


  // }

  const Request_Resource = (employeeId) => {
    navigate(`/requestResource/${employeeId}`);
  };

  const IsRequested = async (employeeId) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/isRequested",
        {
          employeeId: employeeId,
          id: id,
        }
      );
     console.log(response.data)
      // Assuming the API response contains an 'isRequested' property
      const isRequested = response.data.requested;

      return isRequested; // Return the isRequested status
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const getAllEmployees = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/filteredEmployees", {
        params: {
          selectedSkills: selectedSkills.join(','),
          showUnassigned: showUnassigned
        }
      });

      // Fetch and set employees with isRequested status
      const employeesWithIsRequested = await Promise.all(
        response.data.map(async (employee) => {
          const isRequested = await IsRequested(employee.id);
          return { ...employee, isRequested };
        })
      );

      setEmployees(employeesWithIsRequested);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

    function reverseDateFormat(inputDate) {
    // Split the input date using the '-' separator
    const dateParts = inputDate.split("-");

    // Check if the input has three parts (year, month, day)
    if (dateParts.length === 3) {
      // Reverse the parts and join them with '-' separator
      const reversedDate =
        dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
      return reversedDate;
    } else {
      // Handle invalid input format
      return "Invalid Date Format";
    }
  }

  const toggleShowUnassigned = () => {
    setShowUnassigned((prevShowUnassigned) => !prevShowUnassigned);
  };

  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setSelectedSkills(selectedSkillsValues);
  };

//   return (
//     <div className="empty">
//       <div className="EC-Component">
//         <div className="MD-skill-filter">
//           <MultipleSelectDropdown
//             options={SkillsDropDown.map((skill) => ({
//               value: skill,
//               label: skill,
//             }))}
//             selectedOptions={selectedSkills.map((skill) => ({
//               value: skill,
//               label: skill,
//             }))}
//             onChange={handleSkillChange}
//             placeholder="Search Skills"
//           />
//         </div>
//         <div className="MD-filter">
//           <label>
//             <input
//               className="Radiobutton"
//               type="checkbox"
//               name="showUnassigned"
//               checked={showUnassigned}
//               onChange={toggleShowUnassigned}
//             />
//           </label>
//           <span>Show Unassigned</span>
//         </div>
//       </div>

//       <div className="MD-container">
//         <div className="MD-card-container">
//           {employees.map((employee) => (
//             <div className="MD-card" key={employee.id}>
//               <div className="MD-column1">
//                 <h2>{employee.name}</h2>
//                 <p>
//                   <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
//                     {employee.designation}
//                   </span>
//                 </p>
//                 <p style={{ marginTop: "1rem" }}>
//                   {employee.projectId ? (
//                     <p>
//                       <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
//                       {employee.projectName}
//                     </p>
//                   ) : (
//                     <p>
//                       <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
//                       N/A
//                     </p>
//                   )}
//                 </p>
//                 {employee.managerName ? (
//                   <p>
//                     <span style={{ fontWeight: "bold" }}>Manager : </span>
//                     {employee.managerName}
//                   </p>
//                 ) : (
//                   <p>
//                     <span style={{ fontWeight: "bold" }}>Manager:</span> Ankita
//                   </p>
//                 )}
//                 <p>
//                   <span style={{ fontWeight: "bold" }}>Contact : </span>
//                   {employee.contactNo}
//                 </p>
//                 <p>
//                   <span style={{ fontWeight: "bold" }}>Email : </span>
//                   {employee.email}
//                 </p>
//                 <p>
//                   <span style={{ fontWeight: "bold" }}>Skills : </span>
//                   {employee.skills.join(",")}
//                 </p>
//               </div>
//               <div className="MD-column2">
//                 <br></br>
//                 <p style={{ fontSize: "15px", marginTop: "-0.9rem" }}>
//                   <span style={{ fontWeight: "bold" }}>Employee id : </span>
//                   {employee.empId}
//                 </p>
//                 <br></br>
//                 <p style={{ marginTop: "0.8rem" }}>
//                   <span style={{ fontWeight: "bold" }}>DOB : </span>
//                   {reverseDateFormat(employee.dob)}
//                 </p>
//                 <p>
//                   <span style={{ fontWeight: "bold" }}>DOJ: </span>
//                   {reverseDateFormat(employee.doj)}
//                 </p>
//                 <p>
//                   <span style={{ fontWeight: "bold" }}>Location : </span>
//                   {employee.location}
//                 </p>
//                 {!employee.projectId && (
//                   <p>
//                     {employee.isRequested ? (
//                       <button className="MD-requested-button"
//                       // onClick={notify}
//                       disabled
//                       >
//                         Requested
//                       </button>
//                     ) : (
//                       <button
//                         className="MD-assign-button"
//                         // onClick={() => Request_Resource(employee.id)}
//                         onClick={() => {
//                           navigate(`/requestResource/${employee.id}`, {
//                             state: { empName: employee.name },
//                           });
//                         }}
//                       >
//                         Request Resource
//                       </button>
//                     )}
//                   </p>
//                 )}
//               </div>
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// }



return (
  <div className="empty">
    <div className="EC-Component">
      <div className="MD-skill-filter">
        <MultipleSelectDropdown
          options={SkillsDropDown.map((skill) => ({
            value: skill,
            label: skill,
          }))}
          selectedOptions={selectedSkills.map((skill) => ({
            value: skill,
            label: skill,
          }))}
          onChange={handleSkillChange}
          placeholder="Search Skills"
        />
      </div>
      <div className="MD-filter">
        <label>
          <input
            className="Radiobutton"
            type="checkbox"
            name="showUnassigned"
            checked={showUnassigned}
            onChange={toggleShowUnassigned}
          />
        </label>
        <span>Show Unassigned</span>
      </div>
    </div>

    <div className="MD-container">
      <div className="MD-card-container">
        {employees.length > 0 ? (
          employees.map((employee) => (
            <div className="MD-card" key={employee.id}>
              <div className="MD-column1">
                <h2>{employee.name}</h2>
                <p>
                  <span style={{ fontSize: "0.7rem", marginTop: "-0.2rem" }}>
                    {employee.designation}
                  </span>
                </p>
                <p style={{ marginTop: "1rem" }}>
                  {employee.projectId ? (
                    <p>
                      <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
                      {employee.projectName}
                    </p>
                  ) : (
                    <p>
                      <span style={{ fontWeight: "bold" }}>Project Name :</span>{" "}
                      N/A
                    </p>
                  )}
                </p>
                {employee.managerName ? (
                  <p>
                    <span style={{ fontWeight: "bold" }}>Manager : </span>
                    {employee.managerName}
                  </p>
                ) : (
                  <p>
                    <span style={{ fontWeight: "bold" }}>Manager:</span> Ankita
                  </p>
                )}
                <p>
                  <span style={{ fontWeight: "bold" }}>Contact : </span>
                  {employee.contactNo}
                </p>
                <p>
                  <span style={{ fontWeight: "bold" }}>Email : </span>
                  {employee.email}
                </p>
                <p>
                  <span style={{ fontWeight: "bold" }}>Skills : </span>
                  {employee.skills.join(",")}
                </p>
              </div>
              <div className="MD-column2">
                <br></br>
                <p style={{ fontSize: "15px", marginTop: "-0.9rem" }}>
                  <span style={{ fontWeight: "bold" }}>Employee id : </span>
                  {employee.empId}
                </p>
                <br></br>
                <p style={{ marginTop: "0.8rem" }}>
                  <span style={{ fontWeight: "bold" }}>DOB : </span>
                  {reverseDateFormat(employee.dob)}
                </p>
                <p>
                  <span style={{ fontWeight: "bold" }}>DOJ: </span>
                  {reverseDateFormat(employee.doj)}
                </p>
                <p>
                  <span style={{ fontWeight: "bold" }}>Location : </span>
                  {employee.location}
                </p>
                {!employee.projectId && (
                  <p>
                    {employee.isRequested ? (
                      <button className="MD-requested-button"
                      // onClick={notify}
                      disabled
                      >
                        Requested
                      </button>
                    ) : (
                      <button
                        className="MD-assign-button"
                        // onClick={() => Request_Resource(employee.id)}
                        onClick={() => {
                          navigate(`/requestResource/${employee.id}`, {
                            state: { empName: employee.name },
                          });
                        }}
                      >
                        Request Resource
                      </button>
                    )}
                  </p>
                )}
              </div>
            </div>
          ))
        ) : (
          <div>
          <h1>No employees present</h1>
          </div>
        )}
        
      </div>
    </div>
  </div>
);
        }


export default EmployeeCarddd;