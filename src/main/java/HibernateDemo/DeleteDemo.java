package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).configure("hibernate.cfg.xml").buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

            //get instructor by primary key
            int theId =1;

            Instructor tempInstructor = session.get(Instructor.class,theId);

            System.out.println("Found instructor: "+tempInstructor);

            if(tempInstructor != null)
            {
                System.out.println("Deleting: "+tempInstructor);

                //it also delete the instructor details because of cascade.all
                session.delete(tempInstructor);
            }

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
