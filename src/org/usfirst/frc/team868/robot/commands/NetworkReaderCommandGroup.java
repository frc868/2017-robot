package org.usfirst.frc.team868.robot.commands;

import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;
import org.usfirst.frc.team868.robot.subsystems.TCPSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NetworkReaderCommandGroup extends CommandGroup {

	TCPSubsystem input;

	public NetworkReaderCommandGroup() {
		
		input = TCPSubsystem.getInstance();
		requires(input);

		int command;

		do {
			command = input.readInt();

			switch(command) {
				case 0:
					//exit while loop
					break;
					
				case 1:
					addSequential(new DriveDistanceCommand(input.readDouble()));
					break;
					
				case 2:
					addSequential(new TurnToAngle(input.readDouble()));
					break;
					
				default:
					System.err.println("Invalid command argument read from networks!");
					break;
			}
		} while (command != 0);


	}
}
