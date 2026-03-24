package lab3;

// Разработать проект, в котором для ввода, вывода и изменения односвязного линейного списка создать следующие методы:
//
// а) с использованием цикла:
//
// 1) ввод с головы createHead();
// 2) ввод с хвоста createTail();
// 3) вывод (возвращается строка, сформированная из элементов списка) toString();
// 4) добавление элемента в начало списка AddFirst();
// 5) добавление элемента в конец списка AddLast();
// 6) вставка элемента в список с указанным номером Insert();
// 7) удаление элемента с головы списка RemoveFirst();
// 8) удаление последнего элемента списка RemoveLast();
// 9) удаление из списка элемента с указанным номером Remove();
//
// б) с использованием рекурсии:
//
// 1) ввод с головы createHeadRec();
// 2) ввод с хвоста createTailRec();
// 3) вывод (возвращается строка, сформированная из элементов списка) toStringRec().

public class Example12 {
    static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    static class SingleLinkedList {
        private Node head;

        public void clear() {
            head = null;
        }

        // а) С использованием цикла

        // 1) ввод с головы
        public void createHead(int[] values) {
            head = null;
            for (int i = 0; i < values.length; i++) {
                head = new Node(values[i], head);
            }
        }

        // 2) ввод с хвоста
        public void createTail(int[] values) {
            head = null;
            Node tail = null;

            for (int i = 0; i < values.length; i++) {
                Node newNode = new Node(values[i], null);

                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
            }
        }

        // 3) вывод
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node ref = head;

            while (ref != null) {
                sb.append(ref.value);
                if (ref.next != null) {
                    sb.append(" ");
                }
                ref = ref.next;
            }

            return sb.toString();
        }

        // 4) добавление элемента в начало списка
        public void AddFirst(int value) {
            head = new Node(value, head);
        }

        // 5) добавление элемента в конец списка
        public void AddLast(int value) {
            Node newNode = new Node(value, null);

            if (head == null) {
                head = newNode;
                return;
            }

            Node ref = head;
            while (ref.next != null) {
                ref = ref.next;
            }
            ref.next = newNode;
        }

        // 6) вставка элемента в список с указанным номером
        public void Insert(int index, int value) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Неверный индекс");
            }

            if (index == 0) {
                AddFirst(value);
                return;
            }

            Node ref = head;
            int k = 0;

            while (ref != null && k < index - 1) {
                ref = ref.next;
                k++;
            }

            if (ref == null) {
                throw new IndexOutOfBoundsException("Неверный индекс");
            }

            ref.next = new Node(value, ref.next);
        }

        // 7) удаление элемента с головы списка
        public int RemoveFirst() {
            if (head == null) {
                throw new IllegalStateException("Список пуст");
            }

            int value = head.value;
            head = head.next;
            return value;
        }

        // 8) удаление последнего элемента списка
        public int RemoveLast() {
            if (head == null) {
                throw new IllegalStateException("Список пуст");
            }

            if (head.next == null) {
                int value = head.value;
                head = null;
                return value;
            }

            Node ref = head;
            while (ref.next.next != null) {
                ref = ref.next;
            }

            int value = ref.next.value;
            ref.next = null;
            return value;
        }

        // 9) удаление из списка элемента с указанным номером
        public int Remove(int index) {
            if (index < 0 || head == null) {
                throw new IndexOutOfBoundsException("Неверный индекс");
            }

            if (index == 0) {
                return RemoveFirst();
            }

            Node ref = head;
            int k = 0;

            while (ref.next != null && k < index - 1) {
                ref = ref.next;
                k++;
            }

            if (ref.next == null) {
                throw new IndexOutOfBoundsException("Неверный индекс");
            }

            int value = ref.next.value;
            ref.next = ref.next.next;
            return value;
        }

        // б) С использованием рекурсии

        // 1) ввод с головы
        public void createHeadRec(int[] values) {
            head = null;
            createHeadRec(values, 0);
        }

        private void createHeadRec(int[] values, int index) {
            if (index >= values.length) {
                return;
            }
            head = new Node(values[index], head);
            createHeadRec(values, index + 1);
        }

        // 2) ввод с хвоста
        public void createTailRec(int[] values) {
            head = createTailRec(values, 0);
        }

        private Node createTailRec(int[] values, int index) {
            if (index >= values.length) {
                return null;
            }
            return new Node(values[index], createTailRec(values, index + 1));
        }

        // 3) вывод
        public String toStringRec() {
            return toStringRec(head).trim();
        }

        private String toStringRec(Node node) {
            if (node == null) {
                return "";
            }
            return node.value + " " + toStringRec(node.next);
        }
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        int[] values = {1, 2, 3, 4, 5};

        System.out.println("Создание списка с головы (цикл):");
        list.createHead(values);
        System.out.println(list.toString());

        System.out.println("Создание списка с хвоста (цикл):");
        list.createTail(values);
        System.out.println(list.toString());

        System.out.println("Добавить в начало 100:");
        list.AddFirst(100);
        System.out.println(list.toString());

        System.out.println("Добавить в конец 200:");
        list.AddLast(200);
        System.out.println(list.toString());

        System.out.println("Вставить 300 по индексу 3:");
        list.Insert(3, 300);
        System.out.println(list.toString());

        System.out.println("Удалён первый элемент: " + list.RemoveFirst());
        System.out.println(list.toString());

        System.out.println("Удалён последний элемент: " + list.RemoveLast());
        System.out.println(list.toString());

        System.out.println("Удалён элемент по индексу 2: " + list.Remove(2));
        System.out.println(list.toString());

        System.out.println("Создание списка с головы (рекурсия):");
        list.createHeadRec(values);
        System.out.println(list.toStringRec());

        System.out.println("Создание списка с хвоста (рекурсия):");
        list.createTailRec(values);
        System.out.println(list.toStringRec());
    }
}