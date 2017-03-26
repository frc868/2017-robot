package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetSpeed;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetVoltageCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.HoldPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ShootAndBaselineAuton extends CommandGroup {

	public ShootAndBaselineAuton(AutonChooser.StartingPoint start) {
		switch(start) {
		default:
		case B1:
		case B2:
		case B3:
			addParallel(new HoldPosition());
			addParallel(new ShooterSetSpeed(75));
			addSequential(new WaitCommand(2));
	    	addParallel(new AgitatorCommand(State.FORWARD));
	    	addParallel(new FeederCommand(State.FORWARD));
	    	addSequential(new WaitCommand(5));
	    	
	    	addParallel(new ShooterSetVoltageCommand(0));
	    	addParallel(new AgitatorCommand(State.OFF));
	    	addParallel(new FeederCommand(State.OFF));
			addSequential(new DriveDistance(-280));
			break;
		case R1:
		case R2:
		case R3:
			addParallel(new HoldPosition());
			addParallel(new ShooterSetSpeed(75));
			addSequential(new WaitCommand(2));
	    	addParallel(new AgitatorCommand(State.FORWARD));
	    	addParallel(new FeederCommand(State.FORWARD));
	    	addSequential(new WaitCommand(5));
	    	
	    	addParallel(new ShooterSetVoltageCommand(0));
	    	addParallel(new AgitatorCommand(State.OFF));
	    	addParallel(new FeederCommand(State.OFF));
			addSequential(new DriveDistance(280));
			break;
			
		}
	}
}
