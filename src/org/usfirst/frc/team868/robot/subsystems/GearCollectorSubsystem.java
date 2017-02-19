package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearCollectorSubsystem extends Subsystem {

	private static GearCollectorSubsystem instance;
	private Solenoid closer;
	private Solenoid opener;
	private boolean state;
	private AnalogInput detector;
	
	@Override
	protected void initDefaultCommand() {}

	/**
	 * Constructor
	 */
	private GearCollectorSubsystem() {
		closer = new Solenoid(RobotMap.GearCollector.GEAR_SOLENOID_CLOSER);
		opener = new Solenoid(RobotMap.GearCollector.GEAR_SOLENOID_OPENER);
		detector = new AnalogInput(RobotMap.GearCollector.GEAR_DETECTOR_ANALOG_PORT);
		
		// Assign test mode group
		LiveWindow.addActuator("Gear Collector", "Solenoid", closer);
		LiveWindow.addSensor("Gear Collector", "Detector", detector);
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
	public void setGearCollector(boolean open) {
		state = open;
		opener.set(state);
		closer.set(!state);
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
	/**
	 * Returns whether or not a gear has been collected according to a sensor.
	 */
	public boolean isAGearCollected(){
		return detector.getVoltage() > RobotMap.GearCollector.DETECTION_VOLTAGE;//TODO find this value
	}
	/**
	 * Updates the information on SmartDashboard.
	 */
	public void updateSD(){
		SmartDashboard.putBoolean("Gear Collector", state);
		SmartDashboard.putBoolean("Gear detected", isAGearCollected());
	}
}
