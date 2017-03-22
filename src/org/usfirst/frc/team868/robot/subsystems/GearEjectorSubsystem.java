package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearEjectorSubsystem extends Subsystem {

	
	private static GearEjectorSubsystem instance;
	private Solenoid closer;
	private Solenoid opener;
	private DigitalInput spikeDetector;
	private boolean state;
	@Override
	protected void initDefaultCommand() {}

	/**
	 * Constructor
	 */
	private GearEjectorSubsystem() {
		closer = new Solenoid(RobotMap.GearEjector.GEAR_EJECTOR_CLOSER);
		opener = new Solenoid(RobotMap.GearEjector.GEAR_EJECTOR_OPENER);
		spikeDetector = new DigitalInput(RobotMap.GearEjector.GEAR_PRESSURE_PLATE);
		
		// Assign test mode group
		LiveWindow.addActuator("Gear Collector", "Closer", closer);
		LiveWindow.addActuator("Gear Collector", "Opener", opener);
		LiveWindow.addSensor("Gear Collector", "Detector", spikeDetector);
	}
	
	/**
	 * Normal get instance method.
	 */
	public static GearEjectorSubsystem getInstance() {
		return instance == null ? instance = new GearEjectorSubsystem() : instance;
	}
	/**
	 * Set the gear ejector to either: open = true, or closed = false.
	 */
	public void setGearCollector(boolean open) {
		state = open;
		opener.set(state);
		closer.set(!state);
	}
	/**
	 * Opens the gear ejector, if not already open.
	 */
	public void setGearCollectorOpen() {
		setGearCollector(true);
	}
	/**
	 * Closes the gear ejector, if not already closed.
	 */
	public void setGearCollectorClosed() {
		setGearCollector(false);
	}
	/**
	 * Switches the gear ejector between open and closed states.
	 */
	public void toggleGearCollector() {
		setGearCollector(!state);
	}
	/**
	 * Returns whether or not the gear ejector is open.
	 */
	public boolean isGearCollectorOpen() {
		return state;
	}
	/**
	 * Updates the information on SmartDashboard.
	 */
	public void updateSD(){
		SmartDashboard.putBoolean("Gear Ejector", state);
	}
}

