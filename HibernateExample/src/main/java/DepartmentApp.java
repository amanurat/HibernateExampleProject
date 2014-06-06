import com.hibernate.annotation.entity.Department;
import com.hibernate.annotation.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Date;
import java.util.List;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 1:55 PM
 */
public class DepartmentApp {

    public static void main(String args[]) {

        Configuration configure = new Configuration().configure();
            configure.addAnnotatedClass(Department.class);
            configure.addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory = configure.buildSessionFactory();

//        new SchemaExport(configure).create(true, true);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        transaction.begin();


        Department department = (Department) session.get(Department.class, 1);

        List<Employee> employeeList = department.getEmployeeList();

        System.out.println("Total employee of id[1] >> "+employeeList.size());

        for(Employee employee : employeeList) {
            System.out.println("Employee ID: "+ employee.getId() + " Employee First Name : "+ employee.getFirstName());
        }

//        session.save(new Department("IT"));
//        session.save(new Department("HR"));


        transaction.commit();
        session.close();
    }
}
