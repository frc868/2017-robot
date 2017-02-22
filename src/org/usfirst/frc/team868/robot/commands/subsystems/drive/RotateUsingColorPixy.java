package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateUsingColorPixy extends Command {

	@Override
	protected boolean isFinished() {
		return true;
	}
	
//	private ColorPixySubsystem camera; //FIXME
//	private Timer time;
//	private double timeout;
//	
//	/**
//	 * Uses the Pixy camera to rotate to look directly towards the target 
//	 * Will stop after 'timeout' seconds have passed.
//	 */
//    public RotateUsingColorPixy(double timeout) {
//    	camera = ColorPixySubsystem.getInstance();
//    	this.timeout = timeout;
//    }
//    
//    /**
//     * Runs this command with a default timeout of 2.5 seconds
//     */
//    public RotateUsingColorPixy(){
//    	this(2.5);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	time = new Timer(); 
//    	time.reset();
//    	time.start();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	new RotateAngle(camera.getTarget().getXAngleOffFromCenter()).start(); 
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return time.get() > timeout; 
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	end();
//    }
}
