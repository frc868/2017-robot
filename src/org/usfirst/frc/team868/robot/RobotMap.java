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
		//PORTS:
		final int LEFT_MOTOR = 9;
		final int RIGHT_MOTOR = 8;
		final int ENCODER_L_A = 5;
		final int ENCODER_L_B = 6;
		final int ENCODER_R_A = 7;
		final int ENCODER_R_B = 8;
		
		//VALUES:
		final boolean LEFT_IS_INVERTED = false;
		final boolean RIGHT_IS_INVERTED = false;
		final double SPEED = 0.8; //default drive speed
		final double COUNTS_PER_CM = 1; //TODO calculate
	}
	
	public interface Shoot {
		//PORTS:
		final int SHOOTER_MOTOR = 8;
		final int ENCODER_A = 3;
		final int ENCODER_B = 4;
		
		//VALUES:
		final boolean IS_INVERTED = false;
		final double SHOOTER_POWER = 0.8;
	}
	
	public interface Turret {
		//PORTS:
		final int TURRET_MOTOR = 9;
		final int ENCODER_A = 1;
		final int ENCODER_B = 2;
		
		//VALUES:
		final boolean IS_INVERTED = false;
		final int FORWARD_LIMIT = 180; //TODO determine soft limit values
		final int REVERSE_LIMIT = -90;
		final double COUNTS_PER_DEGREE = 1;
	}
	
	public interface Feeder {
		//PORTS:
		final int AGITATOR_MOTOR = 7;
		
		final int CONVEYOR_MOTOR = 6;
		
		//VALUES:
		final boolean AGITATOR_IS_INVERTED = false;
		final double AGITATOR_SPEED = 0.8;
		
		final boolean CONVEYOR_IS_INVERTED = false;
		final double CONVEYOR_SPEED = 0.8;
	}
	
	public interface Climber {
		//PORTS:
		final int CLIMBER_MOTOR = 10;
		
		//VALUES:
		final boolean IS_INVERTED = false;
		final double CLIMBER_SPEED = 0.8;
	}
	
	public interface GearCollector {
		//PORTS:
		final int GEAR_SOLENOID = 0;
	}
	
	public interface Pixy {
		//PORTS:
		/** 0 = Serial MXP, 1 = I2C Onboard, 2 = I2C MXP */
		final int IR_PORT_TYPE = 0;
		final int COLOR_PORT_TYPE = 1;
		final int IR_I2C_VALUE = 0x5; // Only needed if using I2C
		final int COLOR_I2C_VALUE = 0x5;
		
		//VALUES:
		final int CAM_WIDTH = 318; //in pixels
		final int CAM_HEIGHT = 198;//in pixels
		final int CAM_X_ANGLE = 80;//in degrees
		final int CAM_Y_ANGLE = 40;//in degrees
		final int BAUDRATE = 19200;
	}
	
	public static class Joystick {
		//PORTS:
		public static final int PORT_NUMBER = 0;
		public static final int LEFT_Y = 1;
		public static final int RIGHT_Y = 5;
		public static final int LEFT_X = 0;
		public static final int RIGHT_X = 4;
	}
	
	public interface LIDAR {
		//PORTS:
		final SerialPort.Port PORT = SerialPort.Port.kUSB;
		
		//VALUES:
		final int BAUD = 9600;
	}
	
	public interface Flashlight {
		final int GEAR_LIGHT_PORT = 1;
		final int SHOOT_LIGHT_PORT = 0;
	}
}
