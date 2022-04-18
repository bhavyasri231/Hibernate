package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Course;
import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            //option2 hibernate query with HQL
            //get the instructor form db
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    + "where i.id=:theInstructorId",
                            Instructor.class);

            //set parameter query
            query.setParameter("theInstructorId", theId);

            //execute Query and get instructor
            Instructor tempInstructor = query.getSingleResult();


            System.out.println("Instructor: " + tempInstructor);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Session is closed now\n\n");

            //get course for instructor
            System.out.println("Courses: " + tempInstructor.getCourses());


        } catch (NullPointerException e) {
            System.out.println("Enter correct id");
        } finally {
            session.close();
            factory.close();
        }
    }
}