package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

public class EmployeeClass implements Employee {

    // Class member variables
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    // Constructor with parameters
    public EmployeeClass(UUID uuid, String firstName, String lastName, String fullName, Integer salary, Integer age, String jobTitle, String email, Instant contractHireDate, Instant contractTerminationDate) {
        // If a UUID was not detected from the JSON object, throw an exception.
        if (uuid == null) {
            throw new NullPointerException("The UUID cannot be null.\n");
        }
        // Set all member variable values
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
        this.contractTerminationDate = contractTerminationDate;
    }

    /* METHOD DEFINITIONS */

    @Override
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Set by either the Service or Data layer.
     * @param uuid required non-null
     */
    @Override
    public void setUuid(UUID uuid) {
        // If a UUID was not detected from the JSON object, throw an exception.
        if (uuid == null) {
            throw new NullPointerException("The UUID cannot be null.");
        }
        // Otherwise, set the UUID value
        this.uuid = uuid;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String name) {
        firstName = name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String name) {
        lastName = name;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String name) {
        fullName = name;
    }

    @Override
    public Integer getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Integer salary) {
        // Check to ensure the salary value is not negative.
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        // Check to ensure the age value is not under 18.
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older.");
        }
        this.age = age;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    @Override
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        // Check to ensure the email is a proper string containing @ and . to indicate a proper domain follows the email username
        if (!email.contains("@") && !email.contains(".")) {
            throw new IllegalArgumentException("Email field does not contain proper formatting. Check for '@' and '.' symbols.");
        }
        this.email = email;
    }

    @Override
    public Instant getContractHireDate() {
        return contractHireDate;
    }

    @Override
    public void setContractHireDate(Instant date) {
        contractHireDate = date;
    }

    /**
     * Nullable.
     * @return null, if Employee has not been terminated.
     */
    @Override
    public Instant getContractTerminationDate() {
        // Ternary operator to determine whether to return null or a set termination date
        // The "Instant" type is immutable, so defensive copying strategies like the one below are not required. However, it is good practice since
        // other data types (such as "Date") could be modified by outside classes if returned directly rather than an copied instance of it.
        return (contractTerminationDate == null) ? null : Instant.from(contractTerminationDate);
    }

    @Override
    public void setContractTerminationDate(Instant date) {
        contractTerminationDate = date;
    }

}