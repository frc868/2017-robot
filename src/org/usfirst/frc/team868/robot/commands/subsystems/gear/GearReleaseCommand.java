package org.usfirst.frc.team868.robot.commands.subsystems.gear;

import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearReleaseCommand extends Command {

    private GearCollectorSubsystem holder;

    public GearReleaseCommand() {
        holder = GearCollectorSubsystem.getInstance();
        requires(holder);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (!holder.isGearCollectorOpen()) {
            holder.setGearCollectorOpen();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
