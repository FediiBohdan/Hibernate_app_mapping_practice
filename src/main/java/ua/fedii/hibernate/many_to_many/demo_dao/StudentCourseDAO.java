package ua.fedii.hibernate.many_to_many.demo_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.fedii.hibernate.many_to_many.entity.Course;
import ua.fedii.hibernate.many_to_many.entity.Student;

import java.util.Scanner;

public class StudentCourseDAO {
    public static void main(String[] args) {

        int exitFlag;

        do {
            Configuration configuration = new Configuration();
            configuration.configure()
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            Scanner in = new Scanner(System.in);

            System.out.print("\nChoose the action. \n" +
                    "1 - Create a new course\n" +
                    "2 - Add course to a student\n" +
                    "3 - Get student's courses\n" +
                    "4 - Delete a course\n" +
                    "5 - Delete a student\n" +
                    "Your choice: ");

            int number = in.nextInt();

            switch (number) {
                case 1:
                    try {
                        session.beginTransaction();

                        Course tempCourse = new Course("Artificial intelligence");

                        session.save(tempCourse);

                        Student tempStudent1 = new Student("John", "Ricans", "john@mail.co");
                        Student tempStudent2 = new Student("Freddie", "Ford", "fred@mail.co");

                        tempCourse.addStudent(tempStudent1);
                        tempCourse.addStudent(tempStudent2);

                        session.save(tempStudent1);
                        session.save(tempStudent2);

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
                        int studentId = 2;

                        Student tempStudent = session.get(Student.class, studentId);

                        Course tempCourse1 = new Course("Android development");
                        Course tempCourse2 = new Course("iOS development");

                        tempCourse1.addStudent(tempStudent);
                        tempCourse2.addStudent(tempStudent);

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
                        int studentId = 2;

                        Student tempStudent = session.get(Student.class, studentId);

                        System.out.println(tempStudent.getCourses());

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

                        int courseId = 5;
                        Course tempCourse = session.get(Course.class, courseId);

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
                case 5:
                    try {
                        session.beginTransaction();
                        int studentId = 2;

                        Student tempStudent = session.get(Student.class, studentId);

                        System.out.println(tempStudent.getCourses());

                        session.delete(tempStudent);

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
