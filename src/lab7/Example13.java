package lab7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Example13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "src/lab7/example13/student.ser";
        File file = new File(fileName);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        System.out.print("Введите имя студента: ");
        String name = scanner.nextLine();
        System.out.print("Введите группу: ");
        String group = scanner.nextLine();
        System.out.print("Введите средний балл: ");
        double averageGrade = Double.parseDouble(scanner.nextLine());

        Student student = new Student(name, group, averageGrade);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(student);
            System.out.println("Объект записан в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи объекта: " + e.getMessage());
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Student restoredStudent = (Student) in.readObject();
            System.out.println("Восстановленный объект:");
            System.out.println("Имя: " + restoredStudent.getName());
            System.out.println("Группа: " + restoredStudent.getGroup());
            System.out.println("Средний балл: " + restoredStudent.getAverageGrade());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении объекта: " + e.getMessage());
        }
    }

    private static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String name;
        private final String group;
        private final double averageGrade;

        Student(String name, String group, double averageGrade) {
            this.name = name;
            this.group = group;
            this.averageGrade = averageGrade;
        }

        String getName() {
            return name;
        }

        String getGroup() {
            return group;
        }

        double getAverageGrade() {
            return averageGrade;
        }
    }
}
