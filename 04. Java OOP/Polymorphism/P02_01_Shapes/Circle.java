package P02_01_Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    public void calculatePerimeter() {
        double perimeter = 2 * Math.PI * this.radius;
        super.setPerimeter(perimeter);
    }

    @Override
    public void calculateArea() {
        double area = Math.PI * this.radius * this.radius;
        super.setArea(area);
    }
}
