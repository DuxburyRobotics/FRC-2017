package org.usfirst.frc.team4908.robot.Input;


import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**
 * @author Siggy
 *         $
 */
public class SensorInput
{
    private Encoder shooterEncoder;
    private DigitalInput intakeSwitch;

    public SensorInput()
    {
        //shooterEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
        //intakeSwitch = new DigitalInput(-1);
    }

    public double getShooterSpeed()
    {
        return 0.0;//shooterEncoder.getRate();
    }

    public boolean getIntakeSwitch()
    {
        return false; //intakeSwitch.get();
    }
}
