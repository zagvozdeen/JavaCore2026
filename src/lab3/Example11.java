package lab3;

// Создать два проекта, в которых продемонстрировать два способа создания линейного
// однонаправленного списка (с головы и с хвоста) согласно примеру 2 из второго раздела.
// Отработать код с помощью отладчика и привести скриншоты минимум трех точек,
// обработанных отладчиком.
public class Example11 {
    public static void main(String[] args) {
        Node headFromHead = createFromHead();
        Node headFromTail = createFromTail();

        System.out.println("Список, созданный с головы:");
        printList(headFromHead);

        System.out.println("Список, созданный с хвоста:");
        printList(headFromTail);
    }

    // Создание списка с головы
    public static Node createFromHead() {
        Node head = null; // начальное значение ссылки на голову

        for (int i = 9; i >= 0; i--) {
            head = new Node(i, head);
        }

        return head;
    }

    // Создание списка с хвоста
    public static Node createFromTail() {
        Node head = null;
        Node tail = null;

        for (int i = 0; i <= 9; i++) {
            Node newNode = new Node(i, null);

            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }

            tail = newNode;
        }

        return head;
    }

    // Вывод списка
    public static void printList(Node head) {
        Node ref = head;

        while (ref != null) {
            System.out.print(ref.value + " ");
            ref = ref.next;
        }

        System.out.println();
    }
}