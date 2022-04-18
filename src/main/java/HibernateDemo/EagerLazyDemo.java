package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Course;
import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

            int theId=1;
            Instructor tempInstructor = (Instructor) session.get(Instructor.class,theId);


            //get courses for instructor
            System.out.println("luv2code: Instructor: "+tempInstructor );

            System.out.println("luv2code: Courses: "+tempInstructor.getCourses());


            //commit transaction
            session.getTransaction().commit();

            //session.close();

            System.out.println("\nluv2code: the session is now closed\n");

            //get courses for instructor
            System.out.println("luv2code: Courses: "+tempInstructor.getCourses());

            System.out.println("luv2code Done!");

        }
        finally {
            session.close();
            factory.close();
         }
    }
}
