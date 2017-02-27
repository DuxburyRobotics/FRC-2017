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
public class Intake implements ISubSystem
{
    private RobotOutput ro;
    private DriverInput di;
    private SensorInput si;
    private VisionInput vi;

    private boolean wasPressed = false;

    private double val;

    public Intake(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi = vi;
    }

    
    public void init()
    {
    	
    }

    public void calculate()
    {    	
        // intake deploy/retract
        if(di.getIntakeDeployButton())
        {
        	//ro.deployIntake();
        }
       
        if(di.getIntakeRetractButton())
        {
        	//ro.retractIntake();
        }
        
        
        // motor values
        if(di.getIntakeButton())
        {
            val = 1.0;
        }
        else if(di.getIntakeReverseButton())
        {
        	val = -1.0;
        }
        else
        {
            val = 0.0;
        }

        //ro.setIntakeMotor(val);
    }

    public void disable()
    {

    }


    public RobotOutput getRo()
    {
        return ro;
    }

}

