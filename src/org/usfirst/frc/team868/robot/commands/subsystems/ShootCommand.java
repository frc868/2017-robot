package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {

    ShooterSubsystem shooter;
    double power;

    public ShootCommand(boolean on) {
        this(on ? RobotMap.Shoot.SHOOTER_POWER : 0); // TODO fix this
    }

    public ShootCommand(double p) {
        shooter = ShooterSubsystem.getInstance();
        requires(shooter);
        power = p;
    }

    // TODO should be have a factory (public) or a Subsystem initializer
    // (private) or neither?

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        shooter.setPower(power);
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
