package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GyroSubsystem;

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
	private GyroSubsystem gyro;
	private double endCount;
	private PIDController control;
	private double initRotation;
	private double power = 0;
	private final double kp = .02, ki = 0, kd = .05, kf = 0;
	
	/**
	 * Drives the given distance in centimeters using a PID controller.
	 * @param cm in centimeters
	 */
	public DriveDistance(double cm) {
		drive = DriveSubsystem.getInstance();
		requires(drive);
		gyro = GyroSubsystem.getInstance();
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
				power = output;
			}
		});
		control.setAbsoluteTolerance(4);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	initRotation = gyro.getRotation();
    	SmartDashboard.putData("Drive distance PID", control);
    	control.setSetpoint(endCount);
    	control.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(power > .7)
			power = .7;
		if(power < -.7)
			power = -.7;
		if(power < -.02 && power > -RobotMap.Drive.MIN_DRIVE_SPEED)
			power = -RobotMap.Drive.MIN_DRIVE_SPEED;
		if(power > .02 && power < RobotMap.Drive.MIN_DRIVE_SPEED)
			power = RobotMap.Drive.MIN_DRIVE_SPEED;
		double rPower = power, lPower = power;
		double multiplier = 1 - Math.abs(gyro.getRotation()-initRotation)/100;
    	if(gyro.getRotation()-initRotation > 1){
    		lPower = lPower*multiplier;
    	}else if(gyro.getRotation()-initRotation < -1){
    		rPower = rPower*multiplier;
    	}
		drive.setSpeed(lPower, rPower);
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
