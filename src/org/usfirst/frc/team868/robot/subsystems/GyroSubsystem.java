package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.gyro.BNO055;

/**
 *
 */
public class GyroSubsystem extends Subsystem {
	
	//BNO055

    private static GyroSubsystem instance;
    private BNO055 gyro;
    private GyroBase gyroX;
    
    private GyroSubsystem(){
    	gyro = BNO055.getInstance(I2C.Port.kOnboard);
    	gyroX = gyro.createGyroX();
    	gyroX.reset();
    	
		// Assign test mode group
		LiveWindow.addSensor("Gyro", "X-Axis", gyroX);

    }
    
    public double getRotation(){
    	return gyroX.getAngle();
    }
    
    public void updateSD(){
    	SmartDashboard.putNumber("Rotation", getRotation());
    }
    
    public static GyroSubsystem getInstance(){
    	if(instance == null){
    		instance = new GyroSubsystem();
    	}
    	return instance;
    }

    public void initDefaultCommand() {
    }
}

