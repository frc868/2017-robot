package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterFlashlightSubsystem extends Subsystem {

    private static ShooterFlashlightSubsystem instance;
    private Relay flashlight;
    private boolean isOn;
    private static final boolean DEBUG = false;
    
    /**
	 * Get the instance of this subsystem
	 * @return instance
	 */
    public static ShooterFlashlightSubsystem getInstance() {
    	if(instance == null) {
    		instance = new ShooterFlashlightSubsystem();
    	} 
    	return instance;
    }
    
    private ShooterFlashlightSubsystem() {
    	flashlight = new Relay(RobotMap.Flashlight.SHOOT_LIGHT_PORT);
    	flashlight.setDirection(Relay.Direction.kForward);

		// Assign test mode group
		LiveWindow.addActuator("ShooterFlashlight", "Shooter Relay", flashlight);
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
    		SmartDashboard.putBoolean("Shooter flashlight is on", isOn());
    	}
    }

    public void initDefaultCommand() {
    }
}

