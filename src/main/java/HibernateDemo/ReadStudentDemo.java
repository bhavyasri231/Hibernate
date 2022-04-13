package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().addAnnotatedClass(Student.class)
                .configure("hibernate.cfg.xml").buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //create a student object
            System.out.println("Creating new student object....");
            Student tempStudent = new Student("Daffy","Duck","daffy@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction

            session.getTransaction().commit();

            System.out.println("Saving student, Generated id: "+tempStudent.getId());

            //get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id:primary key
            System.out.println("Getting student with id: "+tempStudent.getId());

            Student myStudent = (Student) session.get(Student.class,tempStudent.getId());

            System.out.println("Get complete: "+myStudent );

            //commit the transaction
            session.getTransaction().commit();



            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
