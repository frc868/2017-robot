package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	private DriveSubsystem drive;
	private int endCount;
	private PIDController control;
	private final double kp = 0, ki = 0, kd = 0, kf = 0;
	
	/**
	 * Drives the given distance in centimeters using a PID controller.
	 * @param cm in centimeters
	 */
	public DriveDistance(double cm) {
		endCount = drive.getAvgEncoders() + (int)(cm * RobotMap.Drive.COUNTS_PER_CM);
		control = new PIDController(kp , ki, kd, kf, new PIDSource(){
			public void setPIDSourceType(PIDSourceType pidSource) {}

			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}

			public double pidGet() {
				return drive.getAvgEncoders();
			}
		}, new PIDOutput(){
			public void pidWrite(double output) {
				drive.setSpeed(output);
			}
		});
		control.setAbsoluteTolerance(10);
		control.setToleranceBuffer(3);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	control.setSetpoint(endCount);
    	control.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return control.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	control.disable();
    }
}
