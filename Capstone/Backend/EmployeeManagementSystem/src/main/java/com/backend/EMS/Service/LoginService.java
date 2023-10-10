package com.backend.EMS.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EMS.Constants.SuccessConstants;
import com.backend.EMS.DTO.LoginInDto;
import com.backend.EMS.DTO.LoginOutDto;
import com.backend.EMS.Model.Employee;
import com.backend.EMS.Repository.EmployeeRepository;




/**
 * Service class for handling login-related functionality.
 */
@Service
public class LoginService {
    /**
     * The repository for administrator data.
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Performs the login operation.
     * @param loginInDto The DTO containing login information.
     * @return The DTO representing the logged-in user.
     */
    public final LoginOutDto login(final LoginInDto loginInDto) {
        Employee employee = employeeRepository
                .findByEmail(loginInDto.getEmail());

        LoginOutDto loginResponse = new LoginOutDto();
        loginResponse.setMessage(SuccessConstants.LOGINSUCCESS);
        loginResponse.setRole(employee.getRole());
        loginResponse.setId(employee.getId());
        loginResponse.setName(employee.getName());
        return loginResponse;
    }


}
