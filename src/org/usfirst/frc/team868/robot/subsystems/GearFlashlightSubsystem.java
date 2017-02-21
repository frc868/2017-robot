package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearFlashlightSubsystem extends Subsystem {

    private static GearFlashlightSubsystem instance = new GearFlashlightSubsystem();
    private final Relay flashlight;
    private boolean isOn;
    private static final boolean DEBUG = false;
    
    /**
	 * Get the instance of this subsystem
	 * @return instance
	 */
    public static GearFlashlightSubsystem getInstance() {
    	if(instance == null) {
    		System.out.println("Constructed new GFL");
    		instance = new GearFlashlightSubsystem();
    	} 
    	return instance;
    }
    
    public GearFlashlightSubsystem() {
    	flashlight = new Relay(RobotMap.Flashlight.GEAR_LIGHT_PORT);
    	flashlight.setDirection(Relay.Direction.kForward);

    	// Assign test mode group
		LiveWindow.addActuator("GearFlashlight", "Gear Relay", flashlight);
    }
    
    /**
     * Sets the flashlight where on = true, off = false.
     * @param on
     */
    public void setLight(boolean on) {
    	isOn = on;
    	
    	if(isOn) {
    		flashlight.set(Relay.Value.kOn);
    	} else {
    		flashlight.set(Relay.Value.kOff);
    	}
    }
    
    /**
     * @return current status of the flashlight.
     */
    public boolean isOn() {
//    	return flashlight.get() == Relay.Value.kOn;
    	return isOn;
    }
    
    /**
     * Turns on the flashlight.
     */
    public void setLightOn() {
    	setLight(true);
    }
    
    /**
     * Turns off the flashlight.
     */
    public void setLightOff() {
    	setLight(false);
    }
    
    /**
	 * Update information on SmartDashboard.
	 */
    public void updateSD(){
    	if(DEBUG){
    		SmartDashboard.putBoolean("Gear flashlight is on", isOn());
    	}
    }
    
    public void initDefaultCommand() {
    }
}

