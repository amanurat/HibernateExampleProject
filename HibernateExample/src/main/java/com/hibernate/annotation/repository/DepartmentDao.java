package com.hibernate.annotation.repository;

import com.hibernate.annotation.entity.Department;
import com.hibernate.annotation.repository.base.GenericHibernateDAO;

import java.util.List;

/**
 * Created by amanurat on 10/25/14 AD.
 */
public class DepartmentDao extends GenericHibernateDAO<Department, Integer> {

    public DepartmentDao() {
        //Don't need call parent constructor because using parameter generic type
//        super(Department.class);
    }

    @Override
    public List<Department> findByExample(Department exampleInstance) {
        return null;
    }
}
