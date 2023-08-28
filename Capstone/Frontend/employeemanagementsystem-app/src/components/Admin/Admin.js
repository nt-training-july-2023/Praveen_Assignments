import React, { useState } from 'react'
import axios from 'axios';
import './AdminStyle.css';
import { useNavigate } from 'react-router-dom';


function Admin() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [emp_Id, setEmp_Id] = useState('');
  const [dob, setDOB] = useState('');
  const [doj, setDOJ] = useState('');
  const [location, setLocation] = useState('');
  const [designation, setDesignation] = useState('');
  const [contact_no, setContact_no] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isSubmitted, setIsSubmitted]= useState(false);



  const [nameError,setNameError]=useState(''); 
  const [emailError, setEmailError] = useState('');
  const [employeeIdError, setEmployeeIdError] = useState('');
  const [dobError, setDobError] = useState('');
  const [dojError, setDojError] = useState('');
  const [locationError, setLocationError] = useState('');
  const [designationError, setDesignationError]=useState('');
  const [contactError, setContactError]=useState('');
  const [passwordError, setPasswordError]=useState('');
  const [confirmPasswordError, setConfirmPasswordError]=useState('');

  const navigate = useNavigate();

  const redirectToLogin = () => {
    navigate('/');
  };

  const locationOptions = [
    "Raipur",
    "Indore",
    "Hyderbad"
  ];
  
  const designationOptions = [
    "Engineer",
    "SDE-1",
    "SDE-2"
  ];
  

    const validateName = () => {
      if (name === '') {
        setNameError('Name cannot be empty');
      } else if (name.match(/\d/)) {
        setNameError('Name cannot contain digits');
      } else {
        setNameError('');
      }
    };

    const validateEmail = () => {
      if (!email.endsWith('@nucleusteq.com')) {
        setEmailError('Email must end with @nucleusteq.com');
      } else {
        setEmailError('');
      }
    };

    const validateEmployeeId = () => {
      const Employee_ID = /^N\d{4}$/;
      if (emp_Id === '' || !Employee_ID.test(emp_Id)) {
        setEmployeeIdError('Employee ID should be in the pattern NXXXX (X should be numbers)');
      } else {
        setEmployeeIdError('');
      }
    };

    const validateDOB = () =>{
      if (dob === '') {
        setDobError('Enter DOB')

      }
      else{
        setDobError('');
      }  
    }

    const validateDOJ = () =>{
      if (doj === '') {
        setDojError('Enter DOB')
      }
      else{
        setDojError('');
      }  
    }

    const validateLocation = () =>{
      if (location === '') {
        setLocationError('select location')
      }
      else{
        setLocationError('');
      }  
    };

    
    const validateDesignation = () =>{
      if (designation === '') {
        setDesignationError('select designation')
      }
      else{
        setDesignationError('');
      }  
    };

    const validateContactNo = () => {
      const pattern = /^[0-9]+$/;
      if (contact_no.length !== 10 || !pattern.test(contact_no)) {
        setContactError('Contact No should have 10 digits.');
      } else {
        setContactError('');
      }
    };

    const validatePassword  = () =>{
      const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
      if (password === '' || !passwordRegex.test(password)) {
        setPasswordError('Password should be at least 6 characters long, have a maximum length of 16, and must contain a mix of characters, digits, special characters')

      }
      else{
        setPasswordError('')
      }
    };

    const validateConfirmPassword = () =>{
      if(confirmPassword!=password){
        setConfirmPasswordError('confirmPassword should match with password')
      }
      else{
        setConfirmPasswordError('');
      }
    };

  

 

  // const validateForm = () => {
  //   let isValid = true;

  //   if(!isValid){
  //     setIsSubmitted(true);
  //   }

  //   if (name === '') {
  //     isValid = false;
  //   }

  //   const emailRegex = /^.+@Nucleusteq\.com$/i;
  //   if (!emailRegex.test(email)) {
  //     isValid = false;
  //   }
    
  //   const Employee_ID = /^N\d{4}$/;
  //   if (emp_Id === '' || !Employee_ID.test(emp_Id)) {
  //     isValid = false;
  //   }

  //   if (dob === '') {
  //     isValid = false;
  //   }

  //   if (doj === '') {
  //     isValid = false;
  //   }

  //   if (location === '') {
  //     isValid = false;
  //   }

  //   if (designation === '') {
  //     isValid = false;
  //   }

  //   var phone_no = /^([0-9]){10}$/;
  //   if (!phone_no.test(contact_no)) {
  //     isValid = false;
  //   }

  //   const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
  //   if (password === '' || !passwordRegex.test(password)) {
  //     isValid = false;
  //   }

  //   if(confirmPassword !== password){
  //     isValid= false;
  //   }


  //   return isValid;
  // };

  const save = async (event) => {

    // if(name==="" || email==="" ||){
    //   alert("Every field is mandatory!");
    // }
    
    event.preventDefault();
    validateName();
    validateEmail();
    validateEmployeeId();
    validateDOB();
    validateDOJ();
    validateLocation();
    validateDesignation();
    validateContactNo();
    validatePassword();
    validateConfirmPassword();

    // setIsSubmitted(true);

    if (
      // !nameError &&
      // !emailError &&
      // !employeeIdError &&
      // !dobError &&
      // !dojError &&
      // !locationError &&
      // !designationError &&
      // !contactError &&
      // !passwordError &&
      // !confirmPasswordError
      nameError ||
      emailError ||
      employeeIdError ||
      dobError ||
      dojError ||
      locationError ||
      designationError ||
      contactError ||
      passwordError ||
      confirmPasswordError
    ){
          alert('Please fill every field.');
          return;// Exit the function if there are errors
        }

      try {
        await axios.post('http://localhost:8080/api/adminRegistration', {
          name: name,
          email: email,
          emp_Id: emp_Id,
          dob: dob,
          doj: doj,
          location: location,
          designation: designation,
          contact_no: contact_no,
          password: password,
          confirmPassword: confirmPassword
        
        });
      
        alert("Employee Registered successfully");
        // Reset form fields after successful registration
        setName('');
        setEmail('');
        setEmp_Id('');
        setDOB('');
        setDOJ('');
        setLocation('');
        setDesignation('');
        setContact_no('');
        setPassword('');
        setConfirmPassword('');

      } catch (err) {
        alert('User Registration Failed');
      }
    
  };
    return (  
        <div className="container" >
          <form>
            <div className="form1">
                <label>Name</label>
                <input 
                  type="text" 
                  placeholder="Enter Name"
                  value={name}
                  onChange={(event) =>setName(event.target.value)}
                  onBlur={validateName}
                />
                {nameError&& <div className='error'>{nameError}</div>}
                {/* {isSubmitted && name === '' && <span className="error">*Name cannot be empty</span>} */}
            </div>

          <div className="form1">
              <label>Email</label>
              <input 
              type="email" 
              placeholder="Enter Email example abc@Nucleusteq.com"
              value={email}
              onChange={(event) =>
                {
                  setEmail(event.target.value);      
                }}
                onBlur={validateEmail}
              />
              {emailError&&<div className='error'>{emailError}</div>}
               {/* {isSubmitted && !email.match(/^.+@Nucleusteq\.com$/i) && <span className="error">*Enter a valid email</span>} */}
          </div>

          <div className="form1">
              <label>EmployeeId</label>
              <input 
              type="text"
              placeholder="Enter Employee ID"
              value={emp_Id}
              onChange={(event) =>
                {
                  setEmp_Id(event.target.value);      
                }}
                onBlur={validateEmployeeId}
              />
              {employeeIdError&&<div className='error'>{employeeIdError}</div>}
              {/* {isSubmitted && !emp_Id.match(/^N\d{4}$/) && <span  className="error">*Employee ID should be in format NXXXX</span>} */}
          </div>

          <div className="form1">
              <label>DOB</label>
              <input 
                type="date" 
                placeholder="Enter DOB"
                value={dob}
                onChange={(event) =>
                  {
                    setDOB(event.target.value);      
                  }}
                  onBlur={validateDOB}
            />
            {dobError&&<div className='error'>{dobError}</div>}
             {/* {isSubmitted && dob=== '' && <span className="error">*Enter dob</span>} */}
          </div>

          <div className="form1">
              <label>DOJ</label>
              <input 
                type="date"
                placeholder="Enter DOJ"
                value={doj}
                onChange={(event) =>
                  {
                    setDOJ(event.target.value);      
                  }}onBlur={validateDOJ}
            />
            {dojError&& <div className='error'>{dojError}</div>}
             {/* {isSubmitted && doj === '' && <span className="error">*Enter doj</span>} */}
          </div>

          <div className="form1">
              <label>location</label>
              <select
                className='dropdown'
                value={location}
                onChange={(event) => setLocation(event.target.value)}
                onBlur={validateLocation}
               >
                <option value="">Select Location</option>
                {locationOptions.map((option, index) => (
                  <option key={index} value={option}>
                   {option}
                  </option>
                ))}

              </select>
              {locationError&& <div className='error'>{locationError}</div>}
             {/* {isSubmitted && location === '' && <span className="error">*Choose location from below</span>} */}
          </div>

          <div class="form1">
              <label>designation</label>
              <select
                className='dropdown'
                value={designation}
                onChange={(event) => setDesignation(event.target.value)}
                onBlur={validateDesignation}
              >
                <option value="">Select Designation</option>
                {designationOptions.map((option, index) => (
                  <option key={index} value={option}>
                    {option}
                  </option>
                ))}
              </select>
              {designationError&&<div className='error'>{designationError}</div>}
              
             {/* {isSubmitted && designation  === '' && <span className="error">*Choose the role from below</span>} */}
          </div>

          <div className="form1">
              {/* <label>contact_no</label> */}
              <input 
                type="text" 
                placeholder="Enter Contact_Number"
                value={contact_no}
                onChange={(event) =>
                  {
                    setContact_no(event.target.value);      
                  }}
                onBlur={validateContactNo}  
            />
            {contactError&& <div className='error'>{contactError}</div>}
            {/* {isSubmitted && !contact_no.match(/^([0-9]){10}$/) && <span className="error">*Enter a valid phone number</span>} */}
          </div>

          <div className="form1">
              <label>password</label>
              <input 
                type="password" 
                placeholder="Enter Password"
                value={password}
                onChange={(event) =>
                  {
                    setPassword(event.target.value);      
                  }}
                onBlur={validatePassword}  
            />
            {passwordError&&<div className='error'>{passwordError}</div>}

            {/* {isSubmitted && !(/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/).test(password) && <span className="error">*Password should be at least 6 characters long, have a maximum length of 16, and must contain a mix of characters, digits, special characters</span>} */}
          </div>
          <div className="form1">
              <label>Confirm Password</label>
              <input 
                type="password" 
                placeholder="Enter Confirm Password"
                value={confirmPassword}
                onChange={(event) =>
                  {
                    setConfirmPassword(event.target.value);      
                  }}
                 onBlur={validateConfirmPassword} 
            />
            {confirmPasswordError&& <div className='error'>{confirmPasswordError}</div>}
            {/* {isSubmitted&&confirmPassword !== password && <span className="error">*Please ensure that the confirmed password matches the password you entered</span>} */}
          </div>
          <div className='btn-container'>
          <button className="btn"  onClick={save}>
            Register
            </button>
            <button className='btn' onClick={redirectToLogin}>
              Back to Login
            </button>
          </div>
        </form>

      </div>
    );
}

export default Admin