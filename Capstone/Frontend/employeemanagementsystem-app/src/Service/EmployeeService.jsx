import axios from "axios";
import { MYPROFILE_BASE_URL, GET_ALL_BASE_URL, UPDATE_SKILLS_BASE_URL} from "./API";

class EmployeeService{
    getMyProfile(id){
        return axios.get(MYPROFILE_BASE_URL+id);
    }
    getAll(){
        return axios.get(GET_ALL_BASE_URL);
    }
    updateSkills(id,skills){
        return axios.put(UPDATE_SKILLS_BASE_URL+id,{
            skills:skills
        });
    }

}
const employeeService = new EmployeeService();
export default employeeService;