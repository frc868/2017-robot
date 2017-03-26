package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearEjectorSubsystem;
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
	
	// Optional command timeout settings
	private boolean finishOnTimeout = false;
	private double finishOnTimeoutSecs = 30.0;
	
	// Optional mode to stop if gear plate is pressed
	private boolean finishOnGearEject = false;

	// Set required accuracy for normal command termination
	private static final double CM_TOLERANCE = 1.0;

	// Keeps track of how many times we are within tolerance (and how many times are required)
	private static final int REQUIRED_TOLERANCE_CHECKS = 2;
	private int withinToleranceCnt;

    // Called just before this Command runs the first time
    protected void initialize() {
		endCount = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT + distanceCM;
    	initRotation = gyro.getRotation();
    	SmartDashboard.putData("Drive distance PID", control);
    	control.setSetpoint(endCount);
    	control.enable();
    	startDistance = drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT;
    	withinToleranceCnt = 0;
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
		double multiplier = 1 - Math.abs(gyro.getRotation()-initRotation)/10;
    	if(gyro.getRotation()-initRotation > 1){
    		lPower = lPower*multiplier;
    	}else if(gyro.getRotation()-initRotation < -1){
    		rPower = rPower*multiplier;
    	}
		drive.setSpeed(lPower, rPower);
		SmartDashboard.putNumber("Auton Driven Distance", drive.getAvgEncoders()*RobotMap.Drive.CM_PER_COUNT - startDistance);

		// Bump or reset the number of times we have been on target
		if  (Math.abs(control.getError()) <= CM_TOLERANCE) {
			withinToleranceCnt++;
		} else {
			withinToleranceCnt = 0;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (finishOnTimeout && (timeSinceInitialized() >= finishOnTimeoutSecs)) {
    		// Done if time out enabled and time expired
    		return true;
    	}
    	
    	if (finishOnGearEject) {
    		GearEjectorSubsystem ejector = GearEjectorSubsystem.getInstance();
    		if (ejector.isPlatePressed()) {
    			// Done if enabled to check ejector plate and plate pressed
    			return true;
    		}
    	}

    	// Otherwise, just check to see if we have been at the required
    	// position enough times in a row
    	return (withinToleranceCnt >= REQUIRED_TOLERANCE_CHECKS);
    }

    // Called once after isFinished returns true
    protected void end() {
    	control.disable();
    	drive.setSpeed(0);
    }
    
    /**
     * You can use this to end the command normally if the target distance has not been reached within the specified time period.
     * 
     * @param enable Pass true to enable the timeout option.
     * @param secs Maximum number of seconds to wait for the distance to be reached.
     */
    public void enableFinishOnTimeout(boolean enable, double secs) {
    	finishOnTimeout = enable;
    	finishOnTimeoutSecs = secs;
    }
    
    /**
     * You can use this method to end the command if the trigger plate behind the gear is pressed when driving.
     * 
     * @param enable Pass true if you want to stop driving when the gear trigger plate is pressed by the spike.
     */
    public void enableFinishOnGearEject(boolean enable) {
    	finishOnGearEject = enable;
    }
}