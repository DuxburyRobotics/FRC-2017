
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team4908.robot.Teleoperation.TeleopComponents;

public class Robot extends IterativeRobot
{
    private TeleopComponents teleopComponents;

    public void robotInit()
    {
        teleopComponents = new TeleopComponents();
    }

    public void autonomousInit()
    {

    }

    public void autonomousPeriodic()
    {

    }

    public void teleopPeriodic()
    {
        teleopComponents.update();
    }

    public void testPeriodic()
    {
    
    }

    public void disabledInit()
    {
        teleopComponents.disable();
    }
    
}
