package org.usfirst.frc.team4908.robot.Input;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Siggy
 *         $
 */
public class DriverInput
{
    private Joystick driverLeftStick = new Joystick(0);
    private Joystick driverRightStick = new Joystick(1);
    private Joystick operatorJoystick = new Joystick(2);


    // TODO: Fix these values because they're for sure wrong

    // driver input
    public double getDriveX()
    {
        return driverLeftStick.getRawAxis(0);
    }

    public double getDriveRot()
    {
        return driverRightStick.getRawAxis(1);
    }

    // operator input
    public boolean getShooterButton()
    {
        return operatorJoystick.getRawButton(1);
    }
}
