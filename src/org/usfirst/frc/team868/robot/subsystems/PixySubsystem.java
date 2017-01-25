package org.usfirst.frc.team868.robot.subsystems;

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
    
	public final int CAM_WIDTH = 318;
	public final int CAM_X_ANGLE = 80;
	public final int CAM_HEIGHT = 198;
	public final int CAM_Y_ANGLE = 40;
	public final int BAUDRATE = 19200;
	//TODO: move ^these^ values to RobotMap
	
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
		pixyCam = new SerialPort(BAUDRATE, Port.kMXP);
	}public void getValues(){
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
	
	private void processBytesRecieved(byte[] input) {
		for(int i = 0; i < input.length; i++){
			processByte(input[i]);
		}
	}

	private void processByte(byte b) {
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

	private void processRecord() {
		xMid = record[2];
		yMid = record[3];
		width = record[4];
		height = record[5];
		SmartDashboard.putNumber("Frame count", frame);
	}
	
	/**
	 * Returns the angle in degrees off from the x-axis center the target is. 
	 * Negative degrees means left, positive means right, 
	 * zero means it is either centered or cannot be found.
	 */
	public double getXAngleOffFromCenter(){
		if(xMid != 0){
			return (xMid - (CAM_WIDTH/2))*(CAM_X_ANGLE/2)/CAM_WIDTH;
		}else{
			return xMid;
		}
	}
	
	public double getYAngleOffFromCenter(){
		if(yMid != 0){
			return (yMid - (CAM_HEIGHT/2))*(CAM_Y_ANGLE/2)/CAM_HEIGHT;
		}else{
			return yMid;
		}
	}
	
	public int getSizeOfTargetInPixels(){
		return width*height;
	}
	
	public int getWidthOfTarget(){
		return width;
	}
	
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

