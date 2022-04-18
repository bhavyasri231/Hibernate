package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Course;
import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import HibernateDemo.HibernateDemoEntity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class).buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

            int theId = 10;
            Course tempCourse = (Course) session.get(Course.class,theId);

            System.out.println(tempCourse);

            System.out.println(tempCourse.getReviews());

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
