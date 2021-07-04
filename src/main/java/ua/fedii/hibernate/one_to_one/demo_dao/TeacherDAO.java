package ua.fedii.hibernate.one_to_one.demo_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.fedii.hibernate.one_to_one.entity.Teacher;
import ua.fedii.hibernate.one_to_one.entity.TeacherDetail;

import java.util.Scanner;

public class TeacherDAO {
    public static void main(String[] args) {

        int exitFlag;

        do {
            Configuration configuration = new Configuration();
            configuration.configure()
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(TeacherDetail.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            Scanner in = new Scanner(System.in);

            System.out.print("\nChoose the action. \n" +
                    "1 - Create a new teacher\n" +
                    "2 - Delete the teacher\n" +
                    "3 - Get teacher detail\n" +
                    "4 - Delete teacher detail\n" +
                    "Your choice: ");

            int number = in.nextInt();

            switch (number) {
                case 1:
                    try {
                        Teacher tempTeacher = new Teacher("Brian", "Smith", "bri@mail.co");
                        TeacherDetail tempTeacherDetail = new TeacherDetail("Spain, Barcelona", "football");

                        tempTeacher.setTeacherDetail(tempTeacherDetail);

                        session.beginTransaction();
                        session.save(tempTeacher);
                        session.getTransaction().commit();
                    }
                    finally {
                        sessionFactory.close();
                    }
                    break;
                case 2:
                    try {
                        session.beginTransaction();

                        int theId = 4;

                        Teacher tempTeacher = session.get(Teacher.class, theId);

                        if (tempTeacher != null) {
                            session.delete(tempTeacher);
                        }

                        session.getTransaction().commit();
                    }
                    finally {
                        sessionFactory.close();
                    }
                    break;
                case 3:
                    try {
                        session.beginTransaction();
                        int theId = 6;

                        TeacherDetail tempTeacherDetail = session.get(TeacherDetail.class, theId);

                        System.out.println("tempTeacherDetail: " + tempTeacherDetail);
                        System.out.println("The associated teacher: " + tempTeacherDetail.getTeacher());

                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        session.close();
                        sessionFactory.close();
                    }
                    break;
                case 4:
                    try {
                        session.beginTransaction();
                        int theId = 6;

                        TeacherDetail tempTeacherDetail = session.get(TeacherDetail.class, theId);

                        // breaking bi-directional link
                        tempTeacherDetail.getTeacher().setTeacherDetail(null);

                        session.delete(tempTeacherDetail);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        session.close();
                        sessionFactory.close();
                    }
                    break;
            }

            System.out.println("Action " + number + " was fulfilled!\n");

            System.out.print("Continue? YES - print \"1\", NO - print \"0\": ");

            exitFlag = in.nextInt();
        } while (exitFlag == 1);
    }
}
