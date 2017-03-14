package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

public class AutoOpenDrive extends ICommand
{
	double distance;
	double count;
  String[] runLines;
	
	public AutoOpenDrive(RobotOutput ro, SensorInput si, VisionInput vi, String[] runLines)
	{
		super("OpenDrive", ro, si, vi);

		this.distance = runLines.length();
    this.runLines = runLines;
		count = 0;
	}
	
	public void update(double time)
	{

		if(count <= distance)
		{
      String[] vars = runLines[count].split(":");      
			ro.setDriveMotors(vars[0],vars[0]);
		}
		else
		{
			ro.setDriveMotors(0.0, 0.0);
		}
    count++;
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
