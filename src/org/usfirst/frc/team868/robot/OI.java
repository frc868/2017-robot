package org.usfirst.frc.team868.robot;

import org.usfirst.frc.team868.robot.commands.subsystems.ShootCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.ArcadeDriveCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.RecordMotorMovementHelper;
import lib.hid.ControllerMap;
import lib.hid.ControllerMap.Type;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	private ControllerMap driver, operator, curDriver;
	
	public OI() {
		driver = new ControllerMap(new Joystick(RobotMap.JoystickPort.DRIVER),
						ControllerMap.Type.XBOX_ONE);
		operator = new ControllerMap(new Joystick(RobotMap.JoystickPort.OPERATOR),
						ControllerMap.Type.XBOX_ONE);
		
		setup();
	}
	
	private void setup() {
		setupDriver(driver);
		setupOperator(operator);
		
		curDriver = driver;
		
		initSmartDashboard();
	}
	
	public void setupDriver(ControllerMap controller) {
		controller.clearButtons();
		@SuppressWarnings("unused") // eclipse pls
		ArcadeDriveCommand arcadedrive = new ArcadeDriveCommand(controller);
		
		// TURRET
		controller.getButton(RobotMap.Controls.Turret.SHOOT)
			.whenPressed(new ShootCommand(true))
			.whenReleased(new ShootCommand(false));
	}
	
	public void setupOperator(ControllerMap controller) {
		
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

