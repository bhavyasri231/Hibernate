package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Course;
import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //create a student object
            Instructor tempInstructor = new Instructor("Susan","Public","susan@luv2code.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.youtube.com","Video Games");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);


            //start a transaction
            session.beginTransaction();

            //save the student object
            //it also save the instructor details due to cascade.all
            System.out.println("Saving Instructor: "+tempInstructor);
            session.save(tempInstructor);

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
