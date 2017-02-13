package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import randomUtils.RecordMotorMovement;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private static DriveSubsystem instance;
	private Spark leftMotor;
	private Spark rightMotor;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	/**
	 * Constructor, provides the port values for motors and encoders,
	 * and inverts any motors that need inversion.
	 */
	private DriveSubsystem(){
		leftMotor = new Spark(RobotMap.Drive.LEFT_MOTOR);
		rightMotor = new Spark(RobotMap.Drive.RIGHT_MOTOR);
		leftMotor.setInverted(RobotMap.Drive.LEFT_IS_INVERTED);
		rightMotor.setInverted(RobotMap.Drive.RIGHT_IS_INVERTED);
		leftEncoder = new Encoder(RobotMap.Drive.ENCODER_L_A, RobotMap.Drive.ENCODER_L_B);
		rightEncoder = new Encoder(RobotMap.Drive.ENCODER_R_A, RobotMap.Drive.ENCODER_R_B);
	}
	/**
	 * Sets the left motor to power.
	 * @param power
	 */
	public void setL(double power){
		leftMotor.set(power);
	}
	/**
	 * Sets the right motor to power.
	 * @param power
	 */
	public void setR(double power){
		rightMotor.set(power);
	}
	/**
	 * Sets both left and right motors to power.
	 * @param power
	 */
	public void setPower(double power) {
		setR(power);
		setL(power);
	}
	/**
	 * @return speed of the left side of drive train.
	 */
	public double getLSpeed(){
		return leftEncoder.getRate();
	}
	/**
	 * @return speed of right side of the drive train.
	 */
	public double getRSpeed(){
		return rightEncoder.getRate();
	}
	/**
	 * @return the average speed of the drive train.
	 */
	public double getAvgSpeed(){
		return (getRSpeed()+getLSpeed())/2;
	}
	/**
	 * @return the number of counts on the right encoder.
	 */
	public int getRightEncoder() {
		return rightEncoder.get();
	}
	/**
	 * @return the number of counts on the left encoder.
	 */
	public int getLeftEncoder() {
		return leftEncoder.get();
	}
	/**
	 * Resets the counts on both encoders.
	 */
	public void resetEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	/**
	 * @return the average number of counts on both encoders.
	 */
	public int getAvgEncoders() {
		return (getRightEncoder() + getLeftEncoder()) / 2;
	}
	/**
	 * Attempts to record the movement of the motors.
	 * See class: RecordMotorMovement, and see method: RecordMotors
	 */
	public void recordMotor(){
		RecordMotorMovement.getInstance().RecordMotors();
	}

	/**
	 * Gets the subsystem instance
	 * 
	 * @return subsystem instance
	 */
	public static DriveSubsystem getInstance(){
		if(instance == null){
			instance = new DriveSubsystem();
		}
		return instance;
	}
	
	public void updateSD(){
		SmartDashboard.putNumber("Left Motor Speed", getLSpeed());
		SmartDashboard.putNumber("Right Motor Speed", getRSpeed());
	}

    public void initDefaultCommand() {
    }
}

