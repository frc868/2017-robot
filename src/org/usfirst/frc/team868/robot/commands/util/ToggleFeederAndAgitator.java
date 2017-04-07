package org.usfirst.frc.team868.robot.commands.util;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleFeederAndAgitator extends Command {

    private AgitatorSubsystem agitator;
	private FeederSubsystem feeder;

	public ToggleFeederAndAgitator() {
    	//If both are off, turns both on, else turns both off.
    	agitator = AgitatorSubsystem.getInstance();
    	feeder = FeederSubsystem.getInstance();
    	requires(feeder);
    	requires(agitator);
    }
    
    @Override
    protected void initialize(){
    	if(feeder.getState().equals(State.OFF) && agitator.getState().equals(State.OFF)){
    		feeder.setFeederForward();
    		agitator.setAgitatorForward();
    	}else{
    		feeder.setFeederOff();
    		agitator.setAgitatorOff();
    	}
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
}
