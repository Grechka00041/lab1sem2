package org.example.utils;

import java.util.Objects;
import java.util.Scanner;
import org.example.people.Student;
import org.example.people.Teacher;

import static org.example.utils.RecordUtils.*;

public class DataUtils {

    static void showGroup(Student[] students, int group){
        int count = 0;
        for(Student student: students){
            if (Objects.isNull(student)){
                break;
            }
            if (student.getGroupNumber() == group){
                count++;
                System.out.print(count + ") ");
                readStudentRecord(student);
            }
        }
        System.out.println("Вывод закончен.");
    }

    static void showStudentsOfNumber(Student[] students, int num){
        int count = 0;
        for(Student student: students){
            if (Objects.isNull(student)){
                break;
            }
            if (student.getStudentsNumber() == num){
                count++;
                System.out.print(count + ") ");
                readStudentRecord(student);
            }
        }
        System.out.println("Вывод закончен.");
    }
    static void showMarksStatistics(Student[] students, Scanner scanner) {
        int choice;
        int numOfStudents = countRecords(students);
        do {
            int count = 0;
            System.out.print("Пункт: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Отличники: ");
                    for (int i = 0; i < numOfStudents; i++) {
                        boolean hasNotFive = false;
                        for (int mark : students[i].getTestsMarks()) {
                            if (mark == 3 || mark == 4) {
                                hasNotFive = true;
                                break;
                            }
                        }
                        for (int mark : students[i].getExamMarks()) {
                            if (mark == 3 || mark == 4) {
                                hasNotFive = true;
                                break;
                            }
                        }
                        if (!hasNotFive) {
                            count++;
                            System.out.print(count + ") ");
                            readStudentRecord(students[i]);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Хорошисты: ");
                    for (int i = 0; i < numOfStudents; i++) {
                        boolean hasThree = false;
                        boolean hasFour = false;
                        for (int mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                            if (mark == 4) {
                                hasFour = true;
                            }
                        }
                        for (int mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                            if (mark == 4) {
                                hasFour = true;
                            }
                        }
                        if (hasFour && !hasThree) {
                            count++;
                            System.out.print(count + ") ");
                            readStudentRecord(students[i]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Без стипендии: ");
                    for (int i = 0; i < numOfStudents; i++) {
                        boolean hasThree = false;
                        for (int mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        for (int mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        if (hasThree) {

                            count++;
                            System.out.print(count + ") ");
                            readStudentRecord(students[i]);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Со стипендией: ");
                    for (int i = 0; i < numOfStudents; i++) {
                        boolean hasThree = false;
                        for (int mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        for (int mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        if (!hasThree) {
                            count++;
                            System.out.print(count + ") ");
                            readStudentRecord(students[i]);
                        }
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Введите корректное значение.");
            }

        }while(choice != 5);
    }
    static void findAverage(Student student, int numOfExams){
        int sum = 0;
        for(int mark: student.getExamMarks()){
            sum += mark;
        }
        for(int mark: student.getTestsMarks()){
            sum += mark;
        }
        float average = (float) sum / numOfExams;
        student.setMarkAverage(average);

    }
    static void sortByAverage(Student[] students, Student[] studentsSortedByMarks){
        System.arraycopy(students, 0, studentsSortedByMarks, 0, countRecords(students));
        for (int i = 0; i < studentsSortedByMarks.length; i++){
            for (int j = 0; j < studentsSortedByMarks.length - i - 1; j++){
                if (studentsSortedByMarks[j].getMarkAverage() < studentsSortedByMarks[j+1].getMarkAverage()){
                    Student temp = studentsSortedByMarks[j];
                    studentsSortedByMarks[j] = studentsSortedByMarks[j+1];
                    studentsSortedByMarks[j+1] = temp;
                }
            }
        }
    }
    static void findTeacher(Scanner scanner, Teacher[] teachers){
        int reply;
        int numOfTeachers = countRecords(teachers);
        do{
            System.out.print("Введите номер нужного действия: ");
            reply = scanner.nextInt();
            switch (reply){
                case 1:
                    System.out.print("Введите фамилию нужного преподавателя: ");
                    scanner.nextLine();
                    String secondName = scanner.nextLine();
                    for(int i=0; i < numOfTeachers; i++){
                        if(teachers[i].getSecondName().equalsIgnoreCase(secondName)){
                            readTeacherRecord(teachers[i]);
                        }
                    }
                    System.out.println("\nКонец поиска.");
                    break;
                case 2:
                    System.out.print("Введите группу нужного преподавателя: ");
                    scanner.nextInt();
                    Integer groupNumber = scanner.nextInt();
                    for(int i=0; i < numOfTeachers; i++){
                        if(Objects.equals(teachers[i].getGroupNumber(), groupNumber)){
                            readTeacherRecord(teachers[i]);
                        }
                    }
                    System.out.println("\nКонец поиска.");
                    break;
                case 3:

                    System.out.print("Введите предмет нужного преподавателя(АиГ/Матан/Программирование/Физика/История): ");
                    scanner.nextLine();
                    String subject = scanner.nextLine();
                    for(int i=0; i < numOfTeachers; i++){
                        if(teachers[i].getSubject().equalsIgnoreCase(subject)){
                            readTeacherRecord(teachers[i]);
                        }
                    }
                    System.out.println("\nКонец поиска.");
                    break;
                case 4:
                    break;
            }
        }while(reply != 4);
    }

}
