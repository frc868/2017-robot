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
public class DriveDistanceEncoders extends Command {

	private DriveSubsystem drive;
	private double endCount;
	private PIDController control;
	private double power = 0;
	private final double kp = .02, ki = 0, kd = .05, kf = 0;
	private double distanceCM;
	
	/**
	 * Drives the given distance in centimeters using a PID controller.
	 * @param cm in centimeters
	 */
	public DriveDistanceEncoders(double cm) {
		distanceCM = cm;
		drive = DriveSubsystem.getInstance();
		requires(drive);
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
				power = output;
			}
		});
		control.setAbsoluteTolerance(4);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
		endCount = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT + distanceCM;
    	//SmartDashboard.putData("Drive distance PID", control);
    	control.setSetpoint(endCount);
    	control.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(power > RobotMap.Drive.MAX_AUTON_DRIVE_SPEED)
			power = RobotMap.Drive.MAX_AUTON_DRIVE_SPEED;
		if(power < -RobotMap.Drive.MAX_AUTON_DRIVE_SPEED)
			power = -RobotMap.Drive.MAX_AUTON_DRIVE_SPEED;
		if(power < -.02 && power > -RobotMap.Drive.MIN_DRIVE_SPEED)
			power = -RobotMap.Drive.MIN_DRIVE_SPEED;
		if(power > .02 && power < RobotMap.Drive.MIN_DRIVE_SPEED)
			power = RobotMap.Drive.MIN_DRIVE_SPEED;
		double rPower = power, lPower = power;
		double lSpeed = drive.getLSpeed(), rSpeed = drive.getRSpeed();
    	if(lSpeed > rSpeed){
    		lPower = lPower*rSpeed/lSpeed;
    	}else if(rSpeed > lSpeed){
    		rPower = rPower*lSpeed/rSpeed;
    	}
		drive.setSpeed(lPower, rPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(control.getError()) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	control.disable();
    }
}
