package com.hibernate.annotation.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 1:45 PM
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQuery(name = "employee.findAll", query ="from Employee")
@NamedQueries({
        @NamedQuery(name = "employee.findBydId", query = "from Employee where id = :ID"),
        @NamedQuery(name = "employee.findByFirstName", query = "from Employee where firstName = :FIRST_NAME")})
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SALARY")
    private Integer salary;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "SEX")
    private Integer sex;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



    public Employee() {

    }

    public Employee(String firstName, String lastName, Integer salary, Integer age, String phoneNumber, Integer sex, String position, Date birthDate, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.position = position;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {

        this.updateDateTime = updateDateTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex=" + sex +
                ", position='" + position + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", department=" + department +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }
}
