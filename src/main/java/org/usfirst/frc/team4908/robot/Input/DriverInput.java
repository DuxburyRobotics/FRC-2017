package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Siggy
 *         $
 */
public class DriverInput
{
    private Joystick driverLeftStick = new Joystick(1);
    private Joystick driverRightStick = new Joystick(0);
    private Joystick operatorJoystick = new Joystick(2);

    // driver input
    public double getDriveX()
    {
        return driverLeftStick.getRawAxis(1);
    }

    public double getDriveRot()
    {
        return driverRightStick.getRawAxis(2);
    }

    // operator input


}
