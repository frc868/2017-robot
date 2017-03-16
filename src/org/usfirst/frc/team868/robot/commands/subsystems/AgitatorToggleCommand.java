package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AgitatorToggleCommand extends Command { //TODO integrate into Agitator COmmand

	AgitatorSubsystem agitator;	
    
    /**
     * Toggles the agitator's on/off status
     */
    public AgitatorToggleCommand(){
    	agitator = AgitatorSubsystem.getInstance();
    	requires(agitator);
    }

	// Called just before this Command runs the first time
	protected void initialize() {
		agitator.toggleAgitator();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}	
}
