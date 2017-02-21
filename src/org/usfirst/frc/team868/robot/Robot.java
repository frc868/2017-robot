
package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.UpdateSmartDashboard;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ColorPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team868.robot.subsystems.FeederSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearCollectorSubsystem;
import org.usfirst.frc.team868.robot.subsystems.GearFlashlightSubsystem;
import org.usfirst.frc.team868.robot.subsystems.MainGyroSubsystem;
import org.usfirst.frc.team868.robot.subsystems.IRPixySubsystem;
import org.usfirst.frc.team868.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterFlashlightSubsystem;
import org.usfirst.frc.team868.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {	

	public static OI oi;
	public static AgitatorSubsystem agitator;
	public static ClimberSubsystem climber;
	public static ColorPixySubsystem colorPixy;
	public static DriveSubsystem drivetrain;
	public static GearCollectorSubsystem gearCollector;
	public static GearFlashlightSubsystem gearFlashlight;
	public static MainGyroSubsystem gyro;
	public static IRPixySubsystem irPixy;
	public static LidarSubsystem lidar;
	public static FeederSubsystem feeder;
	public static ShooterFlashlightSubsystem shooterFlashlight;
	public static ShooterSubsystem shooter;
	public static TurretRotationSubsystem turret;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		initSubsystems();
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

		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */

		// schedule the autonomous command (example)
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
		oi = new OI();
		agitator = new AgitatorSubsystem();
		climber = new ClimberSubsystem();
		colorPixy = new ColorPixySubsystem();
		drivetrain = new DriveSubsystem();
		gearCollector = new GearCollectorSubsystem(); 
		gearFlashlight = new GearFlashlightSubsystem();
		gyro = new MainGyroSubsystem();
		irPixy = new IRPixySubsystem();
		lidar = new LidarSubsystem();
		feeder = new FeederSubsystem();
		shooterFlashlight = new ShooterFlashlightSubsystem();
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
