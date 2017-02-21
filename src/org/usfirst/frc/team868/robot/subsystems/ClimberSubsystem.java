package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.subsystems.ClimbUsingController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.HoundMath;

/**
 * Subsystem which represents the rope climber
 */
public class ClimberSubsystem extends Subsystem {
	
	private static ClimberSubsystem instance;
	private CANTalon climber;
	private static final boolean DEBUG = false;
	
	private ClimberSubsystem(){
		climber = new CANTalon(RobotMap.Climber.CLIMBER_MOTOR);
		climber.setInverted(RobotMap.Climber.IS_INVERTED);
		climber.enableLimitSwitch(true, false);
		climber.enableBrakeMode(true);
		climber.changeControlMode(CANTalon.TalonControlMode.Voltage);
		
		// Assign test mode group
		LiveWindow.addActuator("Climber", "Motor", climber);
	}
	
	/**
	 * Begin climbing the rope
	 * @param speed Speed to set motor in a range of 0 to 1, as a percentage of 12 volts
	 */
	public void startClimbing(double speed){
		climber.set(HoundMath.checkRange(speed, 0, 1) * 12);
	}
	
	/**
	 * Begin climbing the rope at the default speed
	 */
	public void startClimbing(){
		startClimbing(RobotMap.Climber.CLIMBER_SPEED);
	}
	
	/**
	 * Gets whether the robot is trying to climb
	 * @return if climber motor is running
	 */
	public boolean isClimbing(){
		return climber.get() != 0;
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
	 * Update information on SmartDashboard.
	 */
	public void updateSD(){
		if(DEBUG){
			SmartDashboard.putBoolean("Is climbing", isClimbing());
			SmartDashboard.putBoolean("Is pressing button", isPressingButton());
		}
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
    	setDefaultCommand(new ClimbUsingController());
    }
}

