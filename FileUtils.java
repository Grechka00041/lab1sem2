package org.example.utils;

import org.example.people.Student;
import org.example.people.Teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FileUtils {
    static void writeFile(Student[] students) {
        boolean appendFile = false;
        for(Student student: students) {
            if (Objects.isNull(student)) {
                break;
            } else {
                try (FileWriter writer = new FileWriter("students.txt", appendFile)) {
                    writer.write(student.getFullName() + " ");
                    writer.write(student.getSex() + " ");
                    writer.write(student.getGroupNumber() + " ");
                    writer.write(student.getStudentsNumber() + " ");
                    byte[] examMarks = student.getExamMarks();
                    for (int examMark : examMarks) {
                        writer.write(examMark + " ");
                    }
                    byte[] testMarks = student.getTestsMarks();
                    for (int testMark : testMarks) {
                        writer.write(testMark + " ");
                    }
                    writer.write('\n');
                } catch (IOException ex) {
                    System.out.println("Ошибка при записи данных в файл.");
                }
                appendFile = true;
            }
        }
    }
    static void readTeachersFile(Teacher[] teachers, Scanner scanner){
        try {
            File file = new File("teachers.txt");
            scanner = new Scanner(file);
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!line.isEmpty()){
                    teachers[num] = new Teacher();
                    String[] elements = line.split(" ");
                    teachers[num].setFullName(elements[0], elements[1], elements[2]);
                    teachers[num].setSex(Byte.parseByte(elements[3]));
                    teachers[num].setAge(Integer.parseInt(elements[4]));
                    teachers[num].setDegree(elements[5]);
                    teachers[num].setSubject(elements[6]);
                    teachers[num].setMail(elements[7]);
                    teachers[num].setGroupNumber(Integer.parseInt(elements[8]));
                    ++num;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при открытии файла.");
        }

    }
    public static void readStudentsFile(Student[] students, Scanner scanner){
        try {
            File file = new File("students.txt");
            scanner = new Scanner(file);
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!line.isEmpty()){
                    String[] elements = line.split(" ");
                    students[num] = new Student();
                    students[num].setFullName(elements[0], elements[1], elements[2]);
                    int sex = Byte.parseByte(elements[3]);
                    if ((sex == 1)) {
                        RecordUtils.male++;
                    } else {
                        RecordUtils.female++;
                    }
                    students[num].setSex((byte)sex);
                    students[num].setGroupNumber(Integer.parseInt(elements[4]));
                    students[num].setStudentsNumber(Byte.parseByte(elements[5]));
                    byte[] examMarks = {Byte.parseByte(elements[6]), Byte.parseByte(elements[7]), Byte.parseByte(elements[8])};
                    students[num].setExamMarks(examMarks);
                    byte[] testMarks = {Byte.parseByte(elements[9]), Byte.parseByte(elements[10]), Byte.parseByte(elements[11]), Byte.parseByte(elements[12]),Byte.parseByte(elements[13])};
                    students[num].setTestsMarks(testMarks);
                    num++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при открытии файла.");
        }
    }
}