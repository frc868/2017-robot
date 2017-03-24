package org.usfirst.frc.team868.robot.commands.subsystems.gear;

import org.usfirst.frc.team868.robot.subsystems.GearEjectorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearEjectorAutomaticCommand extends Command {

	private GearEjectorSubsystem ejector;

	public GearEjectorAutomaticCommand() {
		ejector = GearEjectorSubsystem.getInstance();
		requires(ejector);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(ejector.isPlatePressed()) {
			ejector.setGearEjectorOpen(); //TODO wait a bit then close it?
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
