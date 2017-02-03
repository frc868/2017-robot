package org.usfirst.frc.team868.robot.commands.subsystems.drive;

import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	private DriveSubsystem motors;
	private PIDController controller;
	private final double P = 0, I = 0, D = 0;

    public TurnToAngle(double angle) {
    	motors = DriveSubsystem.getInstance();
    	requires(motors);
    	controller = new PIDController(P, I, D, new PIDSource(){

		public void setPIDSourceType(PIDSourceType pidSource) {}

		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		public double pidGet() {
			return angle;
		}
		
	}, new PIDOutput(){
		
		public void pidWrite(double output) {
			motors.setL(rangeCheck(output));
			motors.setR(rangeCheck(-output));
		}
		
	});
	controller.setAbsoluteTolerance(1);
	controller.setToleranceBuffer(3);// I read the docs, and thought it was applicable, may be good to test if it is helpful or not.
    }
    
    public double rangeCheck(double power){
    	if(power > 1){
    		return 1;
    	}else if(power < -1){
    		return -1;
    	}else{
    		return power;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
