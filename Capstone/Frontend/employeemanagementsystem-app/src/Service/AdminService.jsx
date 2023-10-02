import axios from "axios";
import { REGISTER_BASE_URL,ADD_EMPLOYEE_BASE_URL,ADD_PROJECT_BASE_URL,UNASSIGN_PROJECT_BASE_URL,
 GET_ALL_EMPLOYEE_BASE_URL, ASSIGN_PROJECT_BASE_URL, GET_PROJECTS_BASE_URL,
 GET_ALL_REQUEST_BASE_URL, ACCEPT_REQUEST_BASE_URL, REJECT_REQUEST_BASE_URL, GET_ALL_MANAGERS_BASE_URL} from "./API";

 class AdminService{
    registerAdmin(data){
        return axios.post(REGISTER_BASE_URL,data);
    }
    addEmployee(data){
        return axios.post(ADD_EMPLOYEE_BASE_URL,data);
    }
    addProject(projectData){
        return axios.post(ADD_PROJECT_BASE_URL,projectData);
    }
    getAllManagers(){
        return axios.get(GET_ALL_MANAGERS_BASE_URL);
    }
    getProjects(){
        return axios.get(GET_PROJECTS_BASE_URL);
    }
    assignProject(id,data){
        return axios.put(ASSIGN_PROJECT_BASE_URL+id,data)
    }
    requestedResource(){
        return axios.get(GET_ALL_REQUEST_BASE_URL)
    }
    acceptRequestedResource(id){
        return axios.put(ACCEPT_REQUEST_BASE_URL+id);
    }
    rejectRequestedResource(id){
        return axios.delete(REJECT_REQUEST_BASE_URL+id);
    }
    getAllEmployees(){
        return axios.get(GET_ALL_EMPLOYEE_BASE_URL);
    }
    unassignProject(id){
        return axios.put(UNASSIGN_PROJECT_BASE_URL+id);
    }




}
const adminService = new AdminService()
export default adminService;