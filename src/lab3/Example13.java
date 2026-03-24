package lab3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/*
Необходимо произвести вычисление времени работы нижеперечисленных операций,
количество элементов коллекциях и сами коллекции указаны в индивидуальном варианте.
После выполнения вычислений необходимо заполнить таблицы
и привести скриншоты кода программы и данных вывода в консоль.

- Сравнение времени выполнения операции добавление элемента в начало коллекции;
- Сравнение времени выполнения операции добавление элемента в конец коллекции;
- Сравнение времени выполнения операции добавление элемента в середину коллекции;
- Сравнение времени выполнения операции удаления элемента в начале коллекции;
- Сравнение времени выполнения операции удаления элемента в конце коллекции;
- Сравнение времени выполнения операции удаления элемента в середине коллекции;
- Сравнение времени выполнения операции получения элемента по индексу;

Данные для моего варианта:

- Коллекции: ArrayDeque, SortedMap, ArrayList
- Количество элементов в коллекции: 11 000 000 (11 миллионов)
- Количество элементов для вычисления скорости получения по индексу: 11 000 000 000 (11 миллиардов)

В этом решении:

- Для SortedMap используется реализация TreeMap.
- Для ArrayDeque операции "в середину" и "по индексу" не поддерживаются.
- Для SortedMap операция "по индексу" не поддерживается, так как у Map нет индексов.
*/

public class Example13 {
    private static final int ELEMENT_COUNT = 11_000_000;
    private static final long GET_BY_INDEX_COUNT = 11_000_000_000L;

    // Чтобы JIT не выбрасывал вычисления при тесте получения элемента
    private static volatile long blackHole = 0;

    public static void main(String[] args) {
        System.out.println("Количество элементов в коллекции: " + ELEMENT_COUNT);
        System.out.println("Количество операций получения по индексу: " + GET_BY_INDEX_COUNT);
        System.out.println("Для SortedMap используется TreeMap.");
        System.out.println();

        Result arrayDequeResult = testArrayDeque();
        System.gc();

        Result sortedMapResult = testSortedMap();
        System.gc();

        Result arrayListResult = testArrayList();
        System.gc();

        printAddTable(arrayDequeResult, sortedMapResult, arrayListResult);
        printRemoveTable(arrayDequeResult, sortedMapResult, arrayListResult);
        printGetTable(arrayDequeResult, sortedMapResult, arrayListResult);

        System.out.println();
        System.out.println("Контрольное значение blackHole = " + blackHole);
    }

    private static Result testArrayDeque() {
        System.out.println("Тестируется ArrayDeque...");
        ArrayDeque<Integer> deque = createArrayDeque(ELEMENT_COUNT);

        Result result = new Result("ArrayDeque");

        long start;
        long end;

        start = System.nanoTime();
        deque.addFirst(1);
        end = System.nanoTime();
        result.addFirst = formatTime(end - start);
        deque.removeFirst();

        start = System.nanoTime();
        deque.addLast(1);
        end = System.nanoTime();
        result.addLast = formatTime(end - start);
        deque.removeLast();

        result.addMiddle = "не поддерживается";

        start = System.nanoTime();
        Integer firstValue = deque.removeFirst();
        end = System.nanoTime();
        result.removeFirst = formatTime(end - start);
        deque.addFirst(firstValue);

        start = System.nanoTime();
        Integer lastValue = deque.removeLast();
        end = System.nanoTime();
        result.removeLast = formatTime(end - start);
        deque.addLast(lastValue);

        result.removeMiddle = "не поддерживается";
        result.getByIndex = "не поддерживается";

        deque.clear();
        return result;
    }

    private static Result testSortedMap() {
        System.out.println("Тестируется SortedMap (TreeMap)...");
        SortedMap<Integer, Integer> map = createSortedMap(ELEMENT_COUNT);

        Result result = new Result("SortedMap");

        long start;
        long end;

        int firstInsertKey = -1;
        int middleInsertKey = ELEMENT_COUNT - 1; // нечетный ключ, попадает примерно в середину, если в карте только четные ключи
        int lastInsertKey = ELEMENT_COUNT * 2;   // ключ после последнего

        start = System.nanoTime();
        map.put(firstInsertKey, 1);
        end = System.nanoTime();
        result.addFirst = formatTime(end - start);
        map.remove(firstInsertKey);

        start = System.nanoTime();
        map.put(middleInsertKey, 1);
        end = System.nanoTime();
        result.addMiddle = formatTime(end - start);
        map.remove(middleInsertKey);

        start = System.nanoTime();
        map.put(lastInsertKey, 1);
        end = System.nanoTime();
        result.addLast = formatTime(end - start);
        map.remove(lastInsertKey);

        Integer firstKey = map.firstKey();
        Integer firstValue = map.get(firstKey);
        start = System.nanoTime();
        map.remove(firstKey);
        end = System.nanoTime();
        result.removeFirst = formatTime(end - start);
        map.put(firstKey, firstValue);

        Integer middleRemoveKey = (ELEMENT_COUNT / 2) * 2;
        Integer middleValue = map.get(middleRemoveKey);
        start = System.nanoTime();
        map.remove(middleRemoveKey);
        end = System.nanoTime();
        result.removeMiddle = formatTime(end - start);
        map.put(middleRemoveKey, middleValue);

        Integer lastKey = map.lastKey();
        Integer lastValue = map.get(lastKey);
        start = System.nanoTime();
        map.remove(lastKey);
        end = System.nanoTime();
        result.removeLast = formatTime(end - start);
        map.put(lastKey, lastValue);

        result.getByIndex = "не поддерживается";

        map.clear();
        return result;
    }

    private static Result testArrayList() {
        System.out.println("Тестируется ArrayList...");
        ArrayList<Integer> list = createArrayList(ELEMENT_COUNT);

        Result result = new Result("ArrayList");

        long start;
        long end;

        start = System.nanoTime();
        list.add(0, 1);
        end = System.nanoTime();
        result.addFirst = formatTime(end - start);
        list.remove(0);

        int middleIndex = list.size() / 2;
        start = System.nanoTime();
        list.add(middleIndex, 1);
        end = System.nanoTime();
        result.addMiddle = formatTime(end - start);
        list.remove(middleIndex);

        start = System.nanoTime();
        list.add(1);
        end = System.nanoTime();
        result.addLast = formatTime(end - start);
        list.remove(list.size() - 1);

        start = System.nanoTime();
        Integer removedFirst = list.remove(0);
        end = System.nanoTime();
        result.removeFirst = formatTime(end - start);
        list.add(0, removedFirst);

        middleIndex = list.size() / 2;
        start = System.nanoTime();
        Integer removedMiddle = list.remove(middleIndex);
        end = System.nanoTime();
        result.removeMiddle = formatTime(end - start);
        list.add(middleIndex, removedMiddle);

        int lastIndex = list.size() - 1;
        start = System.nanoTime();
        Integer removedLast = list.remove(lastIndex);
        end = System.nanoTime();
        result.removeLast = formatTime(end - start);
        list.add(removedLast);

        start = System.nanoTime();
        long sum = 0;
        int index = 0;
        int size = list.size();

        for (long i = 0; i < GET_BY_INDEX_COUNT; i++) {
            sum += list.get(index);
            index++;
            if (index == size) {
                index = 0;
            }
        }

        end = System.nanoTime();
        blackHole = sum;
        result.getByIndex = formatTime(end - start);

        list.clear();
        return result;
    }

    private static ArrayDeque<Integer> createArrayDeque(int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            deque.addLast(1);
        }
        return deque;
    }

    private static SortedMap<Integer, Integer> createSortedMap(int size) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.put(i * 2, 1);
        }
        return map;
    }

    private static ArrayList<Integer> createArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(1);
        }
        return list;
    }

    private static String formatTime(long nanos) {
        return String.format("%.3f мс", nanos / 1_000_000.0);
    }

    private static void printAddTable(Result arrayDeque, Result sortedMap, Result arrayList) {
        System.out.println();
        System.out.println("Таблица 1. Сравнение времени выполнения операции добавления");
        System.out.println("| Коллекция  | в начало коллекции | в середину коллекции | в конец коллекции |");
        System.out.println("| ---------- | ------------------ | -------------------- | ----------------- |");
        printAddRow(arrayDeque);
        printAddRow(sortedMap);
        printAddRow(arrayList);
    }

    private static void printRemoveTable(Result arrayDeque, Result sortedMap, Result arrayList) {
        System.out.println();
        System.out.println("Таблица 2. Сравнение времени выполнения операции удаления");
        System.out.println("| Коллекция  | в начале коллекции | в середине коллекции | в конце коллекции |");
        System.out.println("| ---------- | ------------------ | -------------------- | ----------------- |");
        printRemoveRow(arrayDeque);
        printRemoveRow(sortedMap);
        printRemoveRow(arrayList);
    }

    private static void printGetTable(Result arrayDeque, Result sortedMap, Result arrayList) {
        System.out.println();
        System.out.println("Таблица 3. Сравнение времени выполнения операции получения элемента по индексу");
        System.out.println("| Коллекция  | По индексу для " + GET_BY_INDEX_COUNT + " элементов |");
        System.out.println("| ---------- | ---------------------------------- |");
        printGetRow(arrayDeque);
        printGetRow(sortedMap);
        printGetRow(arrayList);
    }

    private static void printAddRow(Result result) {
        System.out.println("| " + result.name + " | " + result.addFirst + " | " + result.addMiddle + " | " + result.addLast + " |");
    }

    private static void printRemoveRow(Result result) {
        System.out.println("| " + result.name + " | " + result.removeFirst + " | " + result.removeMiddle + " | " + result.removeLast + " |");
    }

    private static void printGetRow(Result result) {
        System.out.println("| " + result.name + " | " + result.getByIndex + " |");
    }

    private static class Result {
        String name;
        String addFirst;
        String addMiddle;
        String addLast;
        String removeFirst;
        String removeMiddle;
        String removeLast;
        String getByIndex;

        Result(String name) {
            this.name = name;
        }
    }
}