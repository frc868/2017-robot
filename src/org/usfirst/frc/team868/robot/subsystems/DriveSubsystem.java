package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.ArcadeDriveCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.HoundMath;
import lib.util.RecordMotorMovement;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private Spark leftMotor;
	private Spark rightMotor;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private final boolean DEBUG = true;
	
	/**
	 * Constructor, provides the port values for motors and encoders,
	 * and inverts any motors that need inversion.
	 */
	public DriveSubsystem(){
		leftMotor = new Spark(RobotMap.Drive.LEFT_MOTOR);
		rightMotor = new Spark(RobotMap.Drive.RIGHT_MOTOR);
		leftMotor.setInverted(RobotMap.Drive.LEFT_IS_INVERTED);
		rightMotor.setInverted(RobotMap.Drive.RIGHT_IS_INVERTED);
		leftEncoder = new Encoder(RobotMap.Drive.ENCODER_L_A, RobotMap.Drive.ENCODER_L_B);
		rightEncoder = new Encoder(RobotMap.Drive.ENCODER_R_A, RobotMap.Drive.ENCODER_R_B);
		leftEncoder.setDistancePerPulse(RobotMap.Drive.CM_PER_COUNT);
		rightEncoder.setDistancePerPulse(RobotMap.Drive.CM_PER_COUNT);
		
		// Assign test mode group
		LiveWindow.addActuator("Drive", "Left Motors", leftMotor);
		LiveWindow.addActuator("Drive", "Right Motors", rightMotor);
		LiveWindow.addSensor("Drive", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive", "Right Encoder", rightEncoder);
		
		if(DEBUG){
			SmartDashboard.putData("Drive subsystem", this);
		}
	}
	
	/**
	 * Sets power to left motors
	 * @param speed 1 to -1
	 */
	public void setL(double speed){
		leftMotor.set(HoundMath.checkRange(speed));
	}
	
	/**
	 * Sets power to right motors
	 * @param speed 1 to -1
	 */
	public void setR(double speed){
		rightMotor.set(HoundMath.checkRange(speed));
	}
	
	/**
	 * Sets power to both motors
	 * @param speed 1 to -1
	 */
	public void setSpeed(double speed) {
		setR(speed);
		setL(speed);
	}
	
	/**
	 * Sets power to both motors 
	 * @param leftSpeed 1 to -1
	 * @param RightSpeed 1 to -1
	 */
	public void setSpeed(double leftSpeed, double rightSpeed) {
		setL(leftSpeed);
		setR(rightSpeed);
	}
		

	/**
	 * @return speed of the left side of drive train.
	 */
	public double getLSpeed(){
		return leftEncoder.getRate();
	}
	
	/**
	 * Gets speed of right drivetrain
	 * @return cm per second
	 */
	public double getRSpeed(){
		return rightEncoder.getRate();
	}
	
	/**
	 * Gets average speed of drivetrain
	 * @return cm per second
	 */
	public double getAvgSpeed(){
		return (getRSpeed()+getLSpeed())/2;
	}
	
	/**
	 * Gets counts from right encoder
	 * @return counts
	 */
	public int getRightEncoder() {
		return rightEncoder.get();
	}
	
	/**
	 * Gets counts from left encoder
	 * @return counts
	 */
	public int getLeftEncoder() {
		return leftEncoder.get();
	}
	
	/**
	 * Gets distance traveled by right encoder
	 * @return cm
	 */
	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}
	
	/**
	 * Gets distance traveled by left encoder
	 * @return cm
	 */
	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
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
		int count = 0;
		if(RobotMap.Drive.LEFT_IS_INVERTED){
			count -= getLeftEncoder();
		}else{
			count += getLeftEncoder();
		}
		if(RobotMap.Drive.RIGHT_IS_INVERTED){
			count -= getRightEncoder();
		}else{
			count += getRightEncoder();
		}
		return count/2;
	}
	/**
	 * Attempts to record the movement of the motors.
	 * See class: RecordMotorMovement, and see method: RecordMotors
	 */
	public void recordMotor(){
		RecordMotorMovement.getInstance().RecordMotors();
	}
	
	public void updateSD(){
		SmartDashboard.putNumber("Left Motor Speed", getLSpeed());
		SmartDashboard.putNumber("Right Motor Speed", getRSpeed());
		if(DEBUG){
			SmartDashboard.putNumber("Left Drive Counts", getLeftEncoder());
			SmartDashboard.putNumber("Right Drive Counts", getRightEncoder());
			SmartDashboard.putNumber("Left Motor Power", leftMotor.get());
			SmartDashboard.putNumber("Right Motor Power", rightMotor.get());
			SmartDashboard.putNumber("Left Drive Distance", getLeftEncoderDistance());
			SmartDashboard.putNumber("Right Drive Distance", getRightEncoderDistance());
		}
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDriveCommand(OI.getInstance().getDriver()));
    }
}

