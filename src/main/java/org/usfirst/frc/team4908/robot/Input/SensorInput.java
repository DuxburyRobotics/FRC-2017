package org.usfirst.frc.team4908.robot.Input;


import edu.wpi.first.wpilibj.Counter;
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

    private Counter shooterEncoder;
    private DigitalInput intakeSwitch;

    private double maxShooterSpeed;

    private double current;
    
    public SensorInput()
    {
        shooterEncoder = new Counter(0);
        
        shooterEncoder.setUpSource(9);
        shooterEncoder.setDistancePerPulse(1);
       //shooterEncoder.setReverseDirection(true);
        shooterEncoder.setSamplesToAverage(1);
        
        //shooterEncoder.setDownSourceEdge(true, true);
        
        //intakeSwitch = new DigitalInput(-1);

        maxShooterSpeed = 60.0;
    }

    public double getShooterSpeed()
    {
    	current = shooterEncoder.getRate();
    	
        if(current >= maxShooterSpeed);
            maxShooterSpeed = current;

        return current;
    }
    
    public double getShooterCount()
    {
    	return shooterEncoder.get();
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
