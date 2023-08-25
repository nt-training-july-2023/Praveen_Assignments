import React, { useState } from 'react'
import axios from 'axios';
import './AdminStyle.css';

function Admin() {
    // const [id, setId] = useState("");
    const [name, setName] = useState("");
    const[email, setEmail] = useState("");
    const[emp_Id, setEmp_Id] = useState("");
    const[dob, setDOB] = useState("");
    const[doj, setDOJ] = useState("");
    const[location , setLocation] = useState("");
    const[designation , setDesignation] = useState("");
    const[contact_no, setContact_no] = useState("");
    const[password, setPassword] = useState("");

    async function save(event)
    {
        event.preventDefault();
    try
        {
         await axios.post("http://localhost:8080/api/adminRegistration",
        {
        
        name : name,
        email:email,
        emp_Id:emp_Id,
        dob: dob,
        doj:doj,
        location:location,
        designation:designation,
        contact_no:contact_no,
        password:password

      
        
        });
          alert("Employee Registation Successfully");
        //   setId("");
          // setName("");
          // setEmail("");
          // setEmp_Id("");
          // setDOB("");
          // setDOJ("");
          // setLocation("");
          // setDesignation("");
          // setContact_no("");
          // setPassword("");

        
        
        }
    catch(err)
        {
          alert("User Registation Failed");
        }

   }
    return (
        <div class="container" >
        <form>
        <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" placeholder="Enter Name"
             value={name}
            onChange={(event) =>
              {
                setName(event.target.value);      
              }}
            />
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" placeholder="Enter Email"
             value={email}
             onChange={(event) =>
               {
                setEmail(event.target.value);      
               }}
            />
        </div>

        <div class="form-group">
            <label>EmployeeId</label>
            <input type="text" class="form-control" placeholder="Enter Employee ID"
             value={emp_Id}
             onChange={(event) =>
               {
                setEmp_Id(event.target.value);      
               }}
            />
        </div>

        <div class="form-group">
            <label>DOB</label>
            <input type="date" class="form-control" placeholder="Enter DOB"
            value={dob}
            onChange={(event) =>
              {
                setDOB(event.target.value);      
              }}
           />
        </div>

        <div class="form-group">
            <label>DOJ</label>
            <input type="date" class="form-control" placeholder="Enter DOJ"
            value={doj}
            onChange={(event) =>
              {
                setDOJ(event.target.value);      
              }}
           />
        </div>

        <div class="form-group">
            <label>location</label>
            <input type="text" class="form-control" placeholder="Enter location"
            value={location}
            onChange={(event) =>
              {
                setLocation(event.target.value);      
              }}
           />
        </div>

        <div class="form-group">
            <label>designation</label>
            <input type="text" class="form-control" placeholder="Enter Designation"
            value={designation}
            onChange={(event) =>
              {
                setDesignation(event.target.value);      
              }}
           />
        </div>

        <div class="form-group">
            <label>contact_no</label>
            <input type="number" class="form-control" placeholder="Enter Contact_Number"
            value={contact_no}
            onChange={(event) =>
              {
                setContact_no(event.target.value);      
              }}
           />
        </div>

        <div class="form-group">
            <label>password</label>
            <input type="password" class="form-control" placeholder="Enter Password"
            value={password}
            onChange={(event) =>
              {
                setPassword(event.target.value);      
              }}
           />
        </div>
        
        <button class="btn btn-primary mt-4"  onClick={save}>Register</button>
        </form>

      </div>
    );
}

export default Admin