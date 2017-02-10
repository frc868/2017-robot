package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	
	private static ClimberSubsystem instance;
	private CANTalon climber;
	
	private ClimberSubsystem(){
		climber = new CANTalon(RobotMap.Climber.CLIMBER_MOTOR);
		climber.setInverted(RobotMap.Climber.IS_INVERTED);
	}
	
	public void startClimbing(double speed){
		climber.set(speed);
	}
	
	public void startClimbing(){
		climber.set(RobotMap.Climber.CLIMBER_SPEED);
	}
	
	public void stopClimbing(){
		climber.set(0);
	}
	
	public static ClimberSubsystem getInstance(){
		if(instance == null){
			instance = new ClimberSubsystem();
		}
		return instance;
	}

    public void initDefaultCommand() {
    }
}

