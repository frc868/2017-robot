package org.usfirst.frc.team868.robot.subsystems;

public interface TargetProvider {
	
	/**
	 * Returns desired target value for PID controller.
	 * 
	 * @return Value that you want your PID to go to.
	 */
	double getSetpoint();

}
