package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface RobotMap {
	
	public interface Drive {
		final int LEFT_MOTOR = 4;
		final int RIGHT_MOTOR = 5;
		final boolean LEFT_IS_INVERTED = false;
		final boolean RIGHT_IS_INVERTED = false;
		//6 drive train motors, using Sparks
		final double SPEED = 0.8; //default drive speed
		final double COUNTS_PER_CM = 1; //TODO calculate
		final int ENCODER_L_A = 5;
		final int ENCODER_L_B = 6;
		final int ENCODER_R_A = 7;
		final int ENCODER_R_B = 8;
	}
	
	public interface Shoot {
		final int SHOOTER_MOTOR = 8;
		final boolean IS_INVERTED = false;
		final double SHOOTER_POWER = 0.8;
		final int ENCODER_A = 3;
		final int ENCODER_B = 4;
		//2 shooter motors, using CANTalons
	}
	
	public interface Turret {
		final int TURRET_MOTOR = 9;
		final boolean IS_INVERTED = false;
		final int ENCODER_A = 1;
		final int ENCODER_B = 2;
		final int FORWARD_LIMIT = 180;
		final int REVERSE_LIMIT = -90;
		//1 turret motor, using a CAN speed controller from mindstorm?
	}
	
	public interface Feeder {
		final int AGITATOR_MOTOR = 6;
		final boolean AGITATOR_IS_INVERTED = false;
		final double AGITATOR_SPEED = 0.8;
		
		final int CONVEYOR_MOTOR = 7;
		final boolean CONVEYOR_IS_INVERTED = false;
		final double CONVEYOR_SPEED = 0.8;
		//1 agitator motor, using a Spark
		//1 conveyor belt motor, using a Spark
	}
	
	public interface Climber {
		final int CLIMBER_MOTOR = 10;
		final boolean IS_INVERTED = false;
		//1 climber motor, using a CANTalon
	}
	
	public interface GearCollector {
		final int GEAR_SOLENOID = 0;

		//2 gear collector solenoids
	}
	
	public interface Pixy {
		final int CAM_WIDTH = 318; //in pixels
		final int CAM_HEIGHT = 198;//in pixels
		final int CAM_X_ANGLE = 80;//in degrees
		final int CAM_Y_ANGLE = 40;//in degrees
		final int BAUDRATE = 19200;//
		
		/**
		 * 0 = Serial MXP, 1 = I2C Onboard, 2 = I2C MXP
		 */
		final int IR_PORT_TYPE = 0;
		final int COLOR_PORT_TYPE = 1;
		
		final int IR_I2C_VALUE = 0x5;
		final int COLOR_I2C_VALUE = 0x5;
	}
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	public static class Joystick {
		public static final int PORT_NUMBER = 0;
		public static final int LEFT_Y = 1;
		public static final int RIGHT_Y = 5;
		public static final int LEFT_X = 0;
		public static final int RIGHT_X = 4;
	}
	
	public interface LIDAR {
		final SerialPort.Port PORT = SerialPort.Port.kUSB;
		final int BAUD = 9600;
	}
}
