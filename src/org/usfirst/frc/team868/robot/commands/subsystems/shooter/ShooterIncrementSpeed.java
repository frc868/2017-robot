package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterIncrementSpeed extends Command {
	
	private ShooterSubsystem shoot;
	private double increment;
    public ShooterIncrementSpeed(double increment) {
    	shoot = ShooterSubsystem.getInstance();
    	requires(shoot);
    	this.increment = increment;
    }

    protected void initialize() {
    	shoot.setSpeed(increment+shoot.getSetpoint());
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }
}
