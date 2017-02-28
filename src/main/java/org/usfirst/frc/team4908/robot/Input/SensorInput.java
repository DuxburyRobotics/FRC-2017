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
    private DigitalInput shakerSwitch;

    private AHRS navX;

    private double maxShooterSpeed;

    private double current;
    
    public SensorInput()
    {
        shooterEncoder = new Counter(0);
        shooterEncoder.setUpSource(4);
        shooterEncoder.setUpSourceEdge(true, false);
        shooterEncoder.setDistancePerPulse(1);
        shooterEncoder.setSamplesToAverage(1);
        shooterEncoder.setMaxPeriod(0.1);

        rightEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
        rightEncoder.setDistancePerPulse((Math.PI*4.0625/360.0)/12.0);
        
        leftEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
        leftEncoder.setDistancePerPulse((Math.PI*4.0625/360.0)/12.0);
        
        //sensorSwitch = new DigitalInput(-1);

        navX = new AHRS(SerialPort.Port.kUSB1);

        navX.reset();
    }
    
    public double getLeftDriveSpeed()
    {
    	return leftEncoder.getRate();
    }
    
    public double getRightDriveSpeed()
    {
    	return rightEncoder.getRate();
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

    public boolean getShakerSwitch()
    {
        return false;//shakerSwitch.get();
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
