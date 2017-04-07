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
    	this.increment = increment;
    }

    protected void initialize() {
    	new ShooterSetSpeed(increment+shoot.getSetpoint()).start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }
}
