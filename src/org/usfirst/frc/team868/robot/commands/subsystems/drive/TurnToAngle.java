package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.HoundMath;

/**
 *
 */
public class TurnToAngle extends Command {

	private DriveSubsystem motors;
	private PIDController controller;
	private final double P = 0.036, I = 0, D = 0.07;
	private double setAngle;

	/**
	 * Rotates the robot to the orientation of the given angle.
	 * (Note that this is not relative to the robot's orientation is when this command is called)
	 * @param angle in degrees
	 */
    public TurnToAngle(double angle) {
    	motors = Robot.drivetrain;
    	requires(motors);
    	setAngle = angle;
    	controller = new PIDController(P, I, D, new PIDSource(){

		public void setPIDSourceType(PIDSourceType pidSource) {}

		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		public double pidGet() {
			return Robot.gyro.getRotation();
		}
		
	}, new PIDOutput(){
		
		public void pidWrite(double output) {
			if(output > .05 && output < RobotMap.Drive.MIN_DRIVE_SPEED)
				output = RobotMap.Drive.MIN_DRIVE_SPEED;
			if(output < -.05 && output > -RobotMap.Drive.MIN_DRIVE_SPEED)
				output = -RobotMap.Drive.MIN_DRIVE_SPEED;
			motors.setL(HoundMath.checkRange(output, -.7, .7));
			motors.setR(HoundMath.checkRange(-output, -.7, .7));
		}
		
	});
	controller.setAbsoluteTolerance(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putData("Rotation PID", controller);
    	controller.setSetpoint(setAngle);
    	controller.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    }
}
