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
    //private Joystick operatorJoystick = new Joystick(2);



    // driver input
    public double getDriveX()
    {
        return driverLeftStick.getRawAxis(1);
    }

    public double getDriveRot()
    {
        return driverRightStick.getRawAxis(0);
    }

    public boolean getShifterButton() {return driverLeftStick.getRawButton(1); }

    // operator input
    public boolean getShooterButton()
    {
        return driverRightStick.getRawButton(1);
    }

    public boolean getClimbButton()
    {
        return false; // operatorJoystick.getRawButton(2);
    }

    public boolean getIntakeToggleButton()
    {
        return false; // operatorJoystick.getRawButton(3);
    }

    public boolean getIntakeEnableButton()
    {
        return false; //operatorJoystick.getRawButton(4);
    }

    public boolean getGearButton()
    {
        return false; //operatorJoystick.getRawButton(5);
    }
}
