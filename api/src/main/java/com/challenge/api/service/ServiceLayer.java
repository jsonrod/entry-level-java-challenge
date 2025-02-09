package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeClass;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import java.util.*;

@Service
public class ServiceLayer {

    // Mock database declaration (ArrayList data structure => initially empty)
    private final List<Employee> employees;

    // Employees database instantiation
    public ServiceLayer() {
        employees = new ArrayList<>();
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        // Temporary placeholder for employee (NULL initially)
        Employee employee = null;
        // Search for employee via UUID
        // Mimicking the following SQL command: SELECT * FROM Employees WHERE Employees.UUID = UUID;
        for (Employee emp : employees) {
            // If the UUID values match, stop the search and break out of iteration.
            if (emp.getUuid().equals(uuid)) {
                employee = emp;
                break;
            }
        }

        // If there were no employees found via the specified GET /api/v1/employee/uuid value, throw a 404 NOT FOUND exception error 
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee was found with UUID " + uuid + ".");
        }

        // Otherwise, retrieve the employee
        return employee;
    }

    public Employee createEmployee(@RequestBody Employee requestBody) {

        // Get the UUID value specified in the POST request
        UUID retrievedUuid = requestBody.getUuid();
        // If no UUID value was specified, randomly generate it; otherwise, set the new UUID value to the received one.
        UUID newUuid = (retrievedUuid == null) ? UUID.randomUUID() : retrievedUuid;
    
        // Create a new employee instance with the self-defined constructor using parameters (alternatively could've used each method)
        Employee newEmployee = new EmployeeClass(newUuid,
                                                 requestBody.getFirstName(),
                                                 requestBody.getLastName(),
                                                 requestBody.getFullName(),
                                                 requestBody.getSalary(),
                                                 requestBody.getAge(),
                                                 requestBody.getJobTitle(),
                                                 requestBody.getEmail(),
                                                 requestBody.getContractHireDate(),
                                                 requestBody.getContractTerminationDate());

        // Add the new employee into the database
        // Mimicking the following SQL command: INSERT INTO Employees (uuid, firstname, lastname, ...) VALUES (requestBody.getFirstName(), ....);
        employees.add(newEmployee);

        // Retrieve the newly created employee from POST
        return newEmployee;

    }

}