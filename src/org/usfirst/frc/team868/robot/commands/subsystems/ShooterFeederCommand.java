package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.ShooterFeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterFeederCommand extends Command {

    ShooterFeederSubsystem feeder;

    boolean state;

    /**
     * Sets the feeder to the given argument
     * 
     * @param on
     */
    public ShooterFeederCommand(boolean on) {
        feeder = ShooterFeederSubsystem.getInstance();
        requires(feeder);

        state = on;
    }

    /**
     * Toggles the feeder's on/off status
     */
    public ShooterFeederCommand() {
        feeder = ShooterFeederSubsystem.getInstance();
        requires(feeder);
        state = !feeder.isFeederOn();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        feeder.setFeeder(state);
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
