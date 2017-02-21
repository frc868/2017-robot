package org.usfirst.frc.team868.robot.commands.subsystems.turret;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.TurretRotationSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import lib.hid.ControllerMap;

/**
 *
 */
public class JoystickTurretRotationalControl extends Command {

	private TurretRotationSubsystem turret;
	
    public JoystickTurretRotationalControl() {
    	turret = TurretRotationSubsystem.getInstance();
    	requires(turret);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double x = OI.getInstance().getOperator().getAxis(ControllerMap.Direction.RIGHT_HORIZONTAL);
    	double mult = .25+(OI.getInstance().getOperator().getAxis(OI.Controls.ADJUSTMENT_MULTIPLIER)/2);
    	if(x > 0)
    		turret.setPower(x*mult+RobotMap.Turret.MIN_ROTATE_SPEED);
    	else
    		turret.setPower(x*mult-RobotMap.Turret.MIN_ROTATE_SPEED);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	turret.setPower(0);
    }
}
