package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterFlashlightSubsystem extends Subsystem {

    private static ShooterFlashlightSubsystem instance;
    private Relay flashlight;
    private boolean isOn;
    
    public static ShooterFlashlightSubsystem getInstance() {
    	if(instance == null) {
    		instance = new ShooterFlashlightSubsystem();
    	} 
    	return instance;
    }
    
    private ShooterFlashlightSubsystem() {
    	flashlight = new Relay(RobotMap.Flashlight.GEAR_LIGHT_PORT);
    	flashlight.setDirection(Relay.Direction.kForward);
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

