package org.usfirst.frc.team4908.robot.SubSystems;

import org.usfirst.frc.team4908.robot.Input.*;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

/**
 *
 */
public class Shooter implements ISubSystem
{
    private double targetRPM;

    private DriverInput di;
    private RobotOutput ro;
    private SensorInput si;

    private DuxPID PID;

    private double setValue;

    private boolean isDown;

    private boolean wasPressed;
    private boolean preSpeed;

    private int preSpeedCounter;

    private double preSpeedTarget = 0.5;

    public Shooter(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

        PID = new DuxPID(0.0, 0.0, 0.0, 0.02, si.getMaxShooterSpeed());
        setValue = 0.0;

        wasPressed = false;
        preSpeed = false;
        isDown = false;

        preSpeedCounter = 0;
    }

    public void calculate()
    {
        // TODO: Shift to low gear, add a spin up switch or some shit with SD

        targetRPM = 4908; // get value from vision tracking

        isDown = di.getShooterButton();

        if(isDown && !wasPressed) // PRE SPEED START
        {
            preSpeed = true;
            wasPressed = true;

            setValue = 0.0;
            preSpeedCounter = 0;
        }

        if(isDown && wasPressed && !preSpeed) // MAIN SET LOOP (after pre speed) - i placed this check before the pre speed loop so that it sets the motors to be equal to prespeedtarget once before starting pids
        {

            setValue = PID.calculate(si.getShooterSpeed());
        }

        if(isDown && preSpeed && wasPressed) // PRE SPEED LOOP
        {
            preSpeedCounter++;

            if (preSpeedCounter >= 5 && setValue <= preSpeedTarget) // increments setvalue
            {
                setValue += 0.1;
                preSpeedCounter = 0;
            }

            if (setValue >= preSpeedTarget) // ends prespeed
            {
                preSpeed = false;
                setValue = preSpeedTarget;

                PID.reset();
                PID.setSetPoint(targetRPM);
            }
        }

        if(!isDown) // RESET LOOP
        {
            preSpeed = false;
            wasPressed = false;
            setValue = 0.0;
        }

        if(si.getMaxShooterSpeed() >= PID.getMaxMotorValue())
        {
            PID.setMaxMotorValue(si.getMaxShooterSpeed());
        }

        ro.setShooter(setValue);
    }

    public void disable() {
        ro.setShooter(0);
    }

    /** AUTO CODE BELOW **/

    /**
     * Used for auto, will turn on the shooter at the target RPM.
     * @param targetRPM the target RPM to run at.
     */
    public void activate(int targetRPM) {
        PID.reset();
        PID.setSetPoint(targetRPM);
        ro.setShooter(PID.calculate(si.getShooterSpeed()));

    }


    public RobotOutput getRo()
    {
        return ro;
    }


}
