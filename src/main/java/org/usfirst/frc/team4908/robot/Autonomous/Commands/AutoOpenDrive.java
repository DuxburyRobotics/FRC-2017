package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

public class AutoOpenDrive extends ICommand
{
	double distance;
	double speed;
	
	double count;
	
	public AutoOpenDrive(RobotOutput ro, SensorInput si, VisionInput vi, double distance, double speed)
	{
		super("OpenDrive", ro, si, vi);

		this.distance = distance;
		this.speed = speed;
		count = 0;
	}
	
	public void update(double time)
	{
		count++;
		if(count <= distance)
		{
			ro.setDriveMotors(speed, 0.0);
		}
		else
		{
			ro.setDriveMotors(0.0, 0.0);
		}
	}

	@Override
	public void init() 
	{
		ro.setLowGear();
		count = 0;
		
	}
	
	public boolean isFinished()
	{
		if(count <= distance)
			return false;
		else
			return true;
		
	}

}
