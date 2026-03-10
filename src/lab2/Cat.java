package lab2;

public class Cat extends Animal {
    private String foodType;

    public Cat(String name, int age, String foodType) {
        super(name, age);
        this.foodType = foodType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void playWithBall() {
        System.out.println(getName() + " играет с клубком.");
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " говорит: Мяу!");
    }
}
