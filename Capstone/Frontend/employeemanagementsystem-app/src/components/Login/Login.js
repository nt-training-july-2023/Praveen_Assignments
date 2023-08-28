import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './LoginStyle.css'



function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [message1, setMessage1] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        email,
        password,
      });

      if (response.data.statusCode === 200) {

        // console.log(response.data.statusCode);
        
        setMessage1('Login Successful');
        setEmail('');
        setPassword('');
        setMessage('');
       }
     
        else if (response.data.statusCode === 401) {
          // console.log(response.data.statusCode);
          setMessage('Incorrect password');
          setMessage1('');
         
        }else if (response.data.statusCode === 404) {
          setMessage('Email is not registered');
          setMessage1('');
         
        } 
    } 
    catch(error){
          setMessage('Error Occured');
    }
  };

  const redirectToRegister =()=>{
    navigate('/register');
  };

  return (

    <div className="login-page">
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
      <div className="message">{message}</div>
      <div className="message1">{message1}</div>
      <div className="Admin">
         Not a user? {""}
         <span style={{cursor: "pointer" , color:"blue"}} onClick={redirectToRegister}>
          Sign Up
         </span>
      
      </div>
    </div>
  </div>
































   
    // <div className="Login-form-container">
    //     <form className="Login-form" >
    //       <div className="Login-form-content">
    //         <h3 className="Login-form-title">Login</h3>
    //         <div className="text-center" style={{marginBottom:"25px"}}>
    //           Not registered yet?{" "}
    //           <span className="link-primary" style={{cursor:"pointer"}} /*onClick={redirectToRegister}*/>
    //             Sign Up
    //           </span>
    //         </div>
    //         <div className="form-group mt-1">
    //           <label>Email address</label>
    //           <input
    //             type="email"
    //             className="form-control mt-2"
    //             value={email}
    //             onChange={(e) => setEmail(e.target.value)}
    //             required
    //           />
    //         </div>
    //         <div className="form-group mt2">
    //           <label>Password</label>
    //           <input
    //             type="password"
    //             className="form-control mt-2"
    //             value={password}
    //             onChange={(e) => setPassword(e.target.value)}
    //             required
    //           />
    //         </div>
    //         <div className="d-grid gap-2 mt-3">
    //           <button type="submit"  className="btn btn-primary">
    //             Submit
    //           </button>

              

    //           {/* <button type="submit" onClick={redirectToRegister} className="btn btn-primary">
    //             Register
    //           </button> */}
    //         </div>
    //       </div>
    //     </form>
    //   </div>  
    );
}

export default Login;
