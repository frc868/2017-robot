package client;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
	
//	static final String ROBOT_IP = "10.8.68.2";
	static final String ROBOT_IP = null;
	static final int ROBOT_PORT = 5800; 
	static Scanner input;
	static Socket network;
	static DataOutputStream output;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		boolean exit = false;
		
		System.out.print("Rembember to use a different laptop for Driver Station!\n-----\n");
		
		try {			
			network = new Socket(InetAddress.getByName(ROBOT_IP), ROBOT_PORT);
			output = new DataOutputStream(network.getOutputStream());
						
			System.out.println("Network connected successfully!\n-----\n");
			
			while(!exit) {
				System.out.print("Commands:\n" + 
								 "0. Submit Command Group\n" +
								 "1. Add DriveDistance Command\n" +
								 "2. Add TurnToAngle Command\n" +
								 "100. EXIT");
				
				switch(input.nextInt()) {
					case 100:
						exit = true;
					case 0:
						output.writeInt(0);
						break;
					case 1:
						output.writeInt(1);
						System.out.print("Distance (double): ");
						output.writeDouble(input.nextDouble());
						break;
					case 2:
						output.writeInt(2);
						System.out.print("Angle (double): ");
						output.writeDouble(input.nextDouble());
						break;
					default:
						System.err.println("Invalid command number!");
				}				
			}
			
			output.close();
			network.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		input.close();
	}
}
