package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * @author Siggy
 *         $
 */
public class AutoGearDeposit extends ICommand
{
    private int count;

    public AutoGearDeposit(RobotOutput ro, SensorInput si)
    {
        super("Gear Deposit", ro, si);

        count = 0;
    }

    public void init()
    {
        //ro.deployGear();
    }

    public void update(double time)
    {
        count++;
    }

    public void finish()
    {
        //ro.retractGear();
    }

    public boolean isFinished()
    {
        if(count >= 10)
            return true;
        else
            return false;
    }
}