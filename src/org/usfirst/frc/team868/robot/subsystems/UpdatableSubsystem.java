package org.usfirst.frc.team868.robot.subsystems;

public interface UpdatableSubsystem {
	public void initSD();
	public void initDebugSD();
	public void updateSD();
	public void updateDebugSD();
	public boolean enableDebug();
}
