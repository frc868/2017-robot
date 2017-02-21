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
public class MainGyroSubsystem extends Subsystem {

    private static MainGyroSubsystem instance;
    private BNO055 gyro;
    private GyroBase gyroX;
    
    public MainGyroSubsystem(){
    	gyro = BNO055.getInstance(I2C.Port.kOnboard);
    	gyroX = gyro.createGyroX();
    	gyroX.reset();
    	
		// Assign test mode group
		LiveWindow.addSensor("Gyro", "X-Axis", gyroX);

    }
    
    /**
     * Gets the current rotation of the robot.
     * @return in degrees
     */
    public double getRotation(){
    	return gyroX.getAngle();
    }
    
    /**
	 * Update information on SmartDashboard.
	 */
    public void updateSD(){
    	SmartDashboard.putNumber("Rotation", getRotation());
    }
    
    /**
	 * Get the instance of this subsystem
	 * @return instance
	 */
    public static MainGyroSubsystem getInstance(){
    	if(instance == null){
    		instance = new MainGyroSubsystem();
    	}
    	return instance;
    }

    public void initDefaultCommand() {
    }
}

