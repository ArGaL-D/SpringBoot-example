package com.api.example02.rest.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @SequenceGenerator( name = "student_sequence",
                        sequenceName = "student_sequence",
                        allocationSize = 2
                      )
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator = "student_sequence")
    private Long id;
    private String name;
    private String surname;
    private String email;

    /* Constructors */
    
    public Student () {}

    public Student (Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }    
    
    public Student (String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    /* Getters and Setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /* toString */

    @Override
    public String toString() {
        return "Student [email=" + email + ", id=" + id + ", name=" + name + ", surname=" + surname + "]";
    }    

}
