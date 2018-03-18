/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;

import javax.print.attribute.ResolutionSyntax;

import java.util.ArrayList;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		if (sideLength < 0)
			throw new RuntimeException("implement me!");
		int i;
		for (i = 0; i < 4; ++i) {
			turtle.forward(sideLength);
			turtle.turn(90.0);
		}
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides
	 *            number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		// if(sides <= 2)
		// throw new RuntimeException("implement me!");
		double angle;
		angle = (sides - 2) * 180.0 / sides;
		// if(angle >= 0 && angle < 360)
		return angle;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle
	 *            size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		// throw new RuntimeException("implement me!");
		int side;
		side = (int) Math.round((360 / (180 - angle)));
		return side;
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle
	 *            the turtle context
	 * @param sides
	 *            number of sides of the polygon to draw
	 * @param sideLength
	 *            length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		// throw new RuntimeException("implement me!");
		int i = 0;
		for (i = 0; i < sides; ++i) {
			turtle.forward(sideLength);
			turtle.turn(180 - calculateRegularPolygonAngle(sides));
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the heading towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentHeading. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentHeading
	 *            current direction as clockwise from north
	 * @param currentX
	 *            current location x-coordinate
	 * @param currentY
	 *            current location y-coordinate
	 * @param targetX
	 *            target point x-coordinate
	 * @param targetY
	 *            target point y-coordinate
	 * @return adjustment to heading (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY, int targetX,
			int targetY) {
		// throw new RuntimeException("implement me!");
		int delta_x = targetX - currentX;
		int delta_y = targetY - currentY;
		if (delta_x == 0 && delta_y > 0) {
			if (currentHeading > 0)
				return 360 - currentHeading;
			else
				return 0;

		} else if (delta_x == 0 && delta_y < 0) {
			return 180 - currentHeading;
		} else if (delta_x > 0 && delta_y >= 0) {
			if (90 - currentHeading - Math.toDegrees(Math.atan((double) delta_y / (double) delta_x)) > 0)
				return (90 - currentHeading - Math.toDegrees(Math.atan((double) delta_y / (double) delta_x)));
			else
				return (360 + 90 - currentHeading - Math.toDegrees(Math.atan((double) delta_y / (double) delta_x)));
		} else if (delta_y < 0) {
			return (270 - currentHeading - Math.toDegrees(Math.atan((double) delta_y / (double) delta_x)));
		} else
			return (450 - currentHeading - Math.toDegrees(Math.atan((double) delta_y / (double) delta_x)));

	}

	/**
	 * Given a sequence of points, calculate the heading adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateHeadingToPoint() to implement this function.
	 * 
	 * @param xCoords
	 *            list of x-coordinates (must be same length as yCoords)
	 * @param yCoords
	 *            list of y-coordinates (must be same length as xCoords)
	 * @return list of heading adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
		// throw new RuntimeException("implement me!");
		int i;
		List<Double> l = new ArrayList<>();
		double degrees = 0, degree;
		for (i = 0; i < xCoords.size() - 1; ++i) {
			degree = calculateHeadingToPoint(degrees, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1),
					yCoords.get(i + 1));
			l.add(degree);
			degrees = degree;
		}
		return l;
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle
	 *            the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		// throw new RuntimeException("implement me!");
		int i, j;
		for (i = 0; i < 360; ++i) {
			turtle.turn(i);
			for (j = 0; j < 4; ++j) {
				if (j == 0)
					turtle.color(PenColor.PINK);
				else if (j == 1)
					turtle.color(PenColor.ORANGE);
				else if (j == 2)
					turtle.color(PenColor.GREEN);
				else
					turtle.color(PenColor.RED);
				turtle.forward(150);
				turtle.turn(90);
			}
		}

	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();

		//drawSquare(turtle, 40);

		
		//drawPersonalArt(turtle);
		drawRegularPolygon(turtle, 6, 60);
		turtle.draw();
	}

}
