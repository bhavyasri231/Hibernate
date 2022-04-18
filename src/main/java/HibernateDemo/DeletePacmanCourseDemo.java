package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DeletePacmanCourseDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

            int courseId = 10;
            Course tempCourse = (Course) session.get(Course.class,courseId);

            System.out.println("Deleting Course: "+tempCourse);

            session.delete(tempCourse);
            
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            session.close();
            factory.close();
        }
    }
}
