package org.usfirst.frc.team4908.robot.TeleOperation;

import org.usfirst.frc.team4908.robot.TeleOperation.Subsystems.*;
import java.util.ArrayList;

/**
 * @author Bill
 *         $ Contains array of all subsystems, calcultes output values for each every update
 */
public class TeleopComponents
{
    private ArrayList<ISubsystem> subsystems;

    public TeleopComponents()
    {
        subsystems = new ArrayList<ISubsystem>();

        subsystems.add(new Drive());
        subsystems.add(new Climb());
        subsystems.add(new Intake());
        subsystems.add(new Shooter());
    }

    public void update()
    {

        for(int i = 0; i < subsystems.size(); i++)
            subsystems.get(i).calculate();

    }

    public void disable()
    {
        for(int i = 0; i < subsystems.size(); i++)
            subsystems.get(i).disable();


    }


}
