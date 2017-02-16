package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
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

    public AutoRotate(String type, RobotOutput ro, SensorInput si, double target)
    {
        super(type, ro, si);

        this.target = target;

        PID = new DuxPID(0.0, 0.0, 0.0, 0.01, 0.0);
    }

    public void init()
    {
        PID.reset();
        PID.setSetPoint(target);
    }

    public void update()
    {
        ro.setDriveMotors(0.0, PID.calculate(si.getYaw()));
    }

    public boolean isFinished()
    {
        return PID.isDone();
    }
}
