package org.usfirst.frc.team4908.robot.Util;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This file is used for things that will never change or if they do change they will be universal across the robot.
 * Please, for the love of God! Look at the fridgen' example!
 */
public class Constants 
{	
	private SmartDashboard sd;
	
	private Preferences pf;
	
	public Constants(SmartDashboard sd)
	{
		this.sd = sd;
		
		pf = Preferences.getInstance();
		
		SmartDashboard.putNumber("shooterSpeedP", 0.0);
		SmartDashboard.putNumber("shooterSpeedI", 0.0);
		SmartDashboard.putNumber("shooterSpeedD", 0.0);
		
	}
	
    public static class Buttons {

    }

    public static class LimitSwitches {


    }

    public static class Motors {


    }

    public static class Misc {


        /**
         * Please note the public static final. Also note the all caps! This is important! Use this when creating
         * these constant variables or else!
         */
        public static final int THIS_IS_A_EXAMPLE = 4908; // the bestest team ever


    }

    public static class PIDs
    {
    
    }
    
	public double shooterSpeedP = 0.0;
	public double shooterSpeedI = 0.0;
	public double shooterSpeedD = 0.0;

	public double getShooterSpeedP()
	{
		return pf.getDouble("shooterSpeedP", 0.0);
	}
	
	public double getShooterSpeedI()
	{
		return pf.getDouble("shooterSpeedI", 0.0);
	}
	
	public double getShooterSpeedD()
	{
		return pf.getDouble("shooterSpeedD", 0.0);
	}
}