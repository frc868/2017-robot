package org.usfirst.frc.team868.robot.commands;

import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;
import randomUtils.RecordMotorMovement;

/**
 *
 */
public class RecordMotorMovementHelper extends Command {
	
	String todo = "", fileLoc = "";
	boolean isFinished = false;

    public RecordMotorMovementHelper(String todo, String fileLoc) {
        this.todo = todo;
        this.fileLoc = fileLoc;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(todo.equals("saveFile")){
    		try {
				RecordMotorMovement.getInstance().saveFile(fileLoc);
			} catch (IOException e) {
			}
    		isFinished = true;
    	}else if(todo.equals("readFile")){
    		try {
				RecordMotorMovement.getInstance().readFile(fileLoc);
			} catch (IOException e) {
			}
    		isFinished = true;
    	}else if(todo.equals("record")){
    		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RecordMotorMovement.getInstance().RecordMotors();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
