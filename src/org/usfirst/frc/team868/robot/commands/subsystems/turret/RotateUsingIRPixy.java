package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateUsingIRPixy extends Command {
	
	private IRPixySubsystem camera;
	private TurretRotationSubsystem turret;
	private Timer time;
	private double timeout;
	private double lastAngle;

	/**
	 * Uses the Pixy camera to rotate to look directly towards the target 
	 * Will stop after 'timeout' seconds have passed.
	 */
    public RotateUsingIRPixy(double timeout) {
    	camera = IRPixySubsystem.getInstance();
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
    	this.timeout = timeout;
    }
    
    /**
     * Runs this command with a default timeout of 2.5 seconds
     */
    public RotateUsingIRPixy(){
    	this(2.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turret.stop();
    	time = new Timer();
    	time.reset();
    	time.start();
    	lastAngle = camera.getTarget().getXAngleOffFromCenter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currAngle = camera.getTarget().getXAngleOffFromCenter();
    	turret.setAngle(turret.getAngle()+currAngle);
    	if(lastAngle != currAngle)
    		time.reset();
    	if(time.get() > timeout)
        	new TurretLookForTarget().start();
    	lastAngle = currAngle;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turret.stop();
    }
}
