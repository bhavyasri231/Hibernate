package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DeleteMaryStudentDemo {

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

            System.out.println("\n\nDeleting student: "+tempStudent);
            session.delete(tempStudent);
            
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
