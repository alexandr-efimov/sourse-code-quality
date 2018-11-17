package software.sigma.lecture.object;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

interface Shape {
    double area();
}

@AllArgsConstructor
class Square implements Shape {
    private double side;

    public double area() {
        return side * side;
    }
}

@AllArgsConstructor
class Rectangle implements Shape {
    private double height;
    private double width;

    public double area() {
        return height * width;
    }
}

@AllArgsConstructor
class Circle implements Shape {
    private static final double PI = 3.141592653589793;
    private double radius;

    public double area() {
        return PI * radius * radius;
    }
}

public class ObjectSample {

    public static void main(String[] args) {
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(10, 20);

        List<Shape> shapes = Arrays.asList(circle, rectangle);
        shapes.forEach(shape -> System.out.println("Area: " + shape.area()));
    }
}




