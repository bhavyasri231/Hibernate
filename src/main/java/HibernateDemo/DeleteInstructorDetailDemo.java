package HibernateDemo;

import HibernateDemo.HibernateDemoEntity.Instructor;
import HibernateDemo.HibernateDemoEntity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try
        {
            //start a transaction
            session.beginTransaction();

           int theId = 3;
           InstructorDetail tempInstructorDetail = (InstructorDetail) session.get(InstructorDetail.class,theId);

           System.out.println("tempInstructorDetail: "+tempInstructorDetail);

           //print associated instructor
            System.out.println("The associated instructor: "+ tempInstructorDetail.getInstructor());

            //delete instructor detail
            System.out.println("Deleting tempInstructorDetail: "+tempInstructorDetail);

            //remove the association object reference
            //break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        finally {
            //to handle the leak
           // session.close();
            factory.close();
        }
    }
}
