export const BASE_URL   =    "http://localhost:8080/api/"

//Login

export const LOGIN_BASE_URL     =   BASE_URL + "login";

// Admin

export const REGISTER_BASE_URL                  =     BASE_URL + "adminRegistration";
export const ADD_EMPLOYEE_BASE_URL              =     BASE_URL + "addEmployee";
export const GET_ALL_EMPLOYEE_BASE_URL          =     BASE_URL + "all/Employee";
export const GET_ALL_MANAGERS_BASE_URL          =     BASE_URL + "all/Manager";
export const ASSIGN_PROJECT_BASE_URL            =     BASE_URL + "updateEmployee/";
export const UNASSIGN_PROJECT_BASE_URL          =     BASE_URL + "unAssign/Project/";
export const ADD_PROJECT_BASE_URL               =     BASE_URL + "addProject";
export const GET_PROJECTS_BASE_URL              =     BASE_URL + "projectCards";
// export const GET_PROJECT_BY_MANAGER_ID_BASE_URL =     BASE_URL + "projectCards/";
export const GET_ALL_REQUEST_BASE_URL           =   BASE_URL + "RequestedResource";
export const ACCEPT_REQUEST_BASE_URL            =   BASE_URL + "Accept/RequestedResource/";
export const REJECT_REQUEST_BASE_URL            =   BASE_URL + "Delete/RequestedResource/";


//Employee

export const MYPROFILE_BASE_URL             =     BASE_URL + "employee/id/";
export const GET_ALL_BASE_URL               =     BASE_URL + "allManagersAndEmployees";
export const UPDATE_SKILLS_BASE_URL         =     BASE_URL + "updateSkill/";

//Managers
export const FILTERED_EMPLOYEES_BASE_URL    =     BASE_URL + "filteredEmployees";
export const IS_REQUESTED_BASE_URL              =   BASE_URL + "isRequested";
export const ADD_REQUEST_BASE_URL               =     BASE_URL + "requestResource";
export const GET_PROJECT_BY_MANAGER_ID_BASE_URL =     BASE_URL + "projectCards/";

