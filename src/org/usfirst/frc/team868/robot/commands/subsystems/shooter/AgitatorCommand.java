package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorCommand extends Command { //TOOD integrate into ShooterFeeder

	
	private AgitatorSubsystem agitator;
	private State state;
	
    public AgitatorCommand(State state) {
    	agitator = AgitatorSubsystem.getInstance();
		requires(agitator);
		this.state = state;
    }
    
    public AgitatorCommand(boolean on) {
    	this(on ? State.FORWARD : State.OFF);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	agitator.setAgitator(state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
}
