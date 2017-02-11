package org.usfirst.frc.team4908.robot.SubSystems;

import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

/**
 * Created by wills on 2/10/2017.
 */
public class Gear implements ISubSystem
{
    private RobotOutput ro;
    private DriverInput di;
    private SensorInput si;

    private boolean wasPressed = false;


    public Gear(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
    }

    public void calculate()
    {
        if(di.getGearButton() && !wasPressed)
        {
            wasPressed = true;
            ro.deployGear();
        }
        else if (!di.getGearButton())
        {
            ro.retractGear();
            wasPressed = false;
        }
    }

    public void disable()
    {

    }

    public RobotOutput getRo()
    {
        return null;
    }
}
