package com.hibernate.annotation.entity;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 12:34 AM
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/xxx";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {

          insertStudent();

//        listAllStudent();

        System.out.println("Goodbye!");
    }//end main

    private static void insertStudent() {

    }

    private static void listAllStudent() {
        Connection conn = null;
        Statement stmt = null;

        List<StudentEntity> studentEntitiesList = new ArrayList<StudentEntity>();

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT STUDENT_ID, STUDENT_NAME, EMAIL FROM STUDENT";
            ResultSet rs = stmt.executeQuery(sql);

            StudentEntity studentEntity;
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                Long studentId = rs.getLong("STUDENT_ID");
                String studentName = rs.getString("STUDENT_NAME");
                String email = rs.getString("EMAIL");

                studentEntity = new StudentEntity();
                studentEntity.setId(studentId);
                studentEntity.setName(studentName);
                studentEntity.setEmail(email);
                studentEntitiesList.add(studentEntity);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        System.out.println("Total student : "+ studentEntitiesList.size());
    }


}//end FirstExample