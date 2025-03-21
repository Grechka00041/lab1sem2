package org.example.utils;

import org.example.people.Student;
import org.example.people.Teacher;

import java.util.Objects;
import java.util.Scanner;


import static org.example.utils.DataUtils.*;
import static org.example.utils.FileUtils.readTeachersFile;
import static org.example.utils.FileUtils.writeFile;
import static org.example.utils.RecordUtils.*;


public class MenuUtils {

    public static void printMainMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1. Создать новую запись");
        System.out.println("2. Редактирование записи");
        System.out.println("3. Вывод данных о всех студентах ");
        System.out.println("4. Вывод данных о всех студентах из группы");
        System.out.println("5. Вывод данных о всех студентах с определенным номером");
        System.out.println("6. Вывод студентов по оценкам");
        System.out.println("7. Вывод топа студентов");
        System.out.println("8. Вывод информации о поле студентов");
        System.out.println("9. Вывод информации о преподавателе");
        System.out.println("10. Выход");
    }
    static void printMenuForEditing(){
        System.out.println("1. ФИО");
        System.out.println("2. Пол");
        System.out.println("3. Номер студента ");
        System.out.println("4. Номер группы");
        System.out.println("5. Оценки за экзамены");
        System.out.println("6. Оценки за тесты");
        System.out.println("7. Выход");
    }
    public static void doMenu(Student[] students, Scanner scanner) {
        int choice;
        do {
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner.nextLine();
                    createRecord(students, scanner);
                    break;
                case 2:
                    editRecord(students, scanner);
                    break;
                case 3:
                    System.out.println("ФИО/ПОЛ/ГРУППА/НОМЕР/АИГ/МАТАН/ПРОГА/ФИЗИКА/ИНФА/ИНЯЗ/ФИЛОСОФИЯ/ОРГ");
                    int count = 0;
                    for (Student student : students) {
                        if (Objects.isNull(student)) {
                            break;
                        }
                        count++;
                        System.out.print(count + ") ");
                        readStudentRecord(student);

                    }
                    break;
                case 4:
                    System.out.print("Выберите нужную группу: ");
                    int group = scanner.nextInt();
                    while (!(group > 0 && (int) Math.log10(group) + 1 == 4)) {
                        System.out.println("\nПожалуйста введите корректное значение");
                        group = scanner.nextInt();
                    }
                    showGroup(students, group);
                    break;
                case 5:
                    System.out.print("Выберите нужный номер студентов: ");
                    int num = scanner.nextInt();
                    showStudentsOfNumber(students, num);
                    break;
                case 6:
                    System.out.println("Выберите каких студентов показать(1 - отличники, 2 - хорошисты, 3 - без стипендии, 4 - со стипендией, 5 - выход): ");
                    showMarksStatistics(students, scanner);
                    break;
                case 7:
                    System.out.print("Выберите количество студентов для вывода: ");
                    int numberForPrint = scanner.nextInt();
                    int numOfExams = students[0].getExamMarks().length + students[0].getTestsMarks().length;
                    for (int i = 0; i < countRecords(students); i++) {
                        findAverage(students[i], numOfExams);
                    }
                    Student[] studentsSortedByMarks = new Student[countRecords(students)];
                    sortByAverage(students, studentsSortedByMarks);
                    for (int i = 0; i < numberForPrint; i++) {
                        readStudentRecord(studentsSortedByMarks[i]);
                    }
                    break;
                case 8:
                    System.out.println("Женщин - " + RecordUtils.female + ", мужчин - " + RecordUtils.male);
                    break;
                case 9:
                    Teacher[] teachers = new Teacher[500];
                    readTeachersFile(teachers, scanner);
                    System.out.println("Опции: 1 - поиск по фамилии, 2 - поиск по номеру группы, 3 - поиск по дисциплине, 4 - выход ");
                    findTeacher(scanner, teachers);
                    break;
                case 10:
                    for (Student student : students) {
                        if (Objects.isNull(student)) {
                            break;
                        } else {
                            writeFile(students);
                        }
                    }
                    System.out.println("Данные сохранены.");
                    break;
                default:
                    System.out.println("Пожалуйста выберите число из предложенных вариантов.");
            }

        } while (choice != 10);

    }
}