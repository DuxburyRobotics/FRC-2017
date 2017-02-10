package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Drive implements ISubSystem
{

    private DriverInput di;
    private SensorInput si;

    private double driveX;
    private double driveRot;

    private boolean shifterWasPressed = false;
    private boolean isLow = true;


    private RobotOutput ro;

    public Drive(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

        driveX = 0.0;
        driveRot = 0.0;
    }

    // TELEOPS
    public void calculate()
    {
        driveX = -di.getDriveX();
        driveRot = -di.getDriveRot();

        // set high gear
        if(di.getShifterButton() && !shifterWasPressed && isLow)
        {
            shifterWasPressed = true;
            isLow = false;

            ro.setHighGear(true);
        }
        else if(!di.getShifterButton())
        {
            shifterWasPressed = false;
        }

        // set low gear
        if(di.getShifterButton() && !shifterWasPressed && !isLow)
        {
            shifterWasPressed = true;
            isLow = true;

            ro.setHighGear(false);
        }
        else if(!di.getShifterButton())
        {
            shifterWasPressed = false;
        }


        if(Math.abs(driveX) >= 0.05 || Math.abs(driveRot) >= 0.05 ) // check if out of deadzone
        {
            // square x value
            if(driveX < 0.0)
            {
                driveX = Math.pow(driveX, 2.0)*-1.0;
            }
            else if(driveX > 0.0)
            {
                driveX = Math.pow(driveX, 2.0);
            }

            // square rot values
            if(driveRot < 0.0)
            {
                driveRot = Math.pow(driveRot, 2.0)*-1.0;
            }
            else if (driveRot > 0.0)
            {
                driveRot = Math.pow(driveRot, 2.0);
            }

            ro.setDriveMotors(driveX, driveRot);
        }
        else
        	ro.setDriveMotors(0.0,  0.0);

    }

    public void disable()
    {
        ro.setDriveMotors(0.0,  0.0);
    }


    // COMMANDS
    public void driveToDistance()
    {

    }
}
