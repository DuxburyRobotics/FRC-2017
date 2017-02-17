package org.usfirst.frc.team4908.robot.Input;


import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;

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

    private AHRS navX;

    private double maxShooterSpeed;

    private double current;
    
    public SensorInput()
    {
        shooterEncoder = new Counter(0);
        shooterEncoder.setUpSource(9);
        shooterEncoder.setDistancePerPulse(1);
        shooterEncoder.setSamplesToAverage(1);
        shooterEncoder.setMaxPeriod(0.1);

        //intakeSwitch = new DigitalInput(-1);

        //navX = new AHRS(SerialPort.Port.kMXP);

//        navX.reset();
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

    // NAVX ============================================================================================================
    public void resetRotation()
    {
        navX.zeroYaw();
    }

    public double getYaw()
    {
        return navX.getYaw();
    }


}
