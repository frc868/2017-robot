package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.commands.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.RotateUsingColorPixy;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.GearReleaseCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropOffGearAutonCommandGroup extends CommandGroup {

    public DropOffGearAutonCommandGroup(StartingPoint selected) {
        switch (selected) {
            case B1:
                addSequential(new DriveDistanceCommand(240));
                addSequential(new TurnToAngle(-45));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(50));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            case B2:
                addSequential(new DriveDistanceCommand(180));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(30));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            case B3:
                addSequential(new DriveDistanceCommand(240));
                addSequential(new TurnToAngle(45));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(50));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            case R1:
                addSequential(new DriveDistanceCommand(240));
                addSequential(new TurnToAngle(45));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(50));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            case R2:
                addSequential(new DriveDistanceCommand(180));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(30));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            case R3:
                addSequential(new DriveDistanceCommand(240));
                addSequential(new TurnToAngle(-45));
                addSequential(new RotateUsingColorPixy());
                addSequential(new DriveDistanceCommand(50));
                addSequential(new GearReleaseCommand());
                addSequential(new DriveDistanceCommand(-40));
                break;
            default:
                break;
        }
    }
}
