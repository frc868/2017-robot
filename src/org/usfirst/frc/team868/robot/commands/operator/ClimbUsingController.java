package org.usfirst.frc.team868.robot.commands.operator;

import org.usfirst.frc.team868.robot.OI;
import org.usfirst.frc.team868.robot.RobotMap;
import org.usfirst.frc.team868.robot.subsystems.ClimberSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbUsingController extends Command {

	private ClimberSubsystem climb;
	
    public ClimbUsingController() {
    	climb = ClimberSubsystem.getInstance();
    	requires(climb);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double multiplier = OI.getInstance().getOperator().getAxis(OI.Controls.ADJUSTMENT_MULTIPLIER);
    	if(multiplier >= .5)
    		climb.startClimbing(multiplier*RobotMap.Climber.CLIMBER_SPEED*OI.getInstance().getOperator().getAxis(OI.Controls.CLIMB));
    	if(OI.getInstance().getOperator().getAxis(OI.Controls.CLIMB) == 0)
    		climb.startClimbing(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climb.stopClimbing();
    }
}
