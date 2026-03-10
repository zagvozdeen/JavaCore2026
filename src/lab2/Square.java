package lab2;

public class Square extends Shape {
    private double side;

    public Square(double side) {
        super("Квадрат");
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getDiagonal() {
        return side * Math.sqrt(2);
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
}
