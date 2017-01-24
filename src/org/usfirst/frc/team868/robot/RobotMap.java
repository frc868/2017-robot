package org.usfirst.frc.team868.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
	
	public interface Drive{
		final int LEFT_MOTOR = 0;
		final int RIGHT_MOTOR = 1;
		//6 drive train motors, using Sparks
	}
	
	public interface Shoot{
		//2 shooter motors, using CANTalons
	}
	
	public interface Turret{
		//1 turret motor, using a CAN speed controller from mindstorm?
	}
	
	public interface Feeder{
		//1 agitator motor, using a Spark
		//1 conveyor belt motor, using a Spark
	}
	
	public interface Climber{
		//1 climber motor, using a CANTalon
	}
	
	public interface GearCollector{
		//2 gear collector motors, using CANTalons
	}
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
