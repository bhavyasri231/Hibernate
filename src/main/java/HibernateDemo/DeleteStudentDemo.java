package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().addAnnotatedClass(Student.class)
                .configure("hibernate.cfg.xml").buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {

            int studentId=1;

            //get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id:primary key
            System.out.println("Getting student with id: "+studentId);

            Student myStudent = (Student) session.get(Student.class,studentId);

            //delete the student
          //  System.out.println("Deleting student: "+myStudent);
          //  session.delete(myStudent);

            //delete student id=2
            System.out.println("deleting student id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit the transaction
           session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
