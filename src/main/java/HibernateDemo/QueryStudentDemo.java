package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().addAnnotatedClass(Student.class)
                .configure("hibernate.cfg.xml").buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

            //query student
            List<Student> theStudents = session.createQuery("from Student").list();

            //display students
            displayStudents(theStudents);

            //query students: lastname='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();

            //display the students
            System.out.println("\n\nStudents who have last name as Doe");
            displayStudents(theStudents);

            //query students: lastname='Doe' or firstName = 'Daffy'
            theStudents = session.createQuery("from Student s where"
                    +" s.lastName='Doe' OR s.firstName='Daffy'").list();

            //display the students
            System.out.println("\n\nStudents who have last name as Doe or firstname as Daffy");
            displayStudents(theStudents);

            //query students where email Like '%gmail.com'
            theStudents = session.createQuery("from Student s where" +
                    " s.email LIKE '%gmail.com'").list();

            //display the students
                    System.out.println("\n\nStudents who have email ends with gmail.com");
            displayStudents(theStudents);

            
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");


        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents)
        {
            System.out.println(tempStudent);
        }
    }
}
