package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnByAngleGyro extends Command {
	
	private double angle;
	
	/**
	 * Rotates the robot by 'angle' degrees from it's current rotation, where right is positive and left is negative
	 * @param angle
	 */
    public TurnByAngleGyro(double angle) {
    	this.angle = GyroSubsystem.getInstance().getRotation()+angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	new TurnToAngleGyro(angle).start();
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
