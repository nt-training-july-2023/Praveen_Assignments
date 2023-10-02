import axios from "axios";
import { LOGIN_BASE_URL } from "./API";

class LoginService{

    login(loginData){
        return axios.post(LOGIN_BASE_URL,loginData)
    }
}
 const loginService = new LoginService();
 export default loginService;
