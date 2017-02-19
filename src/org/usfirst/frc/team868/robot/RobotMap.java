package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.SerialPort;
import lib.hid.ControllerMap;
import lib.hid.DPadButton;

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
		final boolean IS_INVERTED = false;
		final double CLIMBER_SPEED = 0.8;
	}
	
	public interface Drive {
		//PORTS:
		final int LEFT_MOTOR = 9;
		final int RIGHT_MOTOR = 8;
		final int ENCODER_L_A = 2;
		final int ENCODER_L_B = 4;
		final int ENCODER_R_A = 0;
		final int ENCODER_R_B = 1;
		
		//VALUES:
		final boolean LEFT_IS_INVERTED = false;
		final boolean RIGHT_IS_INVERTED = true;
		final double SPEED = 0.8; //default drive speed
		final double COUNTS_PER_CM = 1; //TODO calculate
		final double CM_PER_COUNT = 1;
	}
	
	public interface Feeder {
		//PORTS:
		final int AGITATOR_MOTOR = 6;
		final int CONVEYOR_MOTOR = 7;
		final int BEAM_BREAK_PORT = 8;
		
		//VALUES:
		final boolean AGITATOR_IS_INVERTED = false;
		final double AGITATOR_SPEED = 0.8;
		
		final boolean CONVEYOR_IS_INVERTED = false;
		final double CONVEYOR_SPEED = 0.8;
	}
	
	public interface Flashlight {
		//PORTS:
		final int GEAR_LIGHT_PORT = 1;
		final int SHOOT_LIGHT_PORT = 0;
	}
	
	public interface GearCollector {
		//PORTS:
		final int GEAR_SOLENOID = 0;
		final int GEAR_DETECTOR_ANALOG_PORT = 0;
		
		//VALUES:
		final double DETECTION_VOLTAGE = .1;
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
		
		//random, useless comment
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
	
	public interface Shoot { //TODO fix!
		//PORTS:
		final int RIGHT_SHOOTER_MOTOR = 12;
		final int LEFT_SHOOTER_MOTOR = 9;
		final int ENCODER_A = 9;
		/**@deprecated*/
		final int ENCODER_B = 4; //TODO remove
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final double SHOOTER_POWER = 0.8;
	}
	
	public interface Turret {
		//PORTS:
		final int TURRET_MOTOR = 10;
		
		//VALUES:
		final boolean IS_INVERTED = true;
		final int FORWARD_LIMIT = 180; //TODO determine soft limit values
		final int REVERSE_LIMIT = -90;
		final double COUNTS_PER_DEGREE = 1;
		final double DEGREES_PER_COUNT = 1/COUNTS_PER_DEGREE;
	}
	
	public interface Controls {
		public interface Turret {
			final int SHOOT = ControllerMap.Key.X;
			final int R_FORWARD = DPadButton.Direction.UP;
			final int R_BACKWARD = DPadButton.Direction.DOWN;
			final int R_LEFT = DPadButton.Direction.LEFT;
			final int R_RIGHT = DPadButton.Direction.RIGHT;
			final int R_PIXY = ControllerMap.Key.B;
			final int CALIBRATE = ControllerMap.Key.LB;
			final int AGITATOR = ControllerMap.Key.RT;
		}
		
		public interface Gear {
			final int TOGGLE_COLLECTOR = ControllerMap.Key.A;
			final int TOGGLE_FLASHLIGHT = ControllerMap.Key.Y;
		}

		double MIN_JOYSTICK_VAL = 0.15;
		public interface Climber {
			final int CLIMB = ControllerMap.Key.RB;
		}
	}
}
