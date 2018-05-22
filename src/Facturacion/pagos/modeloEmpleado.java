package Facturacion.pagos;


import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class modeloEmpleado /*implements EmployeeDao*/ {

 //   private static Session session;

    public ObservableList<empleado> getEmployees() {
        ObservableList<empleado> list = FXCollections.observableArrayList();

        /* ObservableList<empleado> list = FXCollections.observableArrayList();
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<empleado> employees = session.createQuery("from Employee").list();
        session.beginTransaction().commit();
        employees.stream().forEach(list::add);*/

        return list;
    }


   public empleado getEmployee(long id) {

        /*  session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit(); */
empleado employee=null;
        return employee;
    }
    

    public String getEmployeeType(String username){
    
        /* session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();*/
       
        return "admin";//employee.getType();
    }


    public void saveEmployee(empleado employee) {

        /*  session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();*/
    }


    public void updateEmployee(empleado employee) {
        /*
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setUserName(employee.getUserName());
        e.setPassword(employee.getPassword());
        e.setPhone(employee.getPhone());
        e.setAddress(employee.getAddress());
        session.getTransaction().commit();*/
    }


    public void deleteEmployee(empleado employee) {
        
     /*  session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        session.delete(e);
        session.getTransaction().commit();*/
    }
    

    public boolean checkUser(String username) {

        /*  session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();*/

        return true; //employee != null;
    }
    

    public boolean checkPassword(String username, String password) {

        /*    session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        System.out.println("Pass in Employee Model: "+employee.getPassword()+" Ingres: "+password);*/
        return true; //employee.getPassword().equals(password);
    }
}
