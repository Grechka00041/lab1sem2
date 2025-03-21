package org.example.people;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student extends Person {
    private byte studentsNumber;
    private int groupNumber;
    private float markAverage;
    private byte[] examMarks = new byte[3];
    private byte[] testsMarks = new byte[5];

}
