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
        //default values

        double x = 0.0;
        double y = 0.0;
        table.putNumber("X Num _time", x);
        table.putNumber("Y Num _ iteration", y);

    }

    @Override
    public void teleopPeriodic()
    {

        double x = 0.0;
        double y = 0.0;

        Timer.delay(.25);
        table.putNumber("X Num _time", x);
        table.putNumber("Y Num _ iteration", y);
        x += .25;
        y += 1; //num iterations


        System.out.println("X value:" + table.getNumber("X Num _time", 0.0) + "\nY Value" +
                table.getNumber("Y Num _ iteration", 0.0));

    }
}
