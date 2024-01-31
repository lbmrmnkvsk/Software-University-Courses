package P02_01_Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    @Override
    public void calculatePerimeter() {
        double perimeter = 2 * (this.height + this.width);
        super.setPerimeter(perimeter);
    }

    @Override
    public void calculateArea() {
        double area = this.height * this.width;
        super.setArea(area);
    }
}
