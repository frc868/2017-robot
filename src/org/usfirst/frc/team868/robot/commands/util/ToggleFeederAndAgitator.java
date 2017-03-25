package org.usfirst.frc.team868.robot.commands.util;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleFeederAndAgitator extends CommandGroup {

    public ToggleFeederAndAgitator() {
    	//If both are off, turns both on, else turns both off.
    	if(AgitatorSubsystem.getInstance().getState() == State.OFF && FeederSubsystem.getInstance().getState() == State.OFF){
    		addParallel(new AgitatorCommand(State.FORWARD));
    		addSequential(new FeederCommand(State.FORWARD));
    	}else{
    		addParallel(new AgitatorCommand(State.OFF));
			addParallel(new FeederCommand(State.OFF));
    	}
    }
}
