package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * @author Siggy
 *         $
 */
public class AutoGearDeposit extends ICommand
{
    private int count;

    public AutoGearDeposit(RobotOutput ro, SensorInput si, VisionInput vi)
    {
        super("Gear Deposit", ro, si, vi);

        count = 0;
    }

    public void init()
    {
        ro.deployGear();
    }

    public void update(double time)
    {
        count++;
        if(count >= 17 && count <= 33)
        {
        	ro.setDriveMotors(0.5, 0.0);
        }
        else
        	ro.setDriveMotors(0.0, 0.0);
    }

    public void finish()
    {
        ro.retractGear();
    }

    public boolean isFinished()
    {
        if(count >= 50)
            return true;
        else
            return false;
    }
}