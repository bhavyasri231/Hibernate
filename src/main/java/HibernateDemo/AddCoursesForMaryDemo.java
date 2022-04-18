package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class AddCoursesForMaryDemo {

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

            int studentId = 2;
            Student tempStudent = (Student) session.get(Student.class,studentId);

            System.out.println("\nLoading student: "+ tempStudent);
            System.out.println("Courses: "+tempStudent.getCourses());

            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            System.out.println("Saving courses...");
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            session.save(tempCourse1);
            session.save(tempCourse2);

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
