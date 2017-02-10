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

    public Shooter(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

        PID = new DuxPID(0.0, 0.0, 0.0, 0.02);
        setValue = 0.0;

    }

    public void calculate()
    {

        targetRPM = 4908; // get value from vision tracking

        if(di.getShooterButton())
        {
            PID.reset();
            PID.setSetPoint(targetRPM);
            setValue = PID.calculate(si.getShooterSpeed());
        }
        else if(!di.getShooterButton())
        {
            setValue = 0.0;
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



}
