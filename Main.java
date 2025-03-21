package org.example;

import java.util.Scanner;

import org.example.people.Person;
import org.example.people.Student;

import static org.example.utils.FileUtils.readStudentsFile;
import static org.example.utils.MenuUtils.doMenu;
import static org.example.utils.MenuUtils.printMainMenu;
import static org.example.utils.RecordUtils.createRecord;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[500];
        readStudentsFile(students, scanner);
        if (students[0] == null) {
            System.out.println("В базе нет ни одной записи. Пожалуйста добавьте новую.");
            createRecord(students, scanner);
        }
        printMainMenu();
        doMenu(students, scanner);
        scanner.close();
    }
}