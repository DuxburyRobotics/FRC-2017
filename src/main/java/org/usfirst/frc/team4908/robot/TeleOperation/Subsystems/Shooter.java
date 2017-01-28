package org.usfirst.frc.team4908.robot.TeleOperation.Subsystems;

import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.TeleOperation.RobotOutput;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

/**
 *
 */
public class Shooter implements ISubsystem
{
    private double targetRPM;
    DriverInput di;
    RobotOutput ro;
    SensorInput si;
    DuxPID PID;

    private double setValue;

    public Shooter()
    {
        di = new DriverInput();
        si = new SensorInput();
        PID = new DuxPID(0.0, 0.0, 0.0, 0.02);
        setValue = 0.0;
    }

    public void calculate()
    {
        // get value from vision tracking
        targetRPM = 4908;

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

    }


    public void activateShooter() {

    }

    public void dissableShooter() {

    }


}
