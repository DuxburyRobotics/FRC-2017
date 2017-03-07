
package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

public class AutoOpenRotate extends ICommand
{
	double runTime;
	double speed;
	double count;
	
	public AutoOpenRotate(RobotOutput ro, SensorInput si, VisionInput vi, double runTime, double speed)
	{
		super("Open rotate", ro, si, vi);
		this.runTime = runTime;
		this.speed = speed;
	}

	@Override
	public void init() 
	{
		count = 0;
		
	}
	
	public void update(double time)
	{
		count++;
		if(count < runTime)
			ro.setDriveMotors(0.0, speed/0.75);
		else
			ro.setDriveMotors(0.0, 0.0);
	}

	public boolean isFinished()
	{
		if(count < runTime)
			return false;
		else
			return true;
	}
}
