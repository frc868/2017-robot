package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class GearFlashlightSubsystem extends Subsystem {

    private static GearFlashlightSubsystem instance;
    private Relay flashlight;
    private boolean isOn;
    
    public static GearFlashlightSubsystem getInstance() {
    	if(instance == null) {
    		instance = new GearFlashlightSubsystem();
    	} 
    	return instance;
    }
    
    private GearFlashlightSubsystem() {
    	flashlight = new Relay(RobotMap.Flashlight.GEAR_LIGHT_PORT);
    	flashlight.setDirection(Relay.Direction.kForward);

    	// Assign test mode group
		LiveWindow.addActuator("Gear Flashlight", "Relay", flashlight);
    }
    
    public void setLight(boolean on) {
    	isOn = on;
    	
    	if(isOn) {
    		flashlight.set(Relay.Value.kOn);
    	} else {
    		flashlight.set(Relay.Value.kOff);
    	}
    }
    
    public boolean isOn() {
    	return isOn; //TODO should we instead call flashlight.get()?
    }
    
    public void setLightOn() {
    	setLight(true);
    }
    
    public void setLightOff() {
    	setLight(false);
    }

    public void initDefaultCommand() {
    }
}

