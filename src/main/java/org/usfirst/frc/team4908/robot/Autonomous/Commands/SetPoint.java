package org.usfirst.frc.team4908.robot.Autonomous.Commands;

/**
 * @author Siggy
 *         $
 */
public class SetPoint
{
    public double acceleration;
    public double velocity;
    public double position;

    public String toString()
    {
        return acceleration + " " + velocity + " " + position;
    }
}
