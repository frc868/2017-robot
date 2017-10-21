package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.auton.CalculateGeometry;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface RobotMap {
	
	public enum State {
		FORWARD, OFF, BACKWARD;
	}
	
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
		final int ENCODER_L_A = 2;
		final int ENCODER_L_B = 3;
		final int ENCODER_R_A = 1;
		final int ENCODER_R_B = 0;
		
		//VALUES:
		final boolean LEFT_IS_INVERTED = false; //Not sure if these affect encoders & distances for auton
		final boolean RIGHT_IS_INVERTED = true;
		final double SPEED = 0.8; //default drive speed
		final double COUNTS_PER_CM = 9925.0 / 140.0; //70
		final double CM_PER_COUNT = 1.0 / COUNTS_PER_CM;
		final double MIN_DRIVE_SPEED = .27;
		final double MAX_AUTON_DRIVE_SPEED = .4;//.65;
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
		final double CONVEYOR_SPEED = .7;
		
		final double SHOOTER_SPEED_THREASHOLD = .5; //minimum speed to allow feeder to run forward
		final double MIN_SHOOT_SPEED = 30;
		final double SHAKE_TIME = 5.0;
	}
	
	public interface Flashlight {
		//PORTS:
		final int GEAR_LIGHT_PORT = 1;
		final int SHOOT_LIGHT_PORT = 0;
	}
	
	public interface GearCollector {
		//PORTS:
		final int GEAR_COLLECTOR_CLOSER = 6;
		final int GEAR_COLLECTOR_OPENER = 7;
		final int GEAR_DETECTOR_ANALOG_PORT = 3;
		
		//VALUES:
		final double DETECTION_VOLTAGE = 1.5;
	}
	
	public interface GearEjector {
		//PORTS:
		final int GEAR_EJECTOR_CLOSER = 1;
		final int GEAR_EJECTOR_OPENER = 0;
		
		final int GEAR_PRESSURE_PLATE = 5;
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
		final int DIO_1_PORT = 4; //TODO this port does not physically work!!
		final int DIO_2_PORT = 6;
		final int DIO_GEAR_PORT = 7;
	}
	
	public interface LIDAR {
		//PORTS:
		final SerialPort.Port PORT = SerialPort.Port.kUSB;
		
		//VALUES:
		final int BAUD = 115200;
	}
	
	public interface Pixy {
		//PORTS:
		final SerialPortType IR_PORT_TYPE = SerialPortType.SERIAL_MXP;
		final SerialPortType COLOR_PORT_TYPE = SerialPortType.I2C_MXP;
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
		final boolean IS_INVERTED = false;
		final double SHOOTER_POWER = 0.8;
		final double TARGET_SHOOTER_SPEED = 80;
		final double ROTATIONS_PER_COUNT = 1.0;
	}
	
	public interface Turret {
		//PORTS:
		final int TURRET_MOTOR = 10;
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final double SOFT_LIMIT_OFFSET = 700;
		final double COUNTS_PER_DEGREE = 100;
		final double DEGREES_PER_COUNT = 1/COUNTS_PER_DEGREE;
		final double MIN_PID_ADDITIONAL_VOLTAGE = .64;
		final double MIN_VOLTAGE = 2.3;
		final double MAX_VOLTAGE = 5;
		final double LEFT_LIMIT_TO_FORWARD = 40; //degrees
		final double RAMP_RATE = 1;
	}
	public interface AutonValues {
		
		//VALUES ARE IN CM
		
		//Baseline from Start
		double DISTANCE_TO_BASELINE = 280;// Will likely go farther than baseline
		double BASELINE_MIDDLE_DISTANCE = 190;
		
		//Gear from Start
		double WALL_TO_HOOK = BASELINE_MIDDLE_DISTANCE;//Distance from alliance wall to the middle hook.
		double GEAR_AUTON_DIST_1 = 166;//NOT ACCURATE: Distance until in line with hook if on sides.
		double GEAR_AUTON_DIST_2 = 196;
		double HOOK_BACKOFF = 45;//Arbitrary value: how far to back away from the hook after placing the gear.
		
		//Gear to Hopper
		double distanceAcrossToHopperFromGearPos2 = 411.5 - (107 /Math.sqrt(2)) - 91;
		double distanceForwardToHitHopperAfterDoingGearPos2 = 76 + 33.5 + HOOK_BACKOFF;
		double angleToTurnToHitHopperAfterDoingGearPos2 = CalculateGeometry.getAngle(66, 107 / Math.sqrt(2));
		double distanceToBackUpToHitHopperAfterDoingGearPos2 = CalculateGeometry.getHypotenuse(66, 107 / Math.sqrt(2));
		double distanceToBackupFromGearPos3 = CalculateGeometry.getSide(155.25, 25.25);
		double distanceToGoToHopperFromGearPos3 = 205.75 - 91 -10;
		double angleToTurnToHitHopperAfterDoingGearPos3 = CalculateGeometry.getAngle(28, 10);
		double distanceToBackUpToHitHopperAfterDoingGearPos3 = CalculateGeometry.getHypotenuse(28, 10);
		
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
