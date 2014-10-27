package com.hibernate.annotation;

import com.hibernate.annotation.entity.Department;
import com.hibernate.annotation.repository.DepartmentDao;

import java.util.List;

/**
 * Created by amanurat on 10/25/14 AD.
 */
public class RepositoryApp {

    public static void main(String[] args) {


        DepartmentDao departmentDao = new DepartmentDao();

        List<Department> departmentList = departmentDao.findAll();
        System.out.println("departmentList : " + departmentList);


        Department department = departmentDao.findById(1);
        System.out.println("department : " + department);

    }

}
