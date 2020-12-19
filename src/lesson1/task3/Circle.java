package lesson1.task3;

public class Circle extends Shape{

    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI*radius*radius;
    }
}
