package com.backend.EMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for starting the Employee Management System apllication.
 */
@SpringBootApplication
public class EmployeeManagementSystemApplication {
    /**
     *
     * @param args args.
     */
    public final void run(final String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    /**
     * The main method that launches the Employee Managment System Application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(final String[] args) {
        new EmployeeManagementSystemApplication().run(args);
    }

}
