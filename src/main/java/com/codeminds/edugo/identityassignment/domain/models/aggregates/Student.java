package com.codeminds.edugo.identityassignment.domain.models.aggregates;


import com.codeminds.edugo.identityassignment.domain.models.entities.Wristband;
import com.codeminds.edugo.shared.domain.models.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student extends AuditableAbstractAggregateRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "school_address")
    private String schoolAddress;

    @Column(name = "student_photo_url")
    private String studentPhotoUrl;

    protected Student(){
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public Student(String name, String lastName, String homeAddress, String schoolAddress, String studentPhotoUrl){
        this.name = name;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.schoolAddress = schoolAddress;
        this.studentPhotoUrl = studentPhotoUrl;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, optional = true)
    private Wristband wristband;
}
