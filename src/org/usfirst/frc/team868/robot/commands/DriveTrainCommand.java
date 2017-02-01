package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainCommand extends Command {
	private DriveTrainSubsystem drive;
	Joystick joystick = new Joystick(RobotMap.Joystick.PORT_NUMBER);
	private double fSpeed = 0;
	private double tSpeed = 0;
	
    public DriveTrainCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive = DriveTrainSubsystem.getInstance();
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(joystick.getRawAxis(RobotMap.Joystick.LEFT_X) < 0){
			tSpeed = (double) -(joystick.getRawAxis(RobotMap.Joystick.LEFT_X)*joystick.getRawAxis(RobotMap.Joystick.LEFT_X));
		}
		else if(joystick.getRawAxis(RobotMap.Joystick.LEFT_X) > 0){
			tSpeed = (double)(joystick.getRawAxis(RobotMap.Joystick.LEFT_X)*joystick.getRawAxis(RobotMap.Joystick.LEFT_X));
		}else{
			tSpeed = 0;
		}
    	if(joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y) < 0){
			fSpeed = (double) -(joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y)*joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y));
		}
		else if(joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y) > 0){
			fSpeed = (double)(joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y)*joystick.getRawAxis(RobotMap.Joystick.RIGHT_Y));
		}else{
			fSpeed = 0;
		}
    	drive.setLeftSpeed(-tSpeed+fSpeed);
    	drive.setRightSpeed(-tSpeed-fSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.setLeftSpeed(0);
    	drive.setRightSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.setLeftSpeed(0);
    	drive.setRightSpeed(0);
    }
}
