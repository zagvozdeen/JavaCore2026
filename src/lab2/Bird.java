package lab2;

public class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public void fly() {
        if (canFly) {
            System.out.println(getName() + " летает.");
        } else {
            System.out.println(getName() + " не умеет летать.");
        }
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " говорит: Чирик!");
    }
}
