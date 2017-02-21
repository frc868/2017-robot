package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.subsystems.*;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.*;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.*;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.*;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShootCommand;

import lib.hid.ControllerMap;
import lib.hid.DPadButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

//// CREATING BUTTONS
// One type of button is a joystick button which is any button on a joystick.
// You create one by telling it which joystick it's on and which button
// number it is.
// Joystick stick = new Joystick(port);
// Button button = new JoystickButton(stick, buttonNumber);

// There are a few additional built in buttons you can use. Additionally,
// by subclassing Button you can create custom triggers and bind those to
// commands the same as any other Button.

//// TRIGGERING COMMANDS WITH BUTTONS
// Once you have a button, it's trivial to bind it to a button in one of
// three ways:

// Start the command when the button is pressed and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenPressed(new ExampleCommand());

// Run the command while the button is being held down and interrupt it once
// the button is released.
// button.whileHeld(new ExampleCommand());

// Start the command when the button is released  and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenReleased(new ExampleCommand());
public class OI {
	
	static OI instance;
	private ControllerMap driver, operator;
	
	public OI() {
		driver = new ControllerMap(new Joystick(RobotMap.JoystickPort.DRIVER),
						ControllerMap.Type.XBOX_ONE);
		operator = new ControllerMap(new Joystick(RobotMap.JoystickPort.OPERATOR),
						ControllerMap.Type.XBOX_ONE);
		
		setupDriver(driver);
		setupOperator(operator);
		
		initSmartDashboard();
	}
	
	public interface Controls {
		boolean isPixyTargeting = true;
		
		final int TOGGLE_GEAR_COLLECTOR = ControllerMap.Key.A;
		final int TOGGLE_PIXY_TURRET_TARGETING = ControllerMap.Key.B;
		final int TOGGLE_AGITATOR_AND_FEEDER = ControllerMap.Key.Y;
		final int TOGGLE_SHOOTER = ControllerMap.Key.X;
		
		final int CALIBRATE = ControllerMap.Key.BACK;
		final int FREE_THE_BALL = ControllerMap.Key.START;
	
		final int TOGGLE_GEAR_FLASHLIGHT = ControllerMap.Key.LB;
		final int TOGGLE_SHOOTER_FLASHLIGHT = ControllerMap.Key.RB;

		final int CLIMB = ControllerMap.Key.RT;//TODO: make this command, make it so that LT must be fully pulled in order to start
		final int FINE_TURRET_ADJUSTMENT_MULTIPLIER = ControllerMap.Key.LT;//TODO: add this multiplier to the joystick commands.
		
		final int INCREASE_SHOOTER_SPEED = DPadButton.Direction.UP;//TODO: make this command
		final int DECREASE_SHOOTER_SPEED = DPadButton.Direction.DOWN;
	}
	
	public void setupDriver(ControllerMap controller) {
		controller.clearButtons();
	}
	
	public void setupOperator(ControllerMap controller) {
		controller.clearButtons();
		//TODO: add rotate turret w/ joystick(s)
		
		
		// TURRET
		controller.getButton(Controls.TOGGLE_PIXY_TURRET_TARGETING)//TODO: make this command toggle-able
			.whenPressed(new RotateUsingIRPixy());
		controller.getButton(Controls.CALIBRATE)
			.whenPressed(new CalibrateTurret());
		controller.getButton(Controls.FREE_THE_BALL)
			.whenPressed(new FreeBall(2));//TODO: find a constant or some value to use here
//		controller.getButton(Controls.TOGGLE_AGITATOR_AND_FEEDER)
//			.whenPressed(new AgitatorCommand() && new ShooterFeederCommand());
		//TODO: ^ make that command.
		controller.getButton(Controls.TOGGLE_SHOOTER)
			.whenPressed(new ShootCommand(0));//TODO: make this command
				
		// GEAR
		controller.getButton(Controls.TOGGLE_GEAR_COLLECTOR)
			.whenPressed(new GearCollectorToggleCommand());
		
		//FLASHLIGHTS
		controller.getButton(Controls.TOGGLE_GEAR_FLASHLIGHT)
			.whenPressed(new GearFlashlightCommand());
		controller.getButton(Controls.TOGGLE_SHOOTER_FLASHLIGHT)
			.whenPressed(new ShooterFlashlightCommand());
	}
	
	public ControllerMap getDriver() {
		return driver;
	}
	
	public ControllerMap getOperator() {
		return operator;
	}

	public void initSmartDashboard() {
		SmartDashboard.putData("save file", new RecordMotorMovementHelper("saveFile", "testing#1.txt"));
		SmartDashboard.putData("loadFile(dont press)", new RecordMotorMovementHelper("readFile", "testing#1.txt"));
		SmartDashboard.putData("record motors start", new RecordMotorMovementHelper("record", "testing#1.txt"));
		SmartDashboard.putData("Rotate 180", new RotateAngle(180));
		SmartDashboard.putData("Rotate 90", new RotateAngle(90));
		SmartDashboard.putData("Rotate 0", new RotateAngle(0));
		SmartDashboard.putData("Drive forward 2m", new DriveDistance(200));
	}

	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
}
