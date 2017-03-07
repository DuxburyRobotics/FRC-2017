package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * @author Siggy
 *         $
 */
public class AutoIntake extends ICommand
{
    private int count;

    public AutoIntake(RobotOutput ro, SensorInput si, VisionInput vi)
    {
        super("Intake", ro, si, vi);

        count = 0;
    }

    public void init()
    {
        ro.deployIntake();
    }

    public void update(double time)
    {
        count++;
    }

    public void finish()
    {
    }

    public boolean isFinished()
    {
        if(count >= 5)
            return true;
        else
            return false;
    }
}