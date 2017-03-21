package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSetSpeed extends Command {

	ShooterSubsystem shooter;
	double speed;
	
    public ShooterSetSpeed(double rps) {
    	shooter = ShooterSubsystem.getInstance();
    	requires(shooter);
    	speed = rps;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setSpeed(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
