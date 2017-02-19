package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FreeBall extends Command {

	private AgitatorSubsystem agitator;

	private double delay;
	private double start;

	
    public FreeBall(double delay) {
    	agitator = AgitatorSubsystem.getInstance();
		requires(agitator);
		this.delay = delay;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	start = System.nanoTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (System.nanoTime() - start >= delay) {
    		agitator.switchDirection();
    		start = System.nanoTime();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
