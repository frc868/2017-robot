package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HoppertoGearCommandGroup extends CommandGroup {

    public HoppertoGearCommandGroup(StartingPoint selected) {
       switch(selected) {
       		case B1:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(-45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       		case B2:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(-45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       		case B3:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(-45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       		case R1:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       		case R2:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       		case R3:
       			addSequential(new DriveDistance(196.5));
       			addSequential(new TurnToAngle(45));
       			addSequential(new DriveDistance(154.85638508));
       			addSequential(new GearReleaseCommand());
       			break;
       }
    }
}
