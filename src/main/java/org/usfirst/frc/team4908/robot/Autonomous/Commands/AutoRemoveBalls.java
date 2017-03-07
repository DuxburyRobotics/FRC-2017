package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * @author Siggy
 *         $
 */
public class AutoRemoveBalls extends ICommand
{
    private int count;

    public AutoRemoveBalls(RobotOutput ro, SensorInput si, VisionInput vi)
    {
        super("Remover", ro, si, vi);

        count = 0;
    }

    public void init()
    {
        ro.setShooter(-0.25);
        ro.setElevator(-0.25);
    }

    public void update(double time)
    {
        count++;
    }

    public void finish()
    {
    	ro.setElevator(0.0);
    	ro.setShooter(0.0);
    }

    public boolean isFinished()
    {
        if(count >= 25)
            return true;
        else
            return false;
    }
}