package ua.fedii.hibernate.demo_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.fedii.hibernate.entity.Teacher;
import ua.fedii.hibernate.entity.TeacherDetail;

public class TeacherDAO {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure()
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(TeacherDetail.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


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

    }
}
