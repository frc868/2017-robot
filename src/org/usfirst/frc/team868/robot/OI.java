package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.groups.FeedAndShootCommandGroup;
import org.usfirst.frc.team868.robot.commands.subsystems.*;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.*;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.*;
import org.usfirst.frc.team868.robot.commands.subsystems.gear.*;

import lib.hid.ControllerMap;
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
	
	public void setupDriver(ControllerMap controller) {
		controller.clearButtons();
		
		// DRIVE (DRIVER)
		@SuppressWarnings("unused") // eclipse pls
		ArcadeDriveCommand arcadedrive = new ArcadeDriveCommand(controller);
		
		// TURRET
		controller.getButton(RobotMap.Controls.Turret.R_PIXY)
			.whenPressed(new RotateUsingIRPixy());
		controller.getButton(RobotMap.Controls.Turret.SHOOT)
			.whileHeld(new FeedAndShootCommandGroup());
	}
	
	public void setupOperator(ControllerMap controller) {
		controller.clearButtons();
		
		// TURRET
		controller.getButton(RobotMap.Controls.Turret.R_FORWARD)
			.whenPressed(new RotateTurretToAngle(0));
		controller.getButton(RobotMap.Controls.Turret.R_BACKWARD)
			.whenPressed(new RotateTurretToAngle(180));
		controller.getButton(RobotMap.Controls.Turret.R_LEFT)
			.whenPressed(new RotateTurretToAngle(90));
		controller.getButton(RobotMap.Controls.Turret.R_RIGHT)
			.whenPressed(new RotateTurretToAngle(-90));
		controller.getButton(RobotMap.Controls.Turret.R_PIXY)
			.whenPressed(new RotateUsingIRPixy());
		controller.getButton(RobotMap.Controls.Turret.SHOOT)
			.whileHeld(new FeedAndShootCommandGroup());
		controller.getButton(RobotMap.Controls.Turret.CALIBRATE)
			.whenPressed(new CalibrateTurretCommand());
				
		// GEAR
		controller.getButton(RobotMap.Controls.Gear.TOGGLE_COLLECTOR)
			.whenPressed(new GearCollectorToggleCommand());
		controller.getButton(RobotMap.Controls.Gear.TOGGLE_FLASHLIGHT)
			.whenPressed(new GearFlashlightCommand());
		
		// CLIMBER (OPERATOR)
		controller.getButton(RobotMap.Controls.Climber.CLIMB)
			.whileHeld(new ClimberCommand());
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
	}

	public static OI getInstance() {
		if(instance == null) {
			instance = new OI();
		}
		return instance;
	}
}
