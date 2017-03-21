package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetLEDs extends Command {

    private LEDSubsystem led;
	private ShooterSubsystem shoot;
	private GearCollectorSubsystem gear;
	private FeederSubsystem feed;

	public SetLEDs() {
    	led = LEDSubsystem.getInstance();
    	shoot = ShooterSubsystem.getInstance();
    	gear = GearCollectorSubsystem.getInstance();
    	feed = FeederSubsystem.getInstance();
    	requires(led);
    }

    protected void execute() {
    	led.setGearLEDs(gear.isAGearCollected());//Gear is collected or not.
    	
    	if(shoot.getPIDController().isEnabled()){
    		if(shoot.getPIDController().onTarget() && feed.getBallBeamBreak()){
    			led.setShooterLEDs(true, true);//Shooter is on target and ball is available.
    		}else if(shoot.getSpeed() < .1){
    			led.setShooterLEDs(false, true);//Shooter is stalled, or is unable to speed up.
    		}else{
    			led.setShooterLEDs(true, false);//Shooter is spinning up to speed.
    		}
    	}else{
    		led.setShooterLEDs(false, false);//Shooter is still spinning, but trying to stop, or is stopped.
    	}
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
}
