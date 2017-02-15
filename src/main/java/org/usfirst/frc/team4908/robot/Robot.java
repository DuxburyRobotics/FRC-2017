package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class Robot extends IterativeRobot
{

    private NetworkTable table;


    @Override
    public void robotInit()
    {
        table = NetworkTable.getTable("database");

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic()
    {

        double x = 0;
        double y = 0;

        while (isOperatorControl() && isEnabled()) {
            Timer.delay(.25);
            table.putNumber("X Num _time", x);
            table.putNumber("Y Num _ iteration", y);
            x += .25;
            y += 1; //num iterations
        }

    }
}
