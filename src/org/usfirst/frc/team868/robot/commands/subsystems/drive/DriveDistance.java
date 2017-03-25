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
	private double power = 0;
	private final double kp = .02, ki = 0, kd = .05, kf = 0;
	private double distanceCM;
	
	/**
	 * Drives the given distance in centimeters using a PID controller.
	 * @param cm in centimeters
	 */
	public DriveDistance(double cm) {
		distanceCM = cm;
		drive = DriveSubsystem.getInstance();
		requires(drive);
		gyro = GyroSubsystem.getInstance();
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
	
	double startDistance;

    // Called just before this Command runs the first time
    protected void initialize() {
		endCount = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT + distanceCM;
		gyro.reset();
    	//SmartDashboard.putData("Drive distance PID", control);
    	control.setSetpoint(endCount);
    	control.enable();
    	startDistance = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT;
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
		double rotation = gyro.getRotation();
		double multiplier = 1 - Math.abs(10*Math.sin(rotation*Math.PI/180));
    	if(rotation > 1){
    		lPower = lPower*multiplier;
    	}else if(rotation < -1){
    		rPower = rPower*multiplier;
    	}
		drive.setSpeed(lPower, rPower);
		SmartDashboard.putNumber("Auton Driven Distance", drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT - startDistance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(control.getError()) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	control.disable();
    	drive.setSpeed(0);
    }
}
