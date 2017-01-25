
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team4908.robot.Autonomus.AutoCommand;
import org.usfirst.frc.team4908.robot.TeleOperation.TeleopComponents;

public class Robot extends IterativeRobot
{
    private TeleopComponents teleopComponents;
    private AutoCommand autoCommand;

    public void robotInit()
    {
        teleopComponents = new TeleopComponents();
    }

    public void autonomousInit()
    {
        autoCommand.run();


    }

    public void autonomousPeriodic()
    {

        autoCommand.periodic();

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
