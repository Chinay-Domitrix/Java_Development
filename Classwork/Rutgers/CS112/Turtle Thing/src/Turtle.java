import java.awt.*;

import static edu.princeton.cs.algs4.StdDraw.line;
import static edu.princeton.cs.algs4.StdDraw.setPenColor;
import static edu.princeton.cs.algs4.StdOut.println;
import static java.awt.Color.*;
import static java.lang.Math.*;

public class Turtle {

	// Abstract Data Type (ADT) Turtle

	//****** The instance of a class is an object  *****/

	// Instance variables have unique values to each object (instance of the class)
	// Private modifier hides the instance variable from other classes
	// (x,y) coordinate
	private double x;
	private double y;

	// direction in which the turtle is facing
	private double angle;

	// turtle color
	private Color color;

	//******* Constructors *******/
	// Constructors have the same of the same name of the class
	// Constructors create and initialize the object (initializing the instance variables)

	// Default constructor: no argument constructor
	public Turtle() {
		this(0.0, 0.0, 90.0, BLACK);
	}

	// 4 argument constructor
	public Turtle(double x, double y, double angle, Color color) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.color = color;
	}

	// Instance methods of the class Turtle
	// Instance methods DO NOT HAVE THE WORD static
	// Instance methods manipulate (have access) to instance variables

	// toString() method returns the string representation of an object
	@Override
	public String toString() {
		return "Turtle (%s, %s) direction %s %s".formatted(x, y, angle, color.toString());
	}

	// turnLeft by updating the turtle angle
	public void turnLeft(double delta) {
		angle += delta;
	}

	// moves the turtle forward by stepSize
	// draw a line from the old x,y to the new x,y
	public void moveForward(double stepSize) {
		double oldX = x;
		double oldY = y;
		x += stepSize * cos(toRadians(angle));
		y += stepSize * sin(toRadians(angle));
		setPenColor(color);
		line(oldX, oldY, x, y);
	}

	// accessor methods
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAngle() {
		return angle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		// update the turtle color
		this.color = color;
	}

	// equals method to compare this object with another one
	// Object class is a class in Java, all classes are derived from Object
	@Override
	public boolean equals(Object other) {
		return (other instanceof Turtle o) && (this.x == o.x) && (this.y == o.y) && (this.angle == o.angle) && this.color.equals(o.color); // cannot compare colors like this: this.color == o.color
	}

	// static methods do not have access to any instance variables or methods
	public static void main(String[] args) {
		Turtle t1 = new Turtle(), t2 = new Turtle(0.5, 0.5, 45, new Color(0, 255, 0));
		println(t1);
		println(t2);
		println(t1.equals(t2));
		t2.moveForward(0.1);
		println(t2);
	}
}
