package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class LidarSubsystem extends Subsystem {

	private static LidarSubsystem instance;
	private NetworkTable table;
	private SerialPort serial;
	
	private int distance;
	private int distanceBuffer;
	
	@Override
	protected void initDefaultCommand() {}
	
	private LidarSubsystem() {
		table = NetworkTable.getTable("LIDAR");
		serial = new SerialPort(115200, Port.kUSB);
	}
	
	public static LidarSubsystem getInstance() {
		return instance == null ? instance = new LidarSubsystem() : instance;
	}

	/**
	 * Reads most recent distance from the
	 * serial buffer and returns that. May hang 
	 * very slightly.
	 * @return distance in CM
	 */
	public int getDistance() {
		updateDistance();
		return distance;
	}
	
	/**
	 * Gets currently stored distance, may be
	 * out of date from the serial buffer. Has
	 * the advantage of being very quick.
	 * @return distance in CM
	 */
	public int getCurrentDistance() {
		return distance;
	}

	public void updateDistance() {
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
		updateTable();
	} //end method
	
	public void updateTable() {
		table.putNumber("LIDAR Distance", distance);
	}
}
