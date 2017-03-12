
package org.usfirst.frc.team868.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team868.robot.commands.AutonLauncher;
import org.usfirst.frc.team868.robot.commands.UpdateSmartDashboard;
import org.usfirst.frc.team868.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {	
	
    public static AgitatorSubsystem agitator;
    public static ClimberSubsystem climber;
    public static CameraSubsystem camera;
    public static ColorPixySubsystem colorPixy;
    public static DriveSubsystem drivetrain;
    public static GearCollectorSubsystem gearCollector;
    public static GearFlashlightSubsystem gearLight;
    public static GyroSubsystem gyro;
    public static IRPixySubsystem irPixy;
    public static LidarSubsystem lidar;
    public static LEDSubsystem leds;
    public static FeederSubsystem feeder;
    public static ShooterFlashlightSubsystem shooterLight;
    public static ShooterSubsystem shooter;
    public static TurretRotationSubsystem turret;

	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

    	initSubsystems();
		OI.getInstance().initialize();
		
    	new UpdateSmartDashboard().start();

    }

	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        new AutonLauncher().start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
	
    /**
     * This function gets called to initialize the subsystems
     */
	private void initSubsystems() {
//		AgitatorSubsystem.getInstance();
//		ClimberSubsystem.getInstance();
//		CameraSubsystem.getRearInstance();
//		ColorPixySubsystem.getInstance();
//		DriveSubsystem.getInstance();
//		GearCollectorSubsystem.getInstance();
////		GearFlashlightSubsystem.getInstance();
//		GyroSubsystem.getInstance();
////		IRPixySubsystem.getInstance();
////		LidarSubsystem.getInstance();
//		LEDSubsystem.getInstance();
//		FeederSubsystem.getInstance();
////		ShooterFlashlightSubsystem.getInstance();
//		ShooterSubsystem.getInstance();
//		TurretRotationSubsystem.getInstance();
		
		agitator = new AgitatorSubsystem();
		climber = new ClimberSubsystem();
		camera = CameraSubsystem.getRearInstance();
		colorPixy = new ColorPixySubsystem();
		drivetrain = new DriveSubsystem();
		gearCollector = new GearCollectorSubsystem();
		gearLight = new GearFlashlightSubsystem();
		gyro = new GyroSubsystem();
		irPixy = new IRPixySubsystem();
		lidar = new LidarSubsystem();
		leds = new LEDSubsystem();
		feeder = new FeederSubsystem();
		shooterLight = new ShooterFlashlightSubsystem();
		shooter = new ShooterSubsystem();
		turret = new TurretRotationSubsystem();
	}
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
