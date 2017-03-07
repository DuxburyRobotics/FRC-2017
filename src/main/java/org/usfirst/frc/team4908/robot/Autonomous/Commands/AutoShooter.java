package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoShooter extends ICommand {

    private DuxPID speedPID;
    private DuxPID rotatePID;
    private double setValue;

    private VisionInput vi;
    
    private double visionShooterCount;
    private double PIDResetCount;
    
    private double resetThreshold;
    
    public AutoShooter(RobotOutput ro, SensorInput si, VisionInput vi) {
        super("Shooter", ro, si, vi);
        
        this.vi = vi;

        speedPID = new DuxPID(3.0, 0.1, 2.0, 1.0, 83.0);
        rotatePID = new DuxPID(12.5, 0.5, 0.0, 1.0, 180);
    }
  

    //region Auto Code

    @Override
    public void init() 
    {
        //TODO: Vision code will go here

    	visionShooterCount = 0;
    	PIDResetCount = 0;
 
    	resetThreshold = Preferences.getInstance().getDouble("resetThreshold", 0.0);
    	
    	speedPID.setP(0.0);
    	speedPID.setI(0.0);
        speedPID.setD(0.0);
        
    	rotatePID.setP(0.0);
    	rotatePID.setI(0.0);
    	rotatePID.setD(0.0);
    
    	speedPID.setSetPoint(vi.getTargetSpeed(vi.getTargetDistanceInches()/12.0));
    	speedPID.reset();
    	
    	rotatePID.setSetPoint(si.getYaw() + vi.getTargetRotation());
    	rotatePID.reset();
    }

    public void update() 
    {
        visionShooterCount++;
        PIDResetCount++;

        ro.setDriveMotors(0.0, rotatePID.calculate(si.getYaw()));
        ro.setShooter(speedPID.calculate(si.getShooterSpeed()));

        if((rotatePID.isDone() || (visionShooterCount > 250)) && (speedPID.isDone() || (visionShooterCount > 150)))
        {
        	ro.setElevator(0.8);
        	ro.setShaker(1.0);
        	visionShooterCount = 0;
        }

        PIDResetCount++;
        if(PIDResetCount >= 1000)
        {
            rotatePID.setSetPoint(vi.getTargetRotation());
            speedPID.setSetPoint(vi.getTargetSpeed(vi.getTargetDistanceInches()/12.0));
            PIDResetCount = 0;
        }

    	
    }

    public void finish()
    {
        ro.setShooter(0);
        ro.setElevator(0.0);
        ro.setShaker(0.0);
        ro.setDriveMotors(0.0, 0.0);
    }

    //endregion




}
