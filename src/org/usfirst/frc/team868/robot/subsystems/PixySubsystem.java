package org.usfirst.frc.team868.robot.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  Pinout of back of Pixy: 
 *  ^top of Pixy^
 *  	1	2
 *  	3	4
 *  	5	6
 *  	7	8
 *  	9	10
 *  
 *  Pinout of kMXP ports:
 *  	33	31	29	27	...	7	5	3	1
 *  	34	32	30	28	...	8	6	4	2
 *  
 *  Pixy to kMXP ports:
 *  
 *  Pixy port 1 goes to kMXP port 14
 *  ...		  4		 to			  10
 *  ...		  10	 to			  12
 *  
 */
public class PixySubsystem extends Subsystem {
	
	private static PixySubsystem instance;
	private SerialPort pixyCam;
	private int lastVal = 0;
	private boolean recordStarted = false;
	private int bytesRecorded;
	private int [] record = new int[6];
	private int frame = 0;
	private double xMid;
	private int yMid;
	private int width;
	private int height;
	
	private PixySubsystem(){
		pixyCam = new SerialPort(RobotMap.Pixy.BAUDRATE, Port.kMXP);
	}
	
	public void getValues(){//Call this method to update all of the data obtained from the Pixy
		byte [] input;
		try{
			input = pixyCam.read(pixyCam.getBytesReceived());
		}catch(Throwable t){
			return;
		}
		StringBuilder dump = new StringBuilder(48);
		if(input.length > 0){
			dump.append(Integer.toHexString(input[0] & 0xff));
			for(int i = 1; i < input.length; i++){
				dump.append(' ');
				dump.append(Integer.toHexString(input[i] & 0xff));
			}
			processBytesRecieved(input);
		}
	}
	
	private void processBytesRecieved(byte[] input) {//See getValues()
		for(int i = 0; i < input.length; i++){
			processByte(input[i]);
		}
	}

	private void processByte(byte b) {//See processBytesRecieved()
		int val = b & 0xff;
		if(val == 0xaa && lastVal  == 0x55){
			recordStarted  = true;
			bytesRecorded = 0;
		}else if(recordStarted){
			bytesRecorded++;
			if(bytesRecorded % 2 == 0){
				int field = (val<<8) + lastVal;
				record[(bytesRecorded/2)-1] = field;
				if(bytesRecorded == 12){
					processRecord();
					bytesRecorded = 0;
				}
			}
		}
		lastVal = val;
	}

	private void processRecord() {//Called in processByte(), sends obtained Pixy values to field values.
		xMid = record[2];
		yMid = record[3];
		width = record[4];
		height = record[5];
	}
	
	public void updateSD(){//Updates the SD with the values specified.
		SmartDashboard.putNumber("Camera Target X", getXAngleOffFromCenter());
		SmartDashboard.putNumber("Camera Target Y", getYAngleOffFromCenter());
		SmartDashboard.putNumber("Camera Target Width", getWidthOfTarget());
		SmartDashboard.putNumber("Camera Target Height", getHeightOfTarget());
		SmartDashboard.putNumber("Frame count", frame);
	}
	
	/**
	 * Returns the angle in degrees off from the x-axis center the target is. 
	 * Negative degrees means left, positive means right, 
	 * zero means it is either centered or cannot be found.
	 */
	public double getXAngleOffFromCenter(){
		if(xMid != 0){
			return (xMid - (RobotMap.Pixy.CAM_WIDTH/2))*(RobotMap.Pixy.CAM_X_ANGLE/2)/RobotMap.Pixy.CAM_WIDTH;
		}else{
			return xMid;
		}
	}
	
	/**
	 * Returns the angle in degrees off from the y-axis center the target is. 
	 * Negative degrees means below, positive means above, 
	 * zero means it is either centered or cannot be found.
	 */
	public double getYAngleOffFromCenter(){
		if(yMid != 0){
			return (yMid - (RobotMap.Pixy.CAM_HEIGHT/2))*(RobotMap.Pixy.CAM_Y_ANGLE/2)/RobotMap.Pixy.CAM_HEIGHT;
		}else{
			return yMid;
		}
	}
	
	/**
	 * Returns the size of the current target in pixels.
	 */
	public int getSizeOfTarget(){
		return width*height;
	}
	
	/**
	 * Returns the width of the current target in pixels.
	 */
	public int getWidthOfTarget(){
		return width;
	}
	
	/**
	 * Returns the height of the current target in pixels.
	 */
	public int getHeightOfTarget(){
		return height;
	}
	
	public static PixySubsystem getInstance(){
		if(instance == null){
			instance = new PixySubsystem();
		}
		return instance;
	}

    public void initDefaultCommand() {
    	
    }
}

