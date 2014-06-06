package com.hibernate.annotation.entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 2:55 PM
 */
@Entity

public class Department {

    @Id
    @GeneratedValue
    @Column(name = "DEPARTMENT_ID")
    private Integer id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList;

    public Department() {}

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
