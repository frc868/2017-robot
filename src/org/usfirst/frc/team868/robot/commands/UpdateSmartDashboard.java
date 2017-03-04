package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GyroSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateSmartDashboard extends Command {
	
	private Timer time;
	private int counts;
	
	/**
	 * Use this command for adding any SmartDashboard output.  e.g. Subsystem get methods
	 */
    public UpdateSmartDashboard() {
    	setRunWhenDisabled(true);
    	time = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.reset();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// This had been set to go as high as 200 times a second (actually it had been infinite as it was 1/20 which is probably 0)
    	// I turned it down to 20 times a second max - does it need to be higher? (pkb)
    	// I don't know, last year's robot was 1/200, so I assumed that it would be fine. (cjd)
    	final double refreshRate = (1.0 / 20.0);
    	
    	if(time.get() >= refreshRate){
    		AgitatorSubsystem.getInstance().updateSD();
    		ClimberSubsystem.getInstance().updateSD();
    		ColorPixySubsystem.getInstance().updateSD();
    		DriveSubsystem.getInstance().updateSD();
    		GyroSubsystem.getInstance().updateSD();
//			GearFlashlightSubsystem.getInstance().updateSD();
//    		IRPixySubsystem.getInstance().updateSD();
//    		LidarSubsystem.getInstance().updateSmartDashboard();
			FeederSubsystem.getInstance().updateSD();
//			ShooterFlashlightSubsystem.getInstance().updateSD();
    		ShooterSubsystem.getInstance().updateSD();
    		TurretRotationSubsystem.getInstance().updateSD();
    		time.reset();
    		
    		counts++;
    		SmartDashboard.putNumber("Update SD Counts", counts);
    		//Debug
    	}
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
