package org.usfirst.frc.team868.robot.commands.auton;

import org.usfirst.frc.team868.robot.RobotMap.State;
import org.usfirst.frc.team868.robot.commands.auton.AutonChooser.StartingPoint;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.DriveDistance;
import org.usfirst.frc.team868.robot.commands.subsystems.drive.TurnToAngleGyro;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.AgitatorCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.FeederCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.shooter.ShooterSetVoltageCommand;
import org.usfirst.frc.team868.robot.commands.subsystems.turret.TurretIRLockToTarget;
import org.usfirst.frc.team868.robot.commands.util.FeedAndShootCommandGroup;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HopperAuton extends CommandGroup {

    public HopperAuton(StartingPoint selected) {
    	double posOneAngle = CalculateGeometry.getAngle(541.589574413, 232.5);
    	double posTwoAngle = CalculateGeometry.getAngle(335.839574413, 232.5);
    	double posOneDistance = CalculateGeometry.getHypotenuse(541.589574413, 232.5);
    	double posTwoDistance = CalculateGeometry.getHypotenuse(335.839574413, 232.5);
    	switch(selected) {
    		case R1:
    			addSequential(new TurnToAngleGyro(90 - posOneAngle));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngleGyro(-90 - (90 - posOneAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B1:
    			addSequential(new TurnToAngleGyro(-(90 -posOneAngle)));
    			addSequential(new DriveDistance(posOneDistance));
    			addSequential(new TurnToAngleGyro((-90 - (90 - posOneAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R2:
    			addSequential(new TurnToAngleGyro(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngleGyro(-90 - (90 - posTwoAngle)));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case B2:
    			addSequential(new TurnToAngleGyro(90 - posTwoAngle));
    			addSequential(new DriveDistance(posTwoDistance));
    			addSequential(new TurnToAngleGyro((-90 - (90 - posTwoAngle))));
    			addSequential(new DriveDistance(-75.660425587));
    			addSequential(new FeedAndShootCommandGroup());
    			break;
    		case R3:
    			addSequential(new DriveDistance(230));
    			addSequential(new TurnToAngleGyro(-90));
    			addParallel(new ShooterSetVoltageCommand(6));
    			addSequential(new DriveDistance(-136));
    			addSequential(new TurnToAngleGyro(-90));
    			addSequential(new DriveDistance(30));
    			addParallel(new TurretIRLockToTarget());
    			addSequential(new AgitatorCommand(State.FORWARD));
    			addSequential(new FeederCommand(State.FORWARD));
    			break;
    		case B3:
    			addSequential(new DriveDistance(230));
    			addSequential(new TurnToAngleGyro(90));
    			addParallel(new ShooterSetVoltageCommand(6));
    			addSequential(new DriveDistance(-136));
    			addSequential(new TurnToAngleGyro(90));
    			addSequential(new DriveDistance(30));
    			addParallel(new TurretIRLockToTarget());
    			addSequential(new AgitatorCommand(State.FORWARD));
    			addSequential(new FeederCommand(State.FORWARD));
    			break;
    		default:
    			break;
    	}
    }
    
   
    
}

