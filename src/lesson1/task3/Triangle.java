package lesson1.task3;

public class Triangle extends Shape{

    private int width;
    private int height;

    public Triangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    @Override
    double getArea() {
        return width*height/2.0;
    }
}
