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

    public boolean getShifterButton() {return driverLeftStick.getRawButton(1); }

    // operator input
    public boolean getShooterButton()
    {
    	return operatorJoystick.getRawButton(6);
    }

    public boolean getVisionButton()
    {
    	return operatorJoystick.getRawButton(5);
    }

    public double getIntakeValue()
    {
        return (operatorJoystick.getRawAxis(2) - operatorJoystick.getRawAxis(3));
    }

    public boolean getRunIntake()
    {
        return (Math.abs(getIntakeValue()) >= 0.1);
    }
    
    public boolean getShooterReverseButton()
    {
    	return operatorJoystick.getRawButton(7);
    }

    public boolean getIntakeReverseButton()
    {
    	return operatorJoystick.getRawButton(8);
    }
    
    public boolean getIntakeDeployButton()
    {
        return operatorJoystick.getRawButton(2);
    }
    public boolean getIntakeRetractButton()
    {
    	return operatorJoystick.getRawButton(3);
    }

    public boolean getClimbButton()
    {
        return operatorJoystick.getRawButton(4);
    }

    public boolean getGearButton()
    {
        return operatorJoystick.getRawButton(1);
    }
}
