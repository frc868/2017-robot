package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnByAngleGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAuton extends CommandGroup {

    public GearAuton(StartingPoint selected) {
    	switch(selected) {
    		case B1:
    		case R3:
    		
    			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.GEAR_AUTON_DIST_1+29).build());
    			addSequential(new TurnByAngleGyro(-58, 1.5));
    			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.GEAR_AUTON_DIST_2).plate(true).build());
	    		addSequential(new DriveDistance.Builder(-RobotMap.AutonValues.HOOK_BACKOFF).build());
    			break;
    			
    		case B3:
    		case R1:
    			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.GEAR_AUTON_DIST_1-8).build());
    			addSequential(new TurnByAngleGyro(62, 1.5));
    			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.GEAR_AUTON_DIST_2-21).plate(true).build());
    			addSequential(new DriveDistance.Builder(-RobotMap.AutonValues.HOOK_BACKOFF).build());
    			break;
    			
    		case B2:
    		case R2:
    			addSequential(new DriveDistance.Builder(RobotMap.AutonValues.WALL_TO_HOOK-30).speed(0.6).build());
    			addSequential(new DriveDistance.Builder(30).speed(0.35).plate(true).build());
	    		addSequential(new DriveDistance.Builder(-RobotMap.AutonValues.HOOK_BACKOFF).build());

//	    		addSequential(new WaitCommand(5));//TODO: Test this before any actual matches!!!
	    		addSequential(new StopIfDroppedGear());//Anything after this should only
	    		addSequential(new TurnByAngleGyro(-45, 1.5));
	    		addSequential(new DriveDistance.Builder(22).build());
	    		addSequential(new TurnByAngleGyro(45, 1.5));
	    		addSequential(new DriveDistance.Builder(RobotMap.AutonValues.HOOK_BACKOFF+20).plate(true).build());
    			break;
    			
    		default:
    			break;
    	}
    }
}
