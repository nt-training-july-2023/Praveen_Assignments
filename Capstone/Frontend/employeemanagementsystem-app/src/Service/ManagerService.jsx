import axios from "axios";
import { FILTERED_EMPLOYEES_BASE_URL, IS_REQUESTED_BASE_URL, ADD_REQUEST_BASE_URL, GET_PROJECT_BY_MANAGER_ID_BASE_URL  } from "./API";

class ManagerService{
    getFilteredEmployees(){

    }
    isRequested(data){
        return axios.post(IS_REQUESTED_BASE_URL,data)
    }

    requestResource(data){
        return axios.post(ADD_REQUEST_BASE_URL,data)
    }
    projectByManagerId(id){
        return axios.get(GET_PROJECT_BY_MANAGER_ID_BASE_URL+id);
    }
    getFilteredEmployees(selectedSkills, showUnassigned) {
        return axios.get(FILTERED_EMPLOYEES_BASE_URL, {
          params: {
            selectedSkills: selectedSkills.join(","),
            showUnassigned,
          },
        });
      }

}

const managerService = new ManagerService()
export default managerService


