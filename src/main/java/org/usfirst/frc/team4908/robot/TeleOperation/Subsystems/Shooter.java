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

    public Shooter()
    {
        di = new DriverInput();
        si = new SensorInput();
        PID = new DuxPID(0.0, 0.0, 0.0, 0.02);
    }

    public void calculate()
    {
        // get value from vision tracking
        targetRPM = 4908;

        if(di.getShooterButton())
        {
            PID.reset();
            PID.setSetPoint(targetRPM);
            ro.setShooter(PID.calculate(si.getShooterSpeed()));
        }

    }

    public void disable() {

    }


    public void activateShooter() {

    }

    public void dissableShooter() {

    }


}
