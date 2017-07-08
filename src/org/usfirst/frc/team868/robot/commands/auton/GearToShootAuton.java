package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetSpeed;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.TurretIRLockToTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToShootAuton extends CommandGroup {

	public GearToShootAuton(StartingPoint start) {
		
		addSequential(new GearAuton(start));
		
		switch(start) {
		case R2:
//			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.WALL_TO_HOOK-30).speed(0.6).build());
//			addSequential(new DriveDistance.Builder(30).speed(0.35).plate(true).build());
//    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    		
			addSequential(new TurnByAngleGyro(-60, 2)); //75 degrees?
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance.Builder(-RobotMap.AutonValues.WALL_TO_HOOK-40).build()); //TODO make this it's own constant
			
			addParallel(new TurretIRLockToTarget()); //TODO is this broken?
	    	addParallel(new AgitatorCommand(State.FORWARD)); //TODO make this a command group
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
	    	break;
	    	
		case B2:
//			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.WALL_TO_HOOK-30).speed(0.6).build());
//			addSequential(new DriveDistance.Builder(30).speed(0.35).plate(true).build());
//    		addSequential(new OldDriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    		
			addSequential(new TurnByAngleGyro(60, 2));
			addParallel(new ShooterSetSpeed(84));
			addSequential(new DriveDistance.Builder(-RobotMap.AutonValues.WALL_TO_HOOK-40).build()); //TODO make this it's own constant
			
			addParallel(new TurretIRLockToTarget());
	    	addParallel(new AgitatorCommand(State.FORWARD));
	    	addParallel(new FeederCommand(State.FORWARD));
	    	
	    	break;
	    	
		default:
			break; //TODO other cases
		}
		
	}
}
