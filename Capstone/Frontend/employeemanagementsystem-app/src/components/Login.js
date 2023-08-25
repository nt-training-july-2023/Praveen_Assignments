import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  async function save(event)
  {
      event.preventDefault();
  try
      {
       await axios.post("http://localhost:8080/api/adminRegistration",
      {
        email:email,
        password:password
    });
    alert("login");
    setEmail("");
    setPassword("");
      

        catch(err)
        {
          alert("Invalid Credentials");
        }

   }

            return (
                <div>
                <h1>Login Page</h1>
                <div>
                    <input
                    type="text"
                    placeholder="Username"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div>
                    <button onClick={handleLogin}>Login</button>
                </div>
                {message && <div>{message}</div>}
                </div>
            );
            };

            export default Login;
