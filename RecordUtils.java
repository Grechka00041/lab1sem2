package org.example.utils;

import org.example.people.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static org.example.utils.MenuUtils.printMenuForEditing;

public class RecordUtils {
    public static int female = 0;
    public static int male = 0;

    public static void createRecord(Student[] students, Scanner scanner) {
        Student student = new Student();
        System.out.print("Введите имя, фамилию и отчество студента: ");
        String line = scanner.nextLine();
        String[] fullName = line.split(" ");
        student.setFullName(fullName[0], fullName[1], fullName[2]);
        System.out.print("Введите пол студента(1 - М, 0 - Ж): ");
        byte sex = scanner.nextByte();
        while(!(sex == 0 || sex == 1)){
            System.out.println("\nПожалуйста введите корректное значение");
            sex = scanner.nextByte();
        }
        if ((sex == 1)) {
            male++;
        } else {
            female++;
        }
        student.setSex(sex);
        System.out.print("Укажите номер группы студента(в виде четырехзначного числа):");
        int group = scanner.nextInt();
        while (!(group > 0 && (int) Math.log10(group) + 1 == 4)) {
            System.out.println("\nПожалуйста введите корректное значение");
            group = scanner.nextInt();
        }
        student.setGroupNumber(group);
        System.out.print("Укажите номер студента: ");
        student.setStudentsNumber(scanner.nextByte());
        System.out.println("Введите оценки за экзамены(АиГ, Матан, Программирование): ");
        byte[] examMarks = new byte[3];
        for (byte mark: examMarks){
            System.out.print("Оценка: ");
            mark = scanner.nextByte();
            while(mark <= 2 || mark > 5){
                System.out.print("Введите число от 3 до 5: ");
                mark = scanner.nextByte();
            }
        }
        scanner.nextLine();
        student.setExamMarks(examMarks);
        System.out.println("Введите оценки за диф. зачеты (физика, информатика, иностранный язык, философия, ОРГ): ");
        byte[] testMarks = new byte[5];
        for (byte mark: testMarks){
            System.out.print("Оценка: ");
            mark = scanner.nextByte();
            while(mark <= 2 || mark > 5){
                System.out.print("Введите число от 3 до 5: ");
                mark = scanner.nextByte();
            }
        }
        student.setTestsMarks(testMarks);
        boolean accepted = true;
        for(int i = 0; i < countRecords(students); i++){
            if((Objects.equals(students[i].getFirstName(), fullName[0])) && (Objects.equals(students[i].getSecondName(), fullName[1])) && (Objects.equals(students[i].getFathersName(), fullName[2])) && (students[i].getGroupNumber() == group)){
                System.out.println("Студент уже был добавлен ранее. Эти данные не будут сохранены, пожалуйста отредактируйте студента, а не добавляйте заново.");
                accepted = false;
                break;
            }
        }
        if (accepted){
            students[countRecords(students)] = student;
        }
        scanner.nextLine();
    }

    static void readStudentRecord(Student student) {
        System.out.print(student.getFullName() + " " + student.getSex() + " " + student.getGroupNumber() + " " + student.getStudentsNumber());
        System.out.print(" " + Arrays.toString(student.getExamMarks()) + " " + Arrays.toString(student.getTestsMarks()) + "\n");
    }

    static void readTeacherRecord(Teacher teacher) {
        System.out.print(teacher.getFullName() + " " + teacher.getSex() + " " + teacher.getAge() + " " + teacher.getDegree());
        System.out.print(" " + teacher.getSubject() + " " + teacher.getMail() + " " + teacher.getGroupNumber() + "\n");
    }

    static void editRecord(Student[] students, Scanner scanner){
        byte choice;
        boolean accepted = true;
        printMenuForEditing();
        scanner.nextLine();
        System.out.print("Введите имя, фамилию и отчество студента: ");
        String line = scanner.nextLine();
        String[] fullName = line.split(" ");
        int i;
        for(i = 0; i < countRecords(students); i++){
            if((Objects.equals(students[i].getFirstName(), fullName[0])) && (Objects.equals(students[i].getSecondName(), fullName[1])) && (Objects.equals(students[i].getFathersName(), fullName[2]))){
                accepted = true;
                break;
            }
            else{
                accepted = false;
            }
        }

        do{
            System.out.print("Введите номер нужного действия: ");
            if (accepted){
                choice = scanner.nextByte();
            }
            else{
                System.out.println("Студент не был найден. Редактирование невозможно.");
                choice=7;
            }
            switch(choice){
                case 1:
                    System.out.print("Введите имя, фамилию и отчество студента: ");
                    scanner.nextLine();
                    String line1 = scanner.nextLine();
                    String[] name = line1.split(" ");
                    students[i].setFullName(name[0], name[1], name[2]);
                    break;
                case 2:
                    System.out.print("Введите значение(1 - М, 0 - Ж): ");
                    byte sex = scanner.nextByte();
                    while(!(sex == 0 || sex == 1)){
                        System.out.println("\nПожалуйста введите корректное значение");
                        sex = scanner.nextByte();
                    }
                    if ((sex == 1)) {
                        male++;
                        female--;
                    } else {
                        male--;
                        female++;
                    }
                    students[i].setSex(scanner.nextByte());
                    break;
                case 3:
                    System.out.print("Введите номер студента: ");
                    students[i].setStudentsNumber(scanner.nextByte());
                    break;
                case 4:
                    System.out.print("Введите группу студента: ");
                    students[i].setGroupNumber(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Введите оценки за экзамены(АиГ, Матан, Программирование): ");
                    byte[] examMarks = new byte[3];
                    for (byte mark: examMarks){
                        System.out.print("Оценка: ");
                        mark = scanner.nextByte();
                        while(mark <= 2 || mark > 5){
                            System.out.print("Введите число от 3 до 5: ");
                            mark = scanner.nextByte();
                        }
                    }
                    students[i].setExamMarks(examMarks);
                    break;
                case 6:
                    System.out.println("Введите оценки за диф. зачеты (физика, информатика, иностранный язык, философия, ОРГ): ");
                    byte[] testMarks = new byte[5];
                    for (byte mark: testMarks){
                        System.out.print("Оценка: ");
                        mark = scanner.nextByte();
                        while(mark <= 2 || mark > 5){
                            System.out.print("Введите число от 3 до 5: ");
                            mark = scanner.nextByte();
                        }
                    }
                    students[i].setExamMarks(testMarks);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Пожалуйста выберите число из предложенных вариантов.");
            }
        }while(choice != 7);
    }

    static int countRecords(Person[] people){
        int count;
        for (count = 0; count < people.length; count++){
            if (people[count] == null){
                break;
            }
        }
        return count;
    }
}