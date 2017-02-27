package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Climb implements ISubSystem
{
    private RobotOutput ro;
    private DriverInput di;
    private SensorInput si;
    private VisionInput vi;

    private double setVal;

    public Climb(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi= vi;

    }

    public void init()
    {
    	
    }

    public void calculate()
    {
        if(di.getClimbButton())
        {
        	setVal = 1.0;
        }
        else
        {
            setVal = 0.0;
        }

        //ro.setClimbMotor(setVal);

    }

    public void disable()
    {
        //ro.setClimbMotor(0.0);

    }

    public RobotOutput getRo()
    {
        return ro;
    }
}
