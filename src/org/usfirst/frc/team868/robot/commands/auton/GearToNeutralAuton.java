package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceBuilder;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearToNeutralAuton extends CommandGroup {

    public GearToNeutralAuton(StartingPoint selected) {
    	switch(selected) {
    	case B1:
		case R3:
			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1+29));
			addSequential(new TurnByAngleGyro(-58, 1.5));
			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2, true));
    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
    		
    		addSequential(new TurnByAngleGyro(60, 1.5));
    		addSequential(new DriveDistanceBuilder.Builder().setDistance(300).setSpeed(0.6).setTimeout(5).build());
			break;
			
		case B3:
		case R1:
			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_1-11));
			addSequential(new TurnByAngleGyro(62, 1.5));
			addSequential(new DriveDistance(RobotMap.AutonValues.GEAR_AUTON_DIST_2-6, true));
			addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			
			addSequential(new TurnByAngleGyro(-60, 1.5));
    		addSequential(new DriveDistance(300));
			break;
			
		case B2:
		case R2:
			addSequential(new DriveDistanceBuilder.Builder().setDistance(RobotMap.AutonValues.WALL_TO_HOOK-30).setSpeed(0.6).build());
			addSequential(new DriveDistanceBuilder.Builder().setDistance(30).setSpeed(0.35).usePlate(true).build());
    		addSequential(new DriveDistance(-RobotMap.AutonValues.HOOK_BACKOFF));
			break;
			
		default:
			break;
    	}
    	
    	/*
    	 * B3/R3 (the ones by the boiler) shouldn't turn quite as much, as we want to start heading towards the
    	 * loading stations
    	 */
    }
}
