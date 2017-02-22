package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterFeederCommand extends Command {

	FeederSubsystem feeder;
	
    /**
     * Toggles the feeder's on/off status
     */
    public ShooterFeederCommand(){
    	feeder = FeederSubsystem.getInstance();
    	requires(feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(feeder.getState() != FeederSubsystem.State.OFF)
    		feeder.setFeederOff();
    	else
    		feeder.setFeederForward();
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
