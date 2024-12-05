package CRUDOperations;

import java.util.Scanner;     
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Controller 
{
    private Scanner sc;
    private SessionFactory sf;

    public Controller() 
    {
        sc = new Scanner(System.in);
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sf = configuration.buildSessionFactory();
    }

    public static void main(String[] args) 
    {
        Controller controller = new Controller();
        boolean loop = true;
        while (loop) {
            System.out.println("1.addfaculty\t 2.displayfacultybyid\t 3.updatefaculty\t 4.deletefaculty\t 5.Exit\n");
            System.out.println("Select Choice :- ");
            int opt = controller.sc.nextInt();
            switch (opt) 
            {
                case 1:
                    controller.addfaculty();
                    break;
                case 2:
                    controller.displayfacultybyid();
                    break;
                case 3:
                    controller.updatefaculty();
                    break;
                case 4:
                    controller.deletefaculty();
                    break;
                default:
                    loop = false;
            }
        }
        controller.sc.close();
        controller.sf.close();
    }

    public void addfaculty() 
    {
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        Faculty faculty = new Faculty();

        System.out.println("Enter Faculty ID: ");
        faculty.setId(sc.nextInt());
        sc.nextLine(); 

        System.out.println("Enter Faculty Name: ");
        faculty.setName(sc.nextLine());

        System.out.println("Enter Faculty Gender: ");
        faculty.setGender(sc.nextLine());

        System.out.println("Enter Faculty Department: ");
        faculty.setDepartment(sc.nextLine());

        System.out.println("Enter Faculty Salary: ");
        faculty.setSalary(sc.nextLine());

        System.out.println("Enter Faculty Contact No: ");
        faculty.setContactno(sc.nextLine());

        session.persist(faculty);
        t.commit();

        System.out.println("Faculty Added Successfully");

        session.close();
    }

    // display or find faculty based on ID Column
    public void displayfacultybyid() 
    {
    	Session session = sf.openSession();
    	
    	// There is no need to create transaction object because there is no DML operation
    	System.out.println("Enter Faculty ID to View");
    	int fid = sc.nextInt();
    	
    	Faculty faculty= session.find(Faculty.class, fid);
    	if(faculty!=null)
    	{
    		System.out.println("Faculty ID:"+faculty.getId());
    		System.out.println("Faculty Name:"+faculty.getName());
    		System.out.println("Faculty Gender:"+faculty.getGender());
    		System.out.println("Faculty Department:"+faculty.getDepartment());
    		System.out.println("Faculty Salary:"+faculty.getSalary());
    		System.out.println("Faculty Contact No:"+faculty.getContactno());
    	}
    	else
    	{
    		System.out.println("Faculty ID Not Found");
    	}
    	session.close();
    }

    public void updatefaculty() 
    {
    	Session session = sf.openSession();
    	
    	Transaction t = session.beginTransaction();
    	
    	System.out.println("Enter Faculty ID to Update :");
    	int fid = sc.nextInt();
    	
    	Faculty faculty= session.find(Faculty.class, fid);
        if(faculty!=null)
        {
        	System.out.println("Enter Faculty Name:");
            String fname = sc.next();
            System.out.println("Enter Faculty Salary:");
            String fsalary =sc.next();
            System.out.println("Enter Faculty Contact:");
            String fcontact= sc.next();
            
            faculty.setName(fname);
            faculty.setSalary(fsalary);
            faculty.setContactno(fcontact);
            
            t.commit();
            System.out.println("Faculty Updated Successfully");
        }
        else
        {
        	System.out.println("Faculty ID Not Found");
        }
        session.close();
    }

    public void deletefaculty() 
    {
         Session session = sf.openSession();
    	
    	Transaction t = session.beginTransaction();
    	
    	System.out.println("Enter Faculty ID to Delete :");
    	int fid = sc.nextInt();
    	
    	Faculty faculty= session.find(Faculty.class, fid);
    	if(faculty!=null)
    	{
    		session.remove(faculty);
    		t.commit();
    		System.out.println("Faculty Deleted Successfully");
    	}
    	else
    	{
    		System.out.println("Faculty ID Not Found");
    	}
    }
}
