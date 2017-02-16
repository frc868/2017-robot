package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.ShooterFlashlightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterFlashlightCommand extends Command {

	ShooterFlashlightSubsystem flash;
	
	boolean state;
	
	/**
	 * Sets the flashlight to the given argument
	 * @param on
	 */
    public ShooterFlashlightCommand(boolean on) { 
    	flash = ShooterFlashlightSubsystem.getInstance(); 
        requires(flash);
        
        state = on;
    }
    
    /**
     * Toggles the flashlight's on/off status
     */
    public ShooterFlashlightCommand(){
    	flash = ShooterFlashlightSubsystem.getInstance();
    	requires(flash);
    	state = !flash.isOn();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flash.setLight(state);
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
