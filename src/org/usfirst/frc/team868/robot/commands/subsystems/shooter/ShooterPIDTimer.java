package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterPIDTimer extends Command {

    private ShooterSubsystem shooter;
    double speed;
    boolean wasGood = false;
    Timer timer;

	public ShooterPIDTimer(double rps) {
		shooter = ShooterSubsystem.getInstance();
		requires(shooter);
		timer = new Timer();
		speed = rps;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("Shooter Spinup Time", 0);
    	shooter.setSpeed(speed);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	boolean isGood = Math.abs(shooter.getError()) < 1;
    	
    	if(!wasGood && isGood) {
        	SmartDashboard.putNumber("Shooter Spinup Time", timer.get());
        	wasGood = true;
    	} 
    	
    	if(wasGood && !isGood) {
    		wasGood = false;
    	}
    	
    	SmartDashboard.putBoolean("Shooter is On Target", isGood);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
