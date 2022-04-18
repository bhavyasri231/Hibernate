package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Course;
import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import HibernateDemo.HibernateDemoEntity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class CreateCourseAndReviewsDemo {

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

            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            tempCourse.addReview(new Review("Great course.....Loved it" ));
            tempCourse.addReview(new Review("Cool course , job well done" ));
            tempCourse.addReview(new Review("What a dumb course, you are an idiot!" ));

            //reviews are also saved because of cascade.all
            System.out.println("Saving the course...");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);

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
