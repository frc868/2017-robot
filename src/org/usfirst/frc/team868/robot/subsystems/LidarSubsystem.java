package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class LidarSubsystem extends Subsystem {

	private static LidarSubsystem instance;
	private NetworkTable table;
	private SerialPort serial;
	
	private int distance;
	private int distanceBuffer;
	
	private Thread thread;
	
	@Override
	protected void initDefaultCommand() {}
	
	/**
	 * Constructor. Creates a network table
	 * and a serial port object, and then creates
	 * a thread and runs it.
	 */
	private LidarSubsystem() {
		table = NetworkTable.getTable("LIDAR"); //TODO change to SmartDashboard?
		serial = new SerialPort(RobotMap.LIDAR.BAUD, RobotMap.LIDAR.PORT);
		
		startThread();
	}
	
	/**
	 * Gets the subsystem instance
	 * 
	 * @return subsystem instance
	 */
	public static LidarSubsystem getInstance() {
		return instance == null ? instance = new LidarSubsystem() : instance;
	}
	
	public void startThread() {
		if(thread == null) {
			thread = getThread();
			thread.start();
		}
	}
	
	public void stopThread() {
		if(thread != null) {
			thread.interrupt();
			thread = null;
		}
	}
	
	/**
	 * Creates a runnable instance of the
	 * LIDAR distance reading thread. Loops and
	 * continuously reads from the Serial buffer.
	 * 
	 * @return Thread object to be run
	 */
	private Thread getThread() {
		return new Thread() {
			@Override
			public void run() {
				while (!isInterrupted()) {
					if (bytesAvailable()) {
						updateDistance();
					} else {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {}
					}
				} //end while
			} //end run
		};
	}

	/**
	 * Reads current saved distance
	 * 
	 * @return distance in CM
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Whether more than two 
	 * bytes are available to read
	 * 
	 * @return
	 */
	private boolean bytesAvailable() {
		return serial.getBytesReceived() > 2;
	}

	/**
	 * Reads from Serial data buffer until no data
	 * exists, and sets the value of distance
	 */
	private void updateDistance() {
		while(serial.getBytesReceived() > 0) {
			byte read = serial.read(1)[0];
			
			if (read == 13) {
				//CR
			} else if (read == 10) {
				//LF, last
				distance = distanceBuffer;
				distanceBuffer = 0;
			} else if (read >= 48 && read <= 57) {
				//NUM
				distanceBuffer *= 10;
				distanceBuffer += (int)read - 48;
			} else {
				System.err.println("LIDAR Read Invalid Character");
			}
		} //end while
	} //end method
	
	/**
	 * Updates the NetworkTable with the 
	 * most recent distance information
	 */
	public void updateSmartDashboard() {
		table.putNumber("LIDAR Distance", distance);
	}
}
