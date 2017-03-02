package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.auton.CalculateAnglesAndDistances;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface RobotMap {
	
	public interface JoystickPort {
		// PORTS:
		final int DRIVER = 0;
		final int OPERATOR = 1;
	}
	
	public interface Climber {
		//PORTS:
		final int CLIMBER_MOTOR = 11;
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final double CLIMBER_SPEED = 1;
	}
	
	public interface Drive {
		//PORTS:
		final int LEFT_MOTOR = 9;
		final int RIGHT_MOTOR = 8;
		final int ENCODER_L_A = 0;
		final int ENCODER_L_B = 1;
		final int ENCODER_R_A = 2;
		final int ENCODER_R_B = 3;
		
		//VALUES:
		final boolean LEFT_IS_INVERTED = false;
		final boolean RIGHT_IS_INVERTED = true;
		final double SPEED = 0.8; //default drive speed
		final double COUNTS_PER_CM = 70;
		final double CM_PER_COUNT = 1 / COUNTS_PER_CM;
		final double MIN_DRIVE_SPEED = .27;
	}
	
	public interface Feeder {
		//PORTS:
		final int AGITATOR_MOTOR = 6;
		final int CONVEYOR_MOTOR = 7;
		final int BEAM_BREAK_PORT = 8;
		
		//VALUES:
		final boolean AGITATOR_IS_INVERTED = false;
		final double AGITATOR_SPEED = 0.4;
		final double SHAKE_PERIOD = 1.0;
		
		final boolean CONVEYOR_IS_INVERTED = false;
		final double CONVEYOR_SPEED = 1;
		
		final double SHOOTER_SPEED_THREASHOLD = .5; //minimum speed to allow feeder to run forward
		final double MIN_SHOOT_SPEED = 30;
	}
	
	public interface Flashlight {
		//PORTS:
		final int GEAR_LIGHT_PORT = 1;
		final int SHOOT_LIGHT_PORT = 0;
	}
	
	public interface GearCollector {
		//PORTS:
		final int GEAR_SOLENOID_CLOSER = 1;
		final int GEAR_SOLENOID_OPENER = 0;
		final int GEAR_DETECTOR_ANALOG_PORT = 3;
		
		//VALUES:
		final double DETECTION_VOLTAGE = 1.5;
	}
	
	public static class Joystick {
		//PORTS:
		public static final int PORT_NUMBER = 0;
		public static final int LEFT_Y = 1;
		public static final int RIGHT_Y = 5;
		public static final int LEFT_X = 0;
		public static final int RIGHT_X = 4;
	}
	
	public interface LEDs {
		//PORTS:
		final int DIO_1_PORT = 5;
		final int DIO_2_PORT = 6;
		final int DIO_GEAR_PORT = 7;
	}
	
	public interface LIDAR {
		//PORTS:
		final SerialPort.Port PORT = SerialPort.Port.kUSB;
		
		//VALUES:
		final int BAUD = 9600;
	}
	
	public interface Pixy {
		//PORTS:
		final SerialPortType IR_PORT_TYPE = SerialPortType.SERIAL_MXP;
		final SerialPortType COLOR_PORT_TYPE = SerialPortType.I2C_ONBOARD;
		final int IR_I2C_VALUE = 0x5; // Only needed if using I2C
		final int COLOR_I2C_VALUE = 0x5;
		
		//VALUES:
		final int CAM_WIDTH = 318; //in pixels
		final int CAM_HEIGHT = 198;//in pixels
		final int CAM_X_ANGLE = 80;//in degrees
		final int CAM_Y_ANGLE = 40;//in degrees
		final int BAUDRATE = 19200;
	}
	
	/** Serial port types that make it so that serial objects only need their port value changed in RobotMap */
	public static enum SerialPortType{
		SERIAL_ONBOARD, SERIAL_MXP, I2C_ONBOARD, I2C_MXP
	}
	
	public interface Shoot {
		//PORTS:
		final int RIGHT_SHOOTER_MOTOR = 12;
		final int LEFT_SHOOTER_MOTOR = 9;
		final int ENCODER_A = 9;
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final double SHOOTER_POWER = 0.8;
		final double TARGET_SHOOTER_SPEED = 80;
		final double ROTATIONS_PER_COUNT = 1.0;
	}
	
	public interface Turret {
		//PORTS:
		final int TURRET_MOTOR = 10;
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final double SOFT_LIMIT_OFFSET = 2;
		final double COUNTS_PER_DEGREE = 1;
		final double DEGREES_PER_COUNT = 1/COUNTS_PER_DEGREE;
		final double MIN_VOLTAGE = 2.2;
		final double MAX_VOLTAGE = 6;
		final double RAMP_RATE = 1;
	}
	public interface AutonValues {
		
		//Baseline from Start
		double distanceToCrossBaselineFromPos1orPos3 = 300 - 91;
		double distanceToCrossBaselineFromPos2 = CalculateAnglesAndDistances.getHypotenuse(distanceToCrossBaselineFromPos1orPos3, distanceToCrossBaselineFromPos1orPos3);
		
		//Gear from Start
		double distanceToGearFromPos2 = 237 - 27 - 91;
		double distanceToGearBeforeAngleFromPos1orPos3 = 237 - 91; //NOT ACCURATE
		double angleToGearFromPos1orPos3 = 60; //angle of the triangle made w hypotenuse as robot to gear thing
		double adjacentSideOfTriangleForGearFromPos1orPos3 = 50.5; //NOT ACCURATE, must figure out distance from position 1 x value to gear lift x value 
		double distanceToGearAfterAngleFromPos1orPos3 = CalculateAnglesAndDistances.getHypotenuse(adjacentSideOfTriangleForGearFromPos1orPos3, CalculateAnglesAndDistances.getOppositeFromAngleAndAdjacent(angleToGearFromPos1orPos3, adjacentSideOfTriangleForGearFromPos1orPos3));
		double distanceToBackup = 40;
		
		//Gear to Hopper
		double distanceAcrossToHopperFromGearPos2 = 411.5 - (107 /Math.sqrt(2)) - 91;
		double distanceForwardToHitHopperAfterDoingGearPos2 = 76 + 33.5 + distanceToBackup;
		double angleToTurnToHitHopperAfterDoingGearPos2 = CalculateAnglesAndDistances.getAngleFromSides(66, 107 / Math.sqrt(2));
		double distanceToBackUpToHitHopperAfterDoingGearPos2 = CalculateAnglesAndDistances.getHypotenuse(66, 107 / Math.sqrt(2));
		double distanceToBackupFromGearPos3 = CalculateAnglesAndDistances.getSideFromHypotenuseAndOtherSide(155.25, 25.25);
		double distanceToGoToHopperFromGearPos3 = 205.75 - 91 -10;
		double angleToTurnToHitHopperAfterDoingGearPos3 = CalculateAnglesAndDistances.getAngleFromSides(28, 10);
		double distanceToBackUpToHitHopperAfterDoingGearPos3 = CalculateAnglesAndDistances.getHypotenuse(28, 10);
		//Gear to Neutral
		double distanceToBackupMoreFromPos1orPos3 = 75;
		double distanceAcrossFromPos2 = 300;
		double distanceToNeutralZoneFromGear = 420;
	
		//Hopper from Start
		
		
		//Hopper to Neutral
		double distanceForwardBeforeTurning = 101;
		double distanceToNeutralFromHopper = 33.5;
		
		//Hopper to Gear
		
	}
}
