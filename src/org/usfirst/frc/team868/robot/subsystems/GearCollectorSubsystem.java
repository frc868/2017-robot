package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearCollectorSubsystem extends Subsystem {

	private static GearCollectorSubsystem instance;
	private Solenoid sol;
	private boolean state;
	
	@Override
	protected void initDefaultCommand() {}

	/**
	 * Constructor
	 */
	private GearCollectorSubsystem() {
		sol = new Solenoid(RobotMap.GearCollector.GEAR_SOLENOID);
	}
	/**
	 * Normal get instance method.
	 */
	public static GearCollectorSubsystem getInstance() {
		return instance == null ? instance = new GearCollectorSubsystem() : instance;
	}
	/**
	 * Set the gear collector to either: open = true, or closed = false.
	 */
	public void setGearCollector(boolean on) {
		state = on;
		sol.set(state);
	}
	/**
	 * Opens the gear collector, if not already open.
	 */
	public void setGearCollectorOpen() {
		setGearCollector(true);
	}
	/**
	 * Closes the gear collector, if not already closed.
	 */
	public void setGearCollectorClosed() {
		setGearCollector(false);
	}
	/**
	 * Switches the gear collector between open and closed states.
	 */
	public void toggleGearCollector() {
		setGearCollector(!state);
	}
	/**
	 * Returns whether or not the gear collector is open.
	 */
	public boolean isGearCollectorOpen() {
		return state;
	}
	
	public void updateSD(){
		SmartDashboard.putBoolean("Gear Collector", state);
	}
}
