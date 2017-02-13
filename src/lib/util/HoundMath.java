package lib.util;

public class HoundMath {

	public static double checkRange(double value) {
		return checkRange(value, -1, 1);
	}
	
	public static double d2r(double degrees) {
		return degrees * Math.PI / 180.0;
	}

	public static double r2d(double radians) {
		return radians * 180.0 / Math.PI;
	}
	
	public static double checkRange(double value, double min, double max) {
		if(value < min)
			return min;
		else if(value > max)
			return max;
		else
			return value;
	}

	public static double boundAngleNeg180to180Degrees(double angle) {
		while (angle >= 180.0) {
			angle -= 360.0;
		}
		while (angle < -180.0) {
			angle += 360.0;
		}
		return angle;
	}

	public static double boundAngle0to360Degrees(double angle) {
		while (angle >= 360.0) {
			angle -= 360.0;
		}
		while (angle < 0.0) {
			angle += 360.0;
		}
		return angle;
	}
	
	public static double getAngleDifferenceDegrees(double from, double to) {
	    return boundAngleNeg180to180Degrees(to - from);
	}
}
