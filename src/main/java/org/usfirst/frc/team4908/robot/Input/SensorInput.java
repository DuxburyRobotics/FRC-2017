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
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private Encoder shooterEncoder;
    private DigitalInput intakeSwitch;

    private double maxShooterSpeed;

    public SensorInput()
    {
        //shooterEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
        //intakeSwitch = new DigitalInput(-1);

        maxShooterSpeed = 6000;
    }

    public double getShooterSpeed()
    {
        if(false)//shooterEncoder.getRate() >= maxShooterSpeed;
            maxShooterSpeed = shooterEncoder.getRate();

        return 0.0;//shooterEncoder.getRate();
    }

    public boolean getIntakeSwitch()
    {
        return false; //intakeSwitch.get();
    }


    public double getMaxShooterSpeed()
    {
        return maxShooterSpeed;
    }
}
