
package org.usfirst.frc.team868.robot.subsystems;


import org.usfirst.frc.team868.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
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
 *  RC Servo ports:
 *  ^directly below pinout^
 *  	PWM0	PWM1		<---(Programmable Pixy output PWMs)
 *  	5V		5V			<---(5V of power)
 *  	GND		GND			<---(Ground)
 *  
 *  Pixy to I2C ports: (kOnboard)
 *  
 *  Pixy port 5 goes to I2C port SCL
 *  ...		  9		 to			 SDA
 *  
 *  OR
 *  
 *  Pixy to I2C ports: (kMXP)
 *  
 *  Pinout of kMXP ports:
 *  	33	31	29	27	...	7	5	3	1
 *  	34	32	30	28	...	8	6	4	2
 *  
 *  Pixy port 5 goes to I2C port 32
 *  ...		  9		 to 		 34
 *  
 */
@SuppressWarnings("unused")
public class ColorPixySubsystem extends Subsystem {
	
	private static ColorPixySubsystem instance;
	private SerialPort pixyCamS;
	private I2C pixyCamI;
	private int lastVal = 0;
	private boolean recordStarted = false;
	private int bytesRecorded;
	private int [] record = new int[6];
	//volatile private int frame = 0;
	volatile private double xMid;
	volatile private int yMid;
	volatile private int width;
	volatile private int height;
	
	private Thread thread;
	
	private ColorPixySubsystem(){
		if(RobotMap.Pixy.COLOR_PORT_TYPE == 0){
			pixyCamS = new SerialPort(RobotMap.Pixy.BAUDRATE, SerialPort.Port.kMXP);
		}else if(RobotMap.Pixy.COLOR_PORT_TYPE == 1){
			pixyCamI = new I2C(I2C.Port.kOnboard, RobotMap.Pixy.COLOR_I2C_VALUE);
		}else{
			pixyCamI = new I2C(I2C.Port.kMXP, RobotMap.Pixy.COLOR_I2C_VALUE);
		}
		startThread();
	}
	
	synchronized public void startThread() {
		if(thread != null) return;

		thread = createThread();
		thread.start();
	}
	
	synchronized public void stopThread() {
		if(thread == null) return;
		
		thread.interrupt();
		thread = null;
	}

	private Thread createThread() {
		return new Thread() {
			@Override
			public void run() {
				while(!isInterrupted()) {
					getValues();
				}
			}
		};
	}
	
	synchronized public void getValues(){//Call this method to update all of the data obtained from the Pixy
		byte [] input = new byte [14];
		try{
			if(RobotMap.Pixy.COLOR_PORT_TYPE == 0){
				input = pixyCamS.read(pixyCamS.getBytesReceived());
			}else{
				pixyCamI.readOnly(input, 14);
			}
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
		SmartDashboard.putNumber("Color Target X", getXAngleOffFromCenter());
		SmartDashboard.putNumber("Color Target Y", getYAngleOffFromCenter());
		//SmartDashboard.putNumber("Color Camera Target Width", getWidthOfTarget());
		//SmartDashboard.putNumber("Color Camera Target Height", getHeightOfTarget());
		//SmartDashboard.putNumber("Color Frame count", frame);
	}
	
	/**
	 * Returns the angle in degrees off from the x-axis center the target is. 
	 * Negative degrees means left, positive means right, 
	 * zero means it is either centered or cannot be found.
	 * @return
	 */
	synchronized public double getXAngleOffFromCenter(){
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
	synchronized public double getYAngleOffFromCenter(){
		if(yMid != 0){
			return (yMid - (RobotMap.Pixy.CAM_HEIGHT/2))*(RobotMap.Pixy.CAM_Y_ANGLE/2)/RobotMap.Pixy.CAM_HEIGHT;
		}else{
			return yMid;
		}
	}
	
	/**
	 * Returns the size of the current target in pixels.
	 */
	synchronized public int getSizeOfTargetInPixels(){
		return width*height;
	}
	
	/**
	 * Returns the width of the current target in pixels.
	 */
	synchronized public int getWidthOfTarget(){
		return width;
	}
	
	/**
	 * Returns the height of the current target in pixels.
	 */
	synchronized public int getHeightOfTarget(){
		return height;
	}
	
	synchronized public static ColorPixySubsystem getInstance(){
		if(instance == null){
			instance = new ColorPixySubsystem();
		}return instance;
	}

    public void initDefaultCommand() {
    }
}


