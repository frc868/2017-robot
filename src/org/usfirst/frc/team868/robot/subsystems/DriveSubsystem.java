package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	private static DriveSubsystem instance;
	private Spark leftMotor;
	private Spark rightMotor;
	
	private DriveSubsystem(){
		leftMotor = new Spark(RobotMap.Drive.LEFT_MOTOR);
		rightMotor = new Spark(RobotMap.Drive.RIGHT_MOTOR);
		leftMotor.setInverted(RobotMap.Drive.LEFT_IS_INVERTED);
		rightMotor.setInverted(RobotMap.Drive.RIGHT_IS_INVERTED);
	}
	
	public void setL(double speed){
		leftMotor.set(speed);
	}
	
	public void setR(double speed){
		rightMotor.set(speed);
	}
	
	public double getLSpeed(){
		return leftMotor.getSpeed();
	}
	
	public double getRSpeed(){
		return rightMotor.getSpeed();
	}
	
	public double getAvgSpeed(){
		return (getRSpeed()+getLSpeed())/2;
	}
	
	public void recordMotors(double speedL, double speedR){
		
	}
	
	public static DriveSubsystem getInstance(){
		if(instance == null){
			instance = new DriveSubsystem();
		}
		return instance;
	}
	
	public void updateSD(){
		SmartDashboard.putNumber("Left Motor Speed", getLSpeed());
		SmartDashboard.putNumber("Right Motor Speed", getRSpeed());
		SmartDashboard.putString("write/read for motor record", null);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

