package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {
	
	private DriveSubsystem drive;
	private double leftStick;
	private double rightStick;
	private double leftPower = 0;
	private double rightPower = 0;
	
    public ArcadeDrive(double leftStick, double rightStick) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	this.leftStick = leftStick;
    	this.rightStick = rightStick;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(leftStick < RobotMap.Controls.MIN_JOYSTICK_VAL && leftStick > - RobotMap.Controls.MIN_JOYSTICK_VAL) {
    		leftStick = 0;
    	}
    	if(rightStick < RobotMap.Controls.MIN_JOYSTICK_VAL && rightStick > - RobotMap.Controls.MIN_JOYSTICK_VAL) {
    		rightStick = 0;
    	}
    	leftPower += leftStick;
    	rightPower += leftStick;
    	leftPower += rightStick;
    	rightPower -= rightStick;
    	drive.setSpeed(rangeCheck(leftPower), rangeCheck(rightPower));
    }

    private double rangeCheck(double input) {
    	return Math.max(-1, Math.min(1, input));
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
