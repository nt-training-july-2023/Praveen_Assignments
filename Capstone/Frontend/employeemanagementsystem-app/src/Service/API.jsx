export const BASE_URL   =    "http://localhost:8080/api/"

//Login

export const LOGIN_BASE_URL     =   BASE_URL + "login";

// Admin

export const REGISTER_BASE_URL                  =     BASE_URL + "admin-registration";
export const ADD_EMPLOYEE_BASE_URL              =     BASE_URL + "admin/add-employee";
export const GET_ALL_EMPLOYEE_BASE_URL          =     BASE_URL + "all/Employee";
export const GET_ALL_MANAGERS_BASE_URL          =     BASE_URL + "all/Manager";
export const ASSIGN_PROJECT_BASE_URL            =     BASE_URL + "admin/assign-project/";
export const UNASSIGN_PROJECT_BASE_URL          =     BASE_URL + "admin/employee/unassign/project/";
export const ADD_PROJECT_BASE_URL               =     BASE_URL + "admin/add-project";
export const GET_PROJECTS_BASE_URL              =     BASE_URL + "projects";
// export const GET_PROJECT_BY_MANAGER_ID_BASE_URL =     BASE_URL + "projectCards/";
export const GET_ALL_REQUEST_BASE_URL           =   BASE_URL + "admin/requested-resources";
export const ACCEPT_REQUEST_BASE_URL            =   BASE_URL + "admin/requested-resources/accept/";
export const REJECT_REQUEST_BASE_URL            =   BASE_URL + "admin/requested-resources/reject/";


//Employee

export const MYPROFILE_BASE_URL             =     BASE_URL + "employee/";
export const GET_ALL_BASE_URL               =     BASE_URL + "managers-employees";
export const UPDATE_SKILLS_BASE_URL         =     BASE_URL + "employee/update-skill/";

//Managers
export const FILTERED_EMPLOYEES_BASE_URL    =     BASE_URL + "manager/filtered-employees";
export const IS_REQUESTED_BASE_URL              =   BASE_URL + "manager/employee/is-requested";
export const ADD_REQUEST_BASE_URL               =     BASE_URL + "manager/request-resource";
export const GET_PROJECT_BY_MANAGER_ID_BASE_URL =     BASE_URL + "projects/";

