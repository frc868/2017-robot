package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.RotateUsingColorPixy;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GoToHopperCommandGroup extends CommandGroup {

    public GoToHopperCommandGroup(StartingPoint selected) {
    	switch(selected) {
    		case R1:
    			addSequential(new TurnToAngle(26.175817482942755));
    			addSequential(new DriveDistanceCommand(527.058966436));
    			addSequential(new TurnToAngle(-116.175817482942755));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R2:
    			addSequential(new TurnToAngle(34.694655221684876));
    			addSequential(new DriveDistanceCommand(408.465995821));
    			addSequential(new TurnToAngle(-124.694655222));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R3:
    			addSequential(new DriveDistanceCommand(232.5));
    			addSequential(new TurnToAngle(90));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B1:
    			addSequential(new TurnToAngle(-26.175817482942755));
    			addSequential(new DriveDistanceCommand(527.058966436));
    			addSequential(new TurnToAngle(116.175817482942755));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B2:
    			addSequential(new TurnToAngle(-34.694655221684876));
    			addSequential(new DriveDistanceCommand(408.465995821));
    			addSequential(new TurnToAngle(124.694655222));
    			addSequential(new DriveDistanceCommand(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B3:
    			addSequential(new DriveDistanceCommand(232.5));
    			addSequential(new TurnToAngle(-90));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		default:
    			break;
    	}
    }
}
