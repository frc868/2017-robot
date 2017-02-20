package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	private DriveSubsystem drive;
	private double endCount;
	private PIDController control;
	private final double kp = .02, ki = 0, kd = .05, kf = 0;
	
	/**
	 * Drives the given distance in centimeters using a PID controller.
	 * @param cm in centimeters
	 */
	public DriveDistance(double cm) {
		drive = DriveSubsystem.getInstance();
		requires(drive);
		endCount = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT + cm;
		control = new PIDController(kp , ki, kd, kf, new PIDSource(){
			public void setPIDSourceType(PIDSourceType pidSource) {}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			public double pidGet() {
				return drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT;
			}
		}, new PIDOutput(){
			public void pidWrite(double output) {
				if(output > .7)
					output = .7;
				if(output < -.7)
					output = -.7;
				if(output < -.02 && output > -RobotMap.Drive.MIN_DRIVE_SPEED)
					output = -RobotMap.Drive.MIN_DRIVE_SPEED;
				if(output > .02 && output < RobotMap.Drive.MIN_DRIVE_SPEED)
					output = RobotMap.Drive.MIN_DRIVE_SPEED;
				drive.setSpeed(output);
			}
		});
		control.setAbsoluteTolerance(4);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("Drive distance PID", control);
    	control.setSetpoint(endCount);
    	control.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	control.disable();
    }
}
