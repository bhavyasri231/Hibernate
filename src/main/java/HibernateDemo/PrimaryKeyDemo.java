package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        SessionFactory factory = new AnnotationConfiguration().addAnnotatedClass(Student.class)
                .configure("hibernate.cgf.xml").buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //create 3 student object
            System.out.println("Creating 3 student objects....");
            Student tempStudent1 = new Student("John","Doe","john@gmail.com");
            Student tempStudent2 = new Student("Mary","Public","mary@gmail.com");
            Student tempStudent3 = new Student("Bonita","Apple-bum","bonita@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //commit transaction

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
