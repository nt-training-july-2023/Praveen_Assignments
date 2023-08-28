package com.backend.EMS.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an admin.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Admin")
public class Admin {

    /**
     * The unique ID of the admin.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    /**
     * The name of the admin.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email of the admin.
     */
    @Column(nullable = false)
    private String email;

    /**
     * The employee ID of the admin.
     */
    @Column(nullable = false)
    private String emp_Id;

    /**
     * The date of birth of the admin.
     */
    @Column(nullable = false)
    private String DOB;

    /**
     * The date of joining of the admin.
     */
    @Column(nullable = false)
    private String DOJ;

    /**
     * The location of the admin.
     */
    @Column(nullable = false)
    private String location;

    /**
     * The designation of the admin.
     */
    @Column(nullable = false)
    private String designation;

    /**
     * The contact number of the admin.
     */
    @Column(nullable = false)
    private Long contact_no;

    /**
     * The password of the admin.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The confirmation password for validation.
     */
    @Column(nullable = false)
    private String confirmPassword;

    /**
     * Get the ID of the admin.
     *
     * @return The admin ID.
     */
    public long getId() {
        return Id;
    }

    /**
     * Set the ID of the admin.
     *
     * @param id The ID to set.
     */
    public void setId(long id) {
        Id = id;
    }

    /**
     * Get the name of the admin.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the admin.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the email of the admin.
     *
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the admin.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the employee ID of the admin.
     *
     * @return The employee ID.
     */
    public String getEmp_Id() {
        return emp_Id;
    }

    /**
     * Set the employee ID of the admin.
     *
     * @param emp_Id The employee ID to set.
     */
    public void setEmp_Id(String emp_Id) {
        this.emp_Id = emp_Id;
    }

    /**
     * Get the date of birth of the admin.
     *
     * @return The date of birth.
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * Set the date of birth of the admin.
     *
     * @param DOB The date of birth to set.
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * Get the date of joining of the admin.
     *
     * @return The date of joining.
     */
    public String getDOJ() {
        return DOJ;
    }

    /**
     * Set the date of joining of the admin.
     *
     * @param DOJ The date of joining to set.
     */
    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    /**
     * Get the location of the admin.
     *
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location of the admin.
     *
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the designation of the admin.
     *
     * @return The designation.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Set the designation of the admin.
     *
     * @param designation The designation to set.
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Get the contact number of the admin.
     *
     * @return The contact number.
     */
    public Long getContact_no() {
        return contact_no;
    }

    /**
     * Set the contact number of the admin.
     *
     * @param contact_no The contact number to set.
     */
    public void setContact_no(Long contact_no) {
        this.contact_no = contact_no;
    }

    /**
     * Get the password of the admin.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the admin.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the confirmation password for validation.
     *
     * @return The confirmation password.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Set the confirmation password for validation.
     *
     * @param confirmPassword The confirmation password to set.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

