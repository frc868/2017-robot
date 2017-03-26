package org.usfirst.frc.team868.robot.commands.util;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorToggleCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterFeederCommand;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleFeederAndAgitator extends CommandGroup {

    public ToggleFeederAndAgitator() {
    	//If agitator is running, turns off agitator, and if feeder is running, turns off feeder.
    	if(AgitatorSubsystem.getInstance().getState() != State.OFF){
    		addParallel(new AgitatorToggleCommand());
    		if(FeederSubsystem.getInstance().getState() != State.OFF){
    			addParallel(new ShooterFeederCommand());
    		}
    	//If agitator isn't running, but feeder is, turns off feeder.
    	}else if(FeederSubsystem.getInstance().getState() != State.OFF){
			addParallel(new ShooterFeederCommand());
		//If both aren't running, turns them both on.
    	}else{
    		addParallel(new AgitatorToggleCommand());
			addParallel(new ShooterFeederCommand());
    	}
    }
}
