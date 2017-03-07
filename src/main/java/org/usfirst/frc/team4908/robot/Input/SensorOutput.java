package org.usfirst.frc.team4908.robot.Input;

import java.text.DecimalFormat;

public class SensorOutput 
{
	private SensorInput si;
	private DecimalFormat df;
	
	// temp
	private double maxLeft;
	private double maxRight;
	
	public SensorOutput(SensorInput si)
	{
		this.si = si;
		df = new DecimalFormat("0.000");
		
		maxLeft = 0.0;
		maxRight = 0.0;
	}
	
	
	public void update()
	{
		// drive speed
		//System.out.println(df.format(si.getLeftDriveSpeed()) + "\t\t\t\t\t" + df.format(si.getRightDriveSpeed()));
		
		// max drive speed
		if(maxLeft <= si.getLeftDriveSpeed())
		{
			maxLeft = si.getLeftDriveSpeed();
			
			//System.out.println(df.format(maxLeft) + "\t\t\t\t\t" + df.format(maxRight));
		}
		
		if(maxRight <= si.getRightDriveSpeed())
		{
			maxRight = si.getRightDriveSpeed();
		
			//System.out.println(df.format(maxLeft) + "\t\t\t\t\t" + df.format(maxRight));
		}
		
		// shooter speed and count
		// System.out.println(df.format(si.getShooterSpeed()) + "\t\t\t\t\t" + si.getShooterCount());
	}
}