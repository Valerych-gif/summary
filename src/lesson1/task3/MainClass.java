package lesson1.task3;

public class MainClass {

    public static void main(String[] args) {
        Circle circle = new Circle(5, 5, 15);
        System.out.println(circle.getArea());

        Square square = new Square(5, 5, 10, 10);
        System.out.println(square.getArea());

        Triangle triangle = new Triangle(5, 5, 10, 10);
        System.out.println(triangle.getArea());
    }
}
