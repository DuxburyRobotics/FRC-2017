package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class Robot extends IterativeRobot
{
    private SpeedController controller1;
    private Joystick input;

    public void robotInit()
    {
        controller1 = new VictorSP(0);
        input = new Joystick(0);
    }

    public void teleopPeriodic()
    {
        controller1.set(input.getRawAxis(2)-input.getRawAxis(3));
    }
}
