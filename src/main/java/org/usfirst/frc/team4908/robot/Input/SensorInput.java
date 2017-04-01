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

    private AHRS navX;

    public SensorInput()
    {

        rightEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
        rightEncoder.setDistancePerPulse((Math.PI*4.0625/360.0)/12.0);
        
        leftEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
        leftEncoder.setDistancePerPulse((Math.PI*4.0625/360.0)/12.0);
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
