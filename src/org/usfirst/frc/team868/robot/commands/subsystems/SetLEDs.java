package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterFeederSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLEDs extends Command {

    private LEDSubsystem led;
    private ShooterSubsystem shoot;
    private GearCollectorSubsystem gear;
    private ShooterFeederSubsystem feed;

    public SetLEDs() {
        led = LEDSubsystem.getInstance();
        shoot = ShooterSubsystem.getInstance();
        gear = GearCollectorSubsystem.getInstance();
        feed = ShooterFeederSubsystem.getInstance();
        requires(led);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        led.setGearLEDs(gear.isAGearCollected());// Gear is collected or not.

        if (shoot.getPIDController().isEnabled()) {
            if (shoot.getPIDController().onTarget() && feed.getBallBeamBreak()) {
                led.setShooterLEDs(true, true);// Shooter is on target and ball
                                               // is available.
            } else if (shoot.getSpeed() < .1) {
                led.setShooterLEDs(false, true);// Shooter is stalled, or is
                                                // unable to speed up.
            } else {
                led.setShooterLEDs(true, false);// Shooter is spinning up to
                                                // speed.
            }
        } else {
            led.setShooterLEDs(false, false);// Shooter is still spinning, but
                                             // trying to stop, or is stopped.
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
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
