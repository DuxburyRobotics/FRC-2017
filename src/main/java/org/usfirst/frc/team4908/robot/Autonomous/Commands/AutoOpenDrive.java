package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

public class AutoOpenDrive extends ICommand
{
	double distance;
	double speed;
	
	double count;
	
	double yaws;
	 
	boolean correct;
	
	double rotateVal;
	
	public AutoOpenDrive(RobotOutput ro, SensorInput si, VisionInput vi, double distance, double speed, boolean correct)
	{
		super("OpenDrive", ro, si, vi);

		this.distance = distance;
		this.speed = speed;
		count = 0;
		
		yaws = si.getYaw();
		
		this.correct = correct;
	}
	
	public AutoOpenDrive(RobotOutput ro, SensorInput si, VisionInput vi, double distance, double speed)
	{
		this(ro, si, vi, distance, speed, false);
	}

	@Override
	public void init() 
	{
		ro.setLowGear();
		count = 0;		

		yaws = si.getYaw();
		rotateVal = 0.0;
	}

	
	
	public void update(double time)
	{
		
		double currentOffset = si.getYaw() - yaws;
		
		count++;
		if(count <= distance)
		{
			if(correct)
				rotateVal = (0.04*currentOffset);
			else
				rotateVal = 0.0;
			
			ro.setDriveMotors(speed, rotateVal);
		}
		else
		{
			ro.setDriveMotors(0.0, 0.0);
		}
		
		System.out.println("yaws: " + yaws + "\t\t\tcurrentOffset: " + currentOffset + "\t\t\trotateVal: " + rotateVal);
	}
	
	public boolean isFinished()
	{
		if(count <= distance)
			return false;
		else
			return true;
		
	}

}
