package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

/**
 * @author Siggy
 *         $
 */
public class AutoRotate extends ICommand
{
    private DuxPID PID;

    private double target;

    public AutoRotate(RobotOutput ro, SensorInput si, VisionInput vi, double target)
    {
        super("Drive Rotate Auto Helper", ro, si, vi);

        this.target = target;

        PID = new DuxPID(12.5, 0.5, 0.0, 1.0, 180);
    }

    public void init()
    {
        PID.reset();
        PID.setSetPoint(target);
    }

    public void update(double time)
    {
       ro.setDriveMotors(0.0, -PID.calculate(si.getYaw()));
    }

    public boolean isFinished()
    {
        return PID.isDone();
    }
}
