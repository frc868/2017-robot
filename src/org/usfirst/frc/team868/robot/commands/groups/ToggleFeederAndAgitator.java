package org.usfirst.frc.team868.robot.commands.groups;

import org.usfirst.frc.team868.robot.commands.subsystems.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.ShooterFeederCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ToggleFeederAndAgitator extends CommandGroup {

    public ToggleFeederAndAgitator() {
    	addParallel(new AgitatorCommand());
    	addParallel(new ShooterFeederCommand());
    }
}
