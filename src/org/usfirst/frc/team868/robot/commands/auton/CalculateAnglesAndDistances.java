package org.usfirst.frc.team868.robot.commands.auton;

public class CalculateAnglesAndDistances {
	private double oppositeSide;
	private double adjacentSide;
	private double hypotenuse;
	private double angle;
	
	public CalculateAnglesAndDistances(double adjacent, double opposite) {
		adjacentSide = adjacent;
		oppositeSide = opposite;
		hypotenuse = (Math.pow(adjacent, 2) + Math.pow(opposite, 2));
		angle = (Math.atan(opposite / adjacent) * 57.2958);
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getHypotenuse() {
		return hypotenuse;
	}
	
}
