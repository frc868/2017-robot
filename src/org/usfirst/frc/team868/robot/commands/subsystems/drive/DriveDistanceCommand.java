package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCommand extends Command {

	private boolean isFinished = false;
	private DriveSubsystem drive;
	private int endCount;
	
	public DriveDistanceCommand(double cm) {
		this((int)(cm * RobotMap.Drive.COUNTS_PER_CM));
	}
	
    private DriveDistanceCommand(int counts) {
    	drive = DriveSubsystem.getInstance();
    	requires(drive);
    	
    	endCount = drive.getAvgEncoders() + counts;        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setPower(RobotMap.Drive.SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(drive.getAvgEncoders() > endCount) {
    		isFinished = true;
    		drive.setPower(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
