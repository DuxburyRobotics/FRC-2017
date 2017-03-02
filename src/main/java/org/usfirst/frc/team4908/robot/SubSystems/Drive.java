package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

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
    private RobotOutput ro;
    private VisionInput vi;

    private double driveX;
    private double driveRot;

    private boolean shifterWasPressed = false;
    private boolean isLow = true;


    public Drive(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi = vi;
        driveX = 0.0;
        driveRot = 0.0;
    }

    public void init()
    {
    	
    }
    
    // TELEOPS
    public void calculate()
    {
        driveX = -di.getDriveX();
        driveRot = -di.getDriveRot();
    
        if(di.getShifterButton())
        {
        	ro.setHighGear();
        }
        else
        	ro.setLowGear();
        
        
        // set high gear
        if(di.getShifterButton() && !shifterWasPressed && isLow)
        {
            shifterWasPressed = true;
            isLow = false;

//            ro.setHighGear();
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

  //          ro.setLowGear();
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
        {
            ro.setDriveMotors(0.0, 0.0);
        }
        
        //System.out.println(si.getLeftDriveSpeed() + "\t\t\t\t" + si.getRightDriveSpeed());
    }

    public void disable()
    {
        //ro.setDriveMotors(0.0,  0.0);
    }

    public RobotOutput getRo()
    {
        return ro;
    }

    // COMMANDS
    public void driveToDistance()
    {

    }
}
