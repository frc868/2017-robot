package org.usfirst.frc.team868.robot.commands.auton;

public class CalculateAnglesAndDistances {
	
	//public final static double UNKNOWN = -1;
	
	public static double getAngleFromSides(double adjacent, double opposite) {
		return (Math.atan(opposite / adjacent) * 57.2958);
	}
	
	public static double getHypotenuse(double adjacent, double opposite) {
		return (Math.pow(adjacent, 2) + Math.pow(opposite, 2));
	}
	
	public static double getAdjacentFromAngleAndOpposite(double angle, double opposite) {
		return (opposite / Math.tan(angle * 0.0174533));
	}
	
	public static double getOppositeFromAngleAndAdjacent(double angle, double adjacent) {
		return (adjacent * Math.tan(angle * 0.0174533));
	}
	
	public static double getSideFromHypotenuseAndOtherSide(double hypotenuse, double otherSide) {
		return Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(otherSide, 2));
	}
	
	/*public static double getMissingSide(double leg1, double leg2, double hypotenuse) {
		if (leg1 == UNKNOWN) {
			return getSideFromHypotenuseAndOtherSide(hypotenuse, leg2);
		} else if (leg2 == UNKNOWN) {
			return getSideFromHypotenuseAndOtherSide(hypotenuse, leg1);
		} else {
			return getHypotenuse(leg1, leg2);
		}
	}
	*/
}
