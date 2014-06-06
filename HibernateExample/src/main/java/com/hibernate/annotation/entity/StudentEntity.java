package com.hibernate.annotation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: assanai.manurat
 * Date: 4/7/2014
 * Time: 3:32 PM
 */
public class StudentEntity {

    private Long id;

    private String name;

    private String email;

    public StudentEntity() {
    }
    
    public StudentEntity(String name) {
        this.name = name;
    }

    public StudentEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("email", email)
                .toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

