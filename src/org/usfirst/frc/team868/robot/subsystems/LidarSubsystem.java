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
		
		Thread t = new Thread() {
			@Override
			public void run() {
				while (!isInterrupted()) {
					if (bytesAvailable()) {
						updateDistance();
					} else {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		t.start();
	}
	
	public static LidarSubsystem getInstance() {
		return instance == null ? instance = new LidarSubsystem() : instance;
	}

	/**
	 * Reads distance
	 * @return distance in CM
	 */
	public int getDistance() {
		return distance;
	}
	

	/**
	 * Whether more than two 
	 * bytes are available to read
	 * @return
	 */
	private boolean bytesAvailable() {
		return serial.getBytesReceived() > 2;
	}

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
	
	public void updateTable() {
		table.putNumber("LIDAR Distance", distance);
	}
}
