package org.usfirst.frc.team868.robot.commands;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistanceCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NetworkReaderCommandGroup extends CommandGroup {
	
	static final int PORT = 5800;
	ServerSocket server;
	Socket network;
	DataInputStream input;

    public NetworkReaderCommandGroup() {
    	
    	int command;
    	
    	try {
			server = new ServerSocket(PORT);
			network = server.accept();
	    	input = new DataInputStream(network.getInputStream());
	    	
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
	    		}
	    	} while (command != 0);

		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
}
