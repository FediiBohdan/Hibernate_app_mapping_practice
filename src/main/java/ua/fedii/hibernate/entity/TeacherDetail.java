package ua.fedii.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_detail", schema = "public", catalog = "school")
public class TeacherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "hobby")
    private String hobby;

    public TeacherDetail() {}

    public TeacherDetail(String address, String hobby) {
        this.address = address;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "TeacherDetail{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
