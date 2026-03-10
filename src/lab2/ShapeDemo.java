package lab2;

public class ShapeDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape square = new Square(4);
        Shape triangle = new Triangle(3, 4, 5);

        printShapeInfo(circle);
        printShapeInfo(square);
        printShapeInfo(triangle);

        System.out.println("Диаметр круга: " + ((Circle) circle).getDiameter());
        System.out.println("Диагональ квадрата: " + ((Square) square).getDiagonal());
        System.out.println("Треугольник прямоугольный: " + ((Triangle) triangle).isRightTriangle());
    }

    private static void printShapeInfo(Shape shape) {
        System.out.println(shape.getShapeName());
        System.out.println("Площадь: " + shape.calculateArea());
        System.out.println("Периметр: " + shape.calculatePerimeter());
        System.out.println();
    }
}
