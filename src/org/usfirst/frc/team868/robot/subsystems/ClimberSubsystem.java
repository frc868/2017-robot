package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Subsystem which represents the rope climber
 */
public class ClimberSubsystem extends Subsystem {
	
	private static ClimberSubsystem instance;
	private CANTalon climber;
	
	private ClimberSubsystem(){
		climber = new CANTalon(RobotMap.Climber.CLIMBER_MOTOR);
		climber.setInverted(RobotMap.Climber.IS_INVERTED);
		
		// Assign test mode group
		LiveWindow.addActuator("Climber", "Motor", climber);
	}
	
	/**
	 * Begin climbing the rope
	 * @param speed Speed to set motor
	 */
	public void startClimbing(double speed){
		climber.set(speed);
	}
	
	/**
	 * Begin climbing the rope at the default speed
	 */
	public void startClimbing(){
		climber.set(RobotMap.Climber.CLIMBER_SPEED);
	}
	
	/**
	 * Stop climbing
	 */
	public void stopClimbing(){
		climber.set(0);
	}
	
	/**
	 * Gets whether robot is pressing button
	 * @return if limit FWD limit switch is closed
	 */
	public boolean isPressingButton() {
		return climber.isFwdLimitSwitchClosed();
	}
	
	/**
	 * Get the instance of this subsystem
	 * @return instance
	 */
	public static ClimberSubsystem getInstance(){
		if(instance == null){
			instance = new ClimberSubsystem();
		}
		return instance;
	}

    public void initDefaultCommand() {
    }
}

