package org.usfirst.frc.team868.robot.subsystems;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TCPSubsystem extends Subsystem {

    private static TCPSubsystem instance;
    
    static final int PORT = 5800;
	private ServerSocket server;
	private Socket network;
	private DataInputStream input;
    
    public static TCPSubsystem getInstance() {
    	if(instance == null) {
    		instance = new TCPSubsystem();
    	}
    	return instance;
    }
    
    private TCPSubsystem() {
    	try {
	    	server = new ServerSocket(PORT);
			network = server.accept();
	    	input = new DataInputStream(network.getInputStream());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }

    public void initDefaultCommand() {}
    
    public int readInt() {
    	try {
    		return input.readInt();
    	} catch (Exception e) { //null pointer OR IOException
    		return -1;
    	}
    }
    
    public double readDouble() {
    	try {
    		return input.readDouble();
    	} catch (Exception e) { //null pointer OR IOException
    		return -1;
    	}
    }    
}

