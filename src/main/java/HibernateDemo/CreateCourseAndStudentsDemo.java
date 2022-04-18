package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class CreateCourseAndStudentsDemo {

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

            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            System.out.println("Saving the course...");
            session.save(tempCourse);
            System.out.println("Saved the Course: "+ tempCourse);

            Student tempStudent1 = new Student("Bhavya","Sri","bhavya@gmail.com");
            Student tempStudent2 = new Student("Mary","Public","Marya@gmail.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            System.out.println("\n\nSaving Students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students: "+ tempCourse.getStudents());

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
