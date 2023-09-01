import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './LoginStyle.css'
import { Base64 } from 'js-base64';



function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [message1, setMessage1] = useState('');
  const navigate = useNavigate() 
  const hashedpassword= Base64.encode(password) 

  const handleLogin = async (event) => {
    event.preventDefault();

   

    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        email:email,
        password:hashedpassword,
      });
      if(email===""||password===""){
        return alert("please fill the details");
      }
      if (response.data.statusCode === 200) {

        // console.log(response.data.statusCode);
        // alert("login successful");
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
    <div className='login-page'>
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

    );
}

export default Login;
