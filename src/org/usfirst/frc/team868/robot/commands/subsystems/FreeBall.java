package org.usfirst.frc.team868.robot.commands.subsystems;

import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.AgitatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.util.HoundMath;

/**
 *
 */
public class FreeBall extends Command {

	private AgitatorSubsystem agitator;
	private double delay;

    public FreeBall(double delay) {
    	agitator = AgitatorSubsystem.getInstance();
		requires(agitator);
		this.delay = delay;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	delay = SmartDashboard.getNumber("Agitator Shake Time", delay);
    	double speed = HoundMath.checkRange(2 * RobotMap.Feeder.AGITATOR_SPEED * Math.sin(timeSinceInitialized() * (Math.PI/delay)));
    	agitator.setAgitatorSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	agitator.setAgitatorOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
