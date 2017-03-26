package org.usfirst.frc.team868.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerDrawSubsystem extends Subsystem { //TODO does this really need to be a Subsystem??

	PowerDistributionPanel pdp;
	DriverStation ds;
	private static PowerDrawSubsystem instance;
	
	private PowerDrawSubsystem() {
		pdp = new PowerDistributionPanel();
		ds = DriverStation.getInstance();
	}
	
	public static PowerDrawSubsystem getInstance() {
		if(instance == null) {
			instance = new PowerDrawSubsystem();
		}
		return instance;
	}
	
	public double getTotalCurrent() {
		return pdp.getTotalCurrent();
	}
	
	public double getDriveCurrent() {
		return  pdp.getCurrent(0) +
				pdp.getCurrent(1) +
				pdp.getCurrent(14) +
				pdp.getCurrent(15);
	}
	
	public double getVoltage() {
		return pdp.getVoltage();
	}
	
	public double getVoltageRemote() {
		return ds.getBatteryVoltage();
	}
	
	public boolean isLocalBrownout() {
		return getVoltage() < 6.8;
	}
	
	public boolean isRemoteBrownout() {
		return ds.isBrownedOut();
	}
	
	@Override
	protected void initDefaultCommand() {		
	}

	public void updateSD() {
		
	}

}
