
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot
{
    Joystick xBox;

    double shooter;

    SpeedController one;
    SpeedController two;

    public void robotInit()
    {
        xBox = new Joystick(0);
        shooter = 0.0;

        one = new Spark(0);
        two = new Spark(1);
    }

    public void autonomousInit()
    {

    }

    public void autonomousPeriodic()
    {

    }

    public void teleopPeriodic()
    {
        one.set(shooter);
        two.set(shooter);

        if(xBox.getRawButton(0))
        {
            shooter += .05;
        }

        if(xBox.getRawButton(0))
        {
            shooter -= .05;
        }

    }

    public void testPeriodic()
    {
    
    }

    public void disabledInit()
    {

    }
    
}
