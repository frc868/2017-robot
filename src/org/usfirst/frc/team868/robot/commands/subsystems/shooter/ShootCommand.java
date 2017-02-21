package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {

	ShooterSubsystem shooter;
	double speed;
	
	public ShootCommand(){
		this(RobotMap.Shoot.TARGET_SHOOTER_SPEED);
	}
    
	/**
	 * Sets the speed of the shooter wheel
	 * @param rps Rotations per Second (usually 50 - 80)
	 */
    public ShootCommand(double rps) {
    	shooter = ShooterSubsystem.getInstance();
    	requires(shooter);
    	speed = rps;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(shooter.getPower() > 0){
    		shooter.setSpeed(0);
    	}else{
    		shooter.setSpeed(speed);
    	}
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
