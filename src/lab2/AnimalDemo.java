package lab2;

public class AnimalDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Бим", 4, "Овчарка");
        Cat cat = new Cat("Мурка", 3, "Сухой корм");
        Bird bird = new Bird("Кеша", 2, true);

        printAnimalInfo(dog);
        dog.makeSound();
        dog.fetchStick();
        System.out.println();

        printAnimalInfo(cat);
        cat.makeSound();
        cat.playWithBall();
        System.out.println();

        printAnimalInfo(bird);
        bird.makeSound();
        bird.fly();
    }

    private static void printAnimalInfo(Animal animal) {
        System.out.println("Имя: " + animal.getName());
        System.out.println("Возраст: " + animal.getAge());
    }
}
