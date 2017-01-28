package org.usfirst.frc.team4908.robot.Input;


import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;

/**
 * @author Siggy
 *         $
 */
public class SensorInput
{
    private Encoder shooterEncoder;

    public SensorInput()
    {
        shooterEncoder = new Encoder(0, 1, false, CounterBase.EncodingType.k4X);
    }

    public double getShooterSpeed()
    {
        return shooterEncoder.getRate();
    }

}
