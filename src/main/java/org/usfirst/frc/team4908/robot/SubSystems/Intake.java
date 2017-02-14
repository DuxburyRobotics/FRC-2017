package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

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

    private boolean wasPressed = false;

    private double val;

    public Intake(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

    }


    public void calculate()
    {
        // intake deploy/retract
        if(di.getIntakeToggleButton() && !wasPressed && si.getIntakeSwitch())
        {
            wasPressed = true;

            ro.deployIntake();
        }
        else if(di.getIntakeToggleButton() && !wasPressed && !si.getIntakeSwitch())
        {
            wasPressed = true;

            ro.retractIntake();
        }
        else if(!di.getShifterButton())
        {
            wasPressed = false;
        }

        // motor values
        if(di.getIntakeEnableButton())
        {
            val = 1.0;
        }
        else
        {
            val = 0.0;
        }

        ro.setIntakeMotor(val);
    }

    public void disable()
    {

    }


    public RobotOutput getRo()
    {
        return ro;
    }

}

