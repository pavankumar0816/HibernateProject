package HCQLDemo;

import java.util.Arrays;
import java.util.List; 

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOperations 
{
   public static void main(String[] args)
   {
	   HCQLOperations operations = new HCQLOperations();
	   //operations.addstudent(); 
	   //operations.restrictionsdemo();
	   //operations.orderdemo();
	   //operations.aggregatefunction();
	   operations.hqldemo();
   }
   // add student by using Persistent Object(PO)
   public void addstudent()
   {
	   Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   
	   Transaction t = session.beginTransaction();
	   
	   Student s = new Student();
	   s.setId(104);
	   s.setName("MSWD");
	   s.setGender("FEMALE");
	   s.setAge(50.5);
	   s.setDepartment("ECE");
	   s.setEmail("mswd@gmail.com");
	   s.setContact("9767878774");
	   
	   session.persist(s);
	   t.commit();
	   System.out.println("...Student Added Successfully...");
	   
	   session.close();
       sf.close();	   
   }
   public void restrictionsdemo()
   {
     Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
    
        /*CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        // from Student; [Complete Object]
        Root<Student> root = cq.from(Student.class);
        System.out.println("****All Student Objects****");
        List<Student> students =  session.createQuery(cq).getResultList();
        System.out.println("Students Count="+students.size());
        for(Student s : students)
        {
          // use getter methods to print every property in Student object (s)
          System.out.println(s.toString());
        }*/
      
        
        
      /*CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Student> cq = cb.createQuery(Student.class);
      // from Student; [Complete Object]
      Root<Student> root = cq.from(Student.class);
     cq.select(root).where(cb.equal(root.get("gender"), "FEMALE"));
      System.out.println("****Student Objects with equal criteria****");
      List<Student> students =  session.createQuery(cq).getResultList();
      System.out.println("Students Count="+students.size());
      for(Student s : students)
      {
        // use getter methods to print every property in Student object (s)
        System.out.println(s.toString());
      }
        
        session.close();
        sf.close();
        
   }*/
        
        
        
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        // from Student; [Complete Object]
        Root<Student> root = cq.from(Student.class);
        
         //cq.select(root).where(cb.lessThan(root.get("age"), 30));
         //cq.select(root).where(cb.greaterThan(root.get("age"), 40));
         //cq.select(root).where(cb.le(root.get("age"), 50));  // less than or equal to 50
         //cq.select(root).where(cb.ge(root.get("age"), 40)); // greater than or equal to 40
          //cq.select(root).where(cb.notEqual(root.get("department"), "CSE"));  // not equal to
          //cq.select(root).where(cb.like(root.get("department"), "C%")); // starts with C
          //cq.select(root).where(cb.like(root.get("department"), "%E")); // ends with E
           //cq.select(root).where(cb.like(root.get("email"), "%gmail%"));  // gmail as sub string
           //cq.select(root).where(cb.like(root.get("name"), "K__"));  // start with k and length = 3
           
            //cq.select(root).where(cb.between(root.get("age"), 20, 50)); // between 20 and 50
           // not with any existing criteria
          //cq.select(root).where(cb.not(cb.equal(root.get("department"), "ECE")));
        
        List<String> depts = Arrays.asList("CSE","ECE","ME");
        cq.select(root).where(root.get("department").in(depts)); // set of values 
           
        
        
        System.out.println("****Student Objects with different comparsion criteria****");
        List<Student> students =  session.createQuery(cq).getResultList();
        System.out.println("Students Count="+students.size());
        for(Student s : students)
        {
          // use getter methods to print every property in Student object (s)
          System.out.println(s.toString());
        }
          
          session.close();
          sf.close();
          
     }
   
   public void orderdemo()  // ascending / descending
   {
	   Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   
	   CriteriaBuilder cb = session.getCriteriaBuilder();
       CriteriaQuery<Student> cq = cb.createQuery(Student.class);
       Root<Student> root = cq.from(Student.class);
       
       // ascending order based on age
       //cq.orderBy(cb.asc(root.get("age")));
       
       // descending order based on name
       cq.orderBy(cb.desc(root.get("name")));
       
       System.out.println("****Order By Demo****");
       List<Student> students =  session.createQuery(cq).getResultList();
       System.out.println("Students Count="+students.size());
       for(Student s : students)
       {
         System.out.println(s.toString());
       }
       session.close();
       sf.close();
   }
   
   public void aggregatefunction()
   {
	   Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   
	   CriteriaBuilder cb1 = session.getCriteriaBuilder();
       CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class);
       Root<Student> root1 = cq1.from(Student.class);
       cq1.select(cb1.count(root1.get("name")));
       long totalcount = session.createQuery(cq1).getSingleResult();       
       System.out.println("Total Students Count: "+totalcount);
       
       CriteriaBuilder cb2 = session.getCriteriaBuilder();
       CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class);
       Root<Student> root2 = cq2.from(Student.class);
       cq2.select(cb2.sum(root2.get("age")));
       double totalage = session.createQuery(cq2).getSingleResult();       
       System.out.println("Total Students Age: "+totalage);
       
       CriteriaBuilder cb3 = session.getCriteriaBuilder();
       CriteriaQuery<Double> cq3 = cb3.createQuery(Double.class);
       Root<Student> root3 = cq3.from(Student.class);
       cq3.select(cb3.avg(root3.get("age")));
       double average = session.createQuery(cq3).getSingleResult();       
       System.out.println("Average Students Age: "+average);
       
       CriteriaBuilder cb4 = session.getCriteriaBuilder();
       CriteriaQuery<Integer> cq4 = cb4.createQuery(Integer.class);
       Root<Student> root4 = cq4.from(Student.class);
       cq4.select(cb4.min(root4.get("id")));
       int minsid = session.createQuery(cq4).getSingleResult();       
       System.out.println("Minimum Students ID: "+minsid);
       
       CriteriaBuilder cb5 = session.getCriteriaBuilder();
       CriteriaQuery<Integer> cq5 = cb5.createQuery(Integer.class);
       Root<Student> root5 = cq5.from(Student.class);
       cq5.select(cb5.max(root5.get("id")));
       int maxsid = session.createQuery(cq5).getSingleResult();       
       System.out.println("Maximum Students ID: "+maxsid);
       
       CriteriaBuilder cb6 = session.getCriteriaBuilder();
       CriteriaQuery<Long> cq6 = cb6.createQuery(Long.class);
       Root<Student> root6 = cq6.from(Student.class);
       cq6.select(cb6.countDistinct(root6.get("department")));
       long distinctcount = session.createQuery(cq6).getSingleResult();       
       System.out.println("Distinct Department Count: "+distinctcount);
       
       session.close();
       sf.close();
   }
   
   // display students of CSE department based on age in ascending order
   public void hqldemo()
   {
	   Configuration configuration = new Configuration();
	   configuration.configure("hibernate.cfg.xml");
	   
	   SessionFactory sf = configuration.buildSessionFactory();
	   Session session = sf.openSession();
	   
	   CriteriaBuilder cb = session.getCriteriaBuilder();
       CriteriaQuery<Student> cq = cb.createQuery(Student.class);
       Root<Student> root = cq.from(Student.class);
       
       cq.select(root).where(cb.equal(root.get("department"), "CSE"));
       cq.orderBy(cb.asc(root.get("age")));
       
       List<Student> students =  session.createQuery(cq).getResultList();
       System.out.println("Students Count="+students.size());
       for(Student s : students)
       {
         System.out.println(s.toString());
       }
       session.close();
       sf.close();
   }
}
