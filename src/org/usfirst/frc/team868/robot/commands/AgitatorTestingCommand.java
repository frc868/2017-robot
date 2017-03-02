package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorBetterCommand extends Command {

	
	private AgitatorSubsystem agitator;
	private AgitatorSubsystem.State state;
	
    public AgitatorBetterCommand(AgitatorSubsystem.State state) {
    	agitator = AgitatorSubsystem.getInstance();
		requires(agitator);
		this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	agitator.setAgitator(state);
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
