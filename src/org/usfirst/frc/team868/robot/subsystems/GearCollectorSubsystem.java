package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearCollectorSubsystem extends Subsystem {

	private static GearCollectorSubsystem instance;
	private Solenoid sol;
	private boolean state;
	
	@Override
	protected void initDefaultCommand() {}

	private GearCollectorSubsystem() {
		sol = new Solenoid(RobotMap.GearCollector.GEAR_SOLENOID);
	}
	
	public static GearCollectorSubsystem getInstance() {
		return instance == null ? instance = new GearCollectorSubsystem() : instance;
	}
	
	public void setGearCollector(boolean on) {
		state = on;
		sol.set(state);
	}
	
	public void setGearCollectorOpen() {
		setGearCollector(true);
	}
	
	public void setGearCollectorClosed() {
		setGearCollector(false);
	}
	
	public void toggleGearCollector() {
		setGearCollector(!state);
	}
	
	public boolean isGearCollectorOpen() {
		return state;
	}
}
