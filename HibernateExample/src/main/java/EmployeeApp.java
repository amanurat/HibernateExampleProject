import com.hibernate.annotation.entity.Department;
import com.hibernate.annotation.entity.Employee;
import com.hibernate.annotation.util.PrePersistIntercepter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * User: assanai.manurat
 * Date: 4/23/2014
 * Time: 1:55 PM
 */
public class EmployeeApp {

    private static SessionFactory sessionFactory;

    public EmployeeApp() {
        Configuration configure = new Configuration().configure();
        sessionFactory =
                configure.addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .setInterceptor(new PrePersistIntercepter())
                .buildSessionFactory();

        new SchemaExport(configure).create(true, true);
    }

    public static void main(String args[]) throws Exception {
        EmployeeApp app = new EmployeeApp();
        app.insert();
//        app.update(1);
//        app.delete(1);
//        app.getByPk();
//        app.queryWithCriteria();
//        app.queryByHQL();
//        app.queryHQLByFirstName();
//        app.queryByNativeSQL();
//        app.queryWithCriteriaByNameAndAddress("Damian", "Bangkok");

        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstName", "Damian");
        map.put("address", "Bangkok");
        map.put("lastName", "Sutton");
        app.queryWithCriteriaByMap(map);*/

//        app.queryWithNameQuery();
//        app.queryWithNameQueryByFirstName("Damian");

    }

    private void queryWithNameQueryByFirstName(String firstName) {
        Session session = sessionFactory.openSession();

        List<Employee> employeeList = session.getNamedQuery("employee.findByFirstName")
                .setParameter("FIRST_NAME", firstName)
                .list();

        display(employeeList);
    }

    private void queryWithNameQuery() {
        Session session = sessionFactory.openSession();

        List<Employee> employeeList = session.getNamedQuery("employee.findAll")
                .list();

        display(employeeList);

    }

    private void queryByNativeSQL() {
        Session session = sessionFactory.openSession();

/*        String sql = "select * from EMPLOYEE where FIRST_NAME = :firstName ";

        List<Employee> employeeList = session.createSQLQuery(sql)
                .addEntity(Employee.class)
                .setParameter("firstName", "Damian")
                .list();
        System.out.println("employList : "+ employeeList);
        display(employeeList);*/


    }

    private void queryWithCriteriaByMap(Map<String,Object> criteriaMap) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Employee.class);

        for (Map.Entry<String, Object> entry : criteriaMap.entrySet()) {
                String key = (String) entry.getKey();
                criteria.add(Restrictions.eq(key, entry.getValue()));
        }

        List<Employee> restrictionsList = criteria.list();
        display(restrictionsList);
    }

    private void delete(Integer id) throws Exception{
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Employee employee = (Employee)session.get(Employee.class, id);
        session.delete(employee);

        transaction.commit();
        session.close();
    }

    public void insert() throws Exception {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Employee john = new Employee("John", "Sutton", 200000, 20, "08912345678", 0, "Director", new Date(),"john@gmail.com", "USA");
        john.setDepartment(new Department("IT"));

        Employee damian = new Employee("Damian", "Sutton", 100000, 20, "08187654321", 0, "Manager", new Date(),"damian@gmail.com", "Bangkok");
        damian.setDepartment(new Department("HR"));

        session.save(john);

        session.save(damian);

        transaction.commit();

        session.close();
    }

    /**
     * Find Employee By Primary Key
     * @throws Exception
     */
    public void getByPk() throws Exception {
        Session session = sessionFactory.openSession();
        Employee employee = (Employee)session.get(Employee.class, 1);
        System.out.println("Employee first name is : "+ employee.getFirstName());
        System.out.println("Employee last name is : "+ employee.getLastName());

        String departmentName = employee.getDepartment().getDepartmentName();
        System.out.println("departmentName : "+ departmentName);
        session.close();
    }

    public void queryByHQL() throws Exception {
        Session session = sessionFactory.openSession();
        List<Employee> employeeList = session.createQuery("from Employee").list();
        display(employeeList);
        session.close();
    }
    public void queryHQLByFirstName() throws Exception {
        Session session = sessionFactory.openSession();
        List<Employee> employeeList = session.createQuery("from Employee e where e.firstName = 'John'").list();
        display(employeeList);
        session.close();
    }

    /**
     * Query By Criteria all list
     * @throws Exception
     */
    public void queryWithCriteria() throws Exception {
        Session session = sessionFactory.openSession();
        List<Employee> resultList = session.createCriteria(Employee.class).list();
        display(resultList);
        session.close();
    }

    private void display(List<Employee> resultList) {
        for (Employee employeeModel : resultList) {
            Integer id = employeeModel.getId();
            String firstName = employeeModel.getFirstName();
            String lastName = employeeModel.getLastName();
            System.out.println("ID: "+ id + ", First Name : "+ firstName +", Last Name : "+lastName);
        }
    }

    public void queryWithCriteriaByNameAndAddress(String firstName, String address) throws Exception {
        System.out.println("> queryWithCriteriaByNameAndAddress ");
        Session session = sessionFactory.openSession();


        List<Employee> restrictionsList = session.createCriteria(Employee.class)
                .add(Restrictions.eq("firstName", firstName))
                .add(Restrictions.eq("address", address))
                .list();
       display(restrictionsList);
        session.close();
    }

    /**
     * Update Employee
     * @throws Exception
     */
    public void update(Integer id) throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        //Update Employee
        Employee employee = (Employee) session.get(Employee.class, id);
        employee.setFirstName("John has update");
        session.update(employee);
        transaction.commit();
        session.close();
    }


}
