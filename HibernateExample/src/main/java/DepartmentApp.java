import com.hibernate.annotation.entity.Department;
import com.hibernate.annotation.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.List;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 1:55 PM
 */
public class DepartmentApp {


    private static SessionFactory sessionFactory;

    public DepartmentApp() {

        sessionFactory = getSessionFactory();

    }

    public static void main(String args[]) {
        DepartmentApp departmentApp = new DepartmentApp();
//        departmentApp.insertData();
        departmentApp.retriedData();
        departmentApp.retriedDataList();

    }

    private void retriedData() {
        System.out.println("*** RetriedData ***");
        Session session = sessionFactory.openSession();
        Department department = (Department) session.get(Department.class, 1);

        print(department);
    }

    private void retriedDataList() {
        System.out.println("*** RetriedDataList ***");
        Session session = sessionFactory.openSession();
        List<Department> departmentList = session.createQuery("from Department").list();

        print(departmentList);

    }

    private void insertData() {
        System.out.println("*** insertData ***");
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        transaction.begin();

        session.save(new Department("IT"));
        session.save(new Department("HR"));

        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configure = new Configuration().configure();
        configure.addAnnotatedClass(Department.class);
        configure.addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory = configure.buildSessionFactory();

//        new SchemaExport(configure).create(true, true);

        return sessionFactory;
    }


    private void print(List<Department> departmentList) {
        for (Department department : departmentList) {
            System.out.println("Department Name : " + department.getDepartmentName());
        }
    }

    private void print(Department department) {
        System.out.println("department : " + department);
    }
}
