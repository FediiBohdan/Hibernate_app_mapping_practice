package ua.fedii.hibernate.one_to_many.demo_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.fedii.hibernate.one_to_many.entity.Course;
import ua.fedii.hibernate.one_to_many.entity.Teacher;
import ua.fedii.hibernate.one_to_many.entity.TeacherDetail;

import java.util.Scanner;

public class TeacherDAO {
    public static void main(String[] args) {

        int exitFlag;

        do {
            Configuration configuration = new Configuration();
            configuration.configure()
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(TeacherDetail.class)
                    .addAnnotatedClass(Course.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            Scanner in = new Scanner(System.in);

            System.out.print("\nChoose the action. \n" +
                    "1 - Create a new teacher\n" +
                    "2 - Create course\n" +
                    "3 - Get teacher's courses\n" +
                    "4 - Delete a course\n" +
                    "Your choice: ");

            int number = in.nextInt();

            switch (number) {
                case 1:
                    try {
                        Teacher tempTeacher = new Teacher("Johan", "Davidson", "johhh@mail.co");
                        TeacherDetail tempTeacherDetail = new TeacherDetail("Lviv, Ukraine", "dancing");

                        tempTeacher.setTeacherDetail(tempTeacherDetail);

                        session.beginTransaction();
                        session.save(tempTeacher);
                        session.getTransaction().commit();
                    }
                    finally {
                        session.close();
                        sessionFactory.close();
                    }
                    break;
                case 2:
                    try {
                        session.beginTransaction();
                        int theId = 3;
                        Teacher tempTeacher = session.get(Teacher.class, theId);

                        Course tempCourse1 = new Course("UX/UI course");
                        Course tempCourse2 = new Course("Arduino course");

                        tempTeacher.add(tempCourse1);
                        tempTeacher.add(tempCourse2);

                        session.save(tempCourse1);
                        session.save(tempCourse2);

                        session.getTransaction().commit();
                    }
                    finally {
                        session.close();
                        sessionFactory.close();
                    }
                    break;
                case 3:
                    try {
                        session.beginTransaction();
                        int theId = 3;

                        Teacher tempTeacher = session.get(Teacher.class, theId);

                        System.out.println("Teacher: " + tempTeacher);

                        System.out.println("Courses: " + tempTeacher.getCourses());

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

                        int theId = 1;
                        Course tempCourse = session.get(Course.class, theId);

                        session.delete(tempCourse);
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
