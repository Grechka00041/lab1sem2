package org.example.people;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Teacher extends Person {
    private int age;
    private String degree;
    private String subject;
    private String mail;
    private int groupNumber;

}
