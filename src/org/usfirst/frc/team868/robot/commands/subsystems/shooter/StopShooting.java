package org.usfirst.frc.team868.robot.commands.subsystems.shooter;

import org.usfirst.frc.team868.robot.commands.subsystems.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.ShooterFeederCommand;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StopShooting extends CommandGroup {

    public StopShooting() {
    	if(AgitatorSubsystem.getInstance().getState() != AgitatorSubsystem.State.OFF){//Deactivates agitator
    		addSequential(new AgitatorCommand());
    	}
    	if(FeederSubsystem.getInstance().getState() == FeederSubsystem.State.FORWARD || FeederSubsystem.getInstance().getState() == FeederSubsystem.State.BACKWARD){//Deactivates feeder
    		addSequential(new ShooterFeederCommand());
    	}
    	addSequential(new ShootUsingVoltage(0));//Deactivates shooter
    	
    	//Should we also stop the turret from rotating to whatever target it finds?
    }
}
