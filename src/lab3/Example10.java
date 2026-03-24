package lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

// В кругу стоят N человек, пронумерованных от 1 до N. При ведении счета по кругу
// вычеркивается каждый второй человек, пока не останется один.
// Составить две программы, моделирующие процесс. Одна из программ должна использовать
// класс ArrayList, а вторая - LinkedList. Какая из двух программ работает быстрее? Почему?
public class Example10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество человек N: ");
        int n = scanner.nextInt();

        long startArray = System.nanoTime();
        int winnerArray = solveWithArrayList(n);
        long endArray = System.nanoTime();

        long startLinked = System.nanoTime();
        int winnerLinked = solveWithLinkedList(n);
        long endLinked = System.nanoTime();

        System.out.println("Победитель (ArrayList): " + winnerArray);
        System.out.println("Время ArrayList: " + (endArray - startArray) + " нс");

        System.out.println("Победитель (LinkedList): " + winnerLinked);
        System.out.println("Время LinkedList: " + (endLinked - startLinked) + " нс");

        if ((endArray - startArray) < (endLinked - startLinked)) {
            System.out.println("Быстрее сработал ArrayList.");
        } else if ((endArray - startArray) > (endLinked - startLinked)) {
            System.out.println("Быстрее сработал LinkedList.");
        } else {
            System.out.println("Обе программы отработали примерно одинаково.");
        }

        scanner.close();
    }

    public static int solveWithArrayList(int n) {
        ArrayList<Integer> people = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        int index = 0;

        while (people.size() > 1) {
            index = (index + 1) % people.size();
            people.remove(index);
        }

        return people.get(0);
    }

    public static int solveWithLinkedList(int n) {
        LinkedList<Integer> people = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            people.add(i);
        }

        ListIterator<Integer> iterator = people.listIterator();
        boolean delete = false;

        while (people.size() > 1) {
            if (!iterator.hasNext()) {
                iterator = people.listIterator();
            }

            iterator.next();

            if (delete) {
                iterator.remove();
            }

            delete = !delete;
        }

        return people.get(0);
    }
}