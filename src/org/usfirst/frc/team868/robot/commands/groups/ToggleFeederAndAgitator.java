package org.usfirst.frc.team868.robot.commands.groups;

import org.usfirst.frc.team868.robot.Robot;
import org.usfirst.frc.team868.robot.commands.subsystems.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.ShooterFeederCommand;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleFeederAndAgitator extends CommandGroup {

    public ToggleFeederAndAgitator() {
    	//If agitator is running, turns off agitator, and if feeder is running, turns off feeder.
    	if(Robot.agitator.getState() != AgitatorSubsystem.State.OFF){
    		addParallel(new AgitatorCommand());
    		if(Robot.feeder.getState() != FeederSubsystem.State.OFF){
    			addParallel(new ShooterFeederCommand());
    		}
    	//If agitator isn't running, but feeder is, turns off feeder.
    	}else if(Robot.feeder.getState() != FeederSubsystem.State.OFF){
			addParallel(new ShooterFeederCommand());
		//If both aren't running, turns them both on.
    	}else{
    		addParallel(new AgitatorCommand());
			addParallel(new ShooterFeederCommand());
    	}
    }
}
