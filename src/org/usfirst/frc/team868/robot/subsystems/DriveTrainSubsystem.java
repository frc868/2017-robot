package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
	private Talon RIGHT;
	private Talon LEFT;
	private static DriveTrainSubsystem instance;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public DriveTrainSubsystem(){
    	RIGHT = new Talon(RobotMap.PWM_DRIVE_RIGHT);
    	LEFT = new Talon(RobotMap.PWM_DRIVE_LEFT);
    }
    public void setRightSpeed(double RIGHTSpeed){
    	RIGHT.set(rangeCheck(RIGHTSpeed));
    }
    
    public void setLeftSpeed(double LEFTSpeed){
    	LEFT.set(rangeCheck(LEFTSpeed));
    }
    
    public double rangeCheck(double value){
    	if(value > 1){
    		value = 1;
    	}else if(value<-1){
    		value = -1;   		
    	}
    	return value;
    }
    public static DriveTrainSubsystem getInstance(){
    	if(instance == null){
    		instance = new DriveTrainSubsystem();
    	}
    	return instance;
    }
}

