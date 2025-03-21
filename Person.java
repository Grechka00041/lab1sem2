package org.example.people;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Person {
    private String firstName;
    private String secondName;
    private String fathersName;
    private byte sex;
    public void setFullName(String firstName, String secondName, String fathersName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.fathersName = fathersName;
    }

    public String getFullName(){
        return  firstName + " " + secondName + " " + fathersName;
    }
}
