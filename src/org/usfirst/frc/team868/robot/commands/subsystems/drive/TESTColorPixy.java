package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem.Record;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TESTColorPixy extends Command {
	
	private ColorPixySubsystem camera;
	private Timer time;
	private double timeout;
	
	/**
	 * Uses the Pixy camera to rotate to look directly towards the target 
	 * Will stop after 'timeout' seconds have passed.
	 */
    public TESTColorPixy(double timeout) {
    	camera = ColorPixySubsystem.getInstance();
    	this.timeout = timeout;
    }
    
    /**
     * Runs this command with a default timeout of 2.5 seconds
     */
    public TESTColorPixy(){
    	this(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    private boolean targetNotWithinView(Record target){
    	//Target is either too close or too far to the right to be distinguishable
    	double x = target.getXAngleOffFromCenter(), h = target.getHeightOfTarget();
    	if(x > 20 || h > 100){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    private double calculateDegreesOffFromTarget(Record target){//TODO: this!!!
    	double x = target.getXAngleOffFromCenter(), h = target.getHeightOfTarget();
    	return 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Record target = camera.getTarget(); // Target should be in a cross shape
    	if(!targetNotWithinView(target)){
    		return;
    	}else{
    		new TurnByAngleGyro(calculateDegreesOffFromTarget(target)).start();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > timeout;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
}