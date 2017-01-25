package org.usfirst.frc.team4908.robot.Teleoperation;

import org.usfirst.frc.team4908.robot.Teleoperation.Subsystems.*;
import java.util.ArrayList;

/**
 * @author Bill
 *         $ Contains array of all subsystems, calcultes output values for each every update
 */
public class TeleopComponents
{
    ArrayList<ISubsystem> subsystems;

    public TeleopComponents()
    {
        subsystems = new ArrayList<>();

        subsystems.add(new Drive());
        subsystems.add(new Climb());
        subsystems.add(new Intake());
        subsystems.add(new Shooter());
    }

    public void update()
    {
        subsystems.forEach(ISubsystem::calculate);
    }

    public void disable()
    {
        subsystems.forEach(ISubsystem::disable);
    }
}
