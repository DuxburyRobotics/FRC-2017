package org.usfirst.frc.team4908.robot.SubSystems;

import org.usfirst.frc.team4908.robot.Input.*;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter implements ISubSystem
{
    private double targetRPM;

    private DriverInput di;
    private RobotOutput ro;
    private SensorInput si;
    private VisionInput vi;

    private DuxPID speedPID;
    private DuxPID rotatePID;

    private double setValue;

    private boolean isDown;
    private boolean wasDown;

    private int preSpeedCounter;
    private int rotatePIDCount;

    private static final double scaleFactor = 1.0;
    
    private boolean readyToShoot;

    private double speedSet;
    
    private double preSpeedTarget = 0.8;

    private double count;
    
    public Shooter(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi = vi;

        rotatePID = new DuxPID(0.0, 0.0, 0.0, 1, 180); // PID in degrees
        rotatePID.reset();

        speedPID = new DuxPID(6.5, 0.2, 1.25, 0.02, 83.0);
        speedPID.reset();
        setValue = 0.0;

        wasDown = false;
        
        count = 0;
        isDown = false;

        preSpeedCounter = 0;
        rotatePIDCount = 0;
        
        readyToShoot = false;
        
        Preferences.getInstance().putDouble("shooterTarget", 10.0);
    }
    
    public void init()
    {
    	/*
    	speedPID.setP(Preferences.getInstance().getDouble("shooterSpeedP", 0.0));
    	speedPID.setI(Preferences.getInstance().getDouble("shooterSpeedI", 0.0));
    	speedPID.setD(Preferences.getInstance().getDouble("shooterSpeedD", 0.0));
    	*/
    }

    
    
    public void calculate()
    {
        // TODO: Shift to low gear, add a spin up switch or some shit with SD
    	
    	
    	speedPID.setP(Preferences.getInstance().getDouble("shooterSpeedP", 0.0));
    	speedPID.setI(Preferences.getInstance().getDouble("shooterSpeedI", 0.0));
    	speedPID.setD(Preferences.getInstance().getDouble("shooterSpeedD", 0.0));
    	
    	
    	targetRPM = Preferences.getInstance().getDouble("shooterTarget", 0.0); // vi.getTargetSpeed(getTargetDistanceInches/12.0);
    	speedPID.setSetPoint(vi.getTargetSpeed(vi.getTargetDistanceInches()/12.0));
    	
        isDown = di.getShooterButton();
        count++; 
        
        if(isDown && !wasDown)
        {
        	//System.out.println("1");
        	speedPID.setSetPoint(30.0);
        	wasDown = true;
        }
        
        if(isDown && wasDown)
        {
        	//System.out.println("2");
        	setValue = speedPID.calculate(si.getShooterSpeed());
        	double rotateValue = rotatePID.calculate(si.getYaw());
        	
        	if(speedPID.isDone() && rotatePID.isDone())
        	{
        		//ro.setElevator(1.0);
        	}
        
        	if(count >= 75)
        	{
        		//ro.setElevator(1.0);
        	}
        	
        	//System.out.println(si.getYaw() + "loo");
        	
        	
        }
        
    
    	rotatePID.setP(Preferences.getInstance().getDouble("rotateP", 0.0));
    	rotatePID.setI(Preferences.getInstance().getDouble("rotateI", 0.0));
    	rotatePID.setD(Preferences.getInstance().getDouble("rotateD", 0.0));
    
    	if(!isDown)
        {
        	//System.out.println("3");
        	setValue= 0.0;
            //ro.setElevator(0.0);
 
            count = 0;
            
            wasDown = false;
            
        	speedPID.reset();
        }
               
        
        //ro.setShooter(0.8);
        //ro.setElevator(1.0);
    	
    	if(di.getShooterButton())
    	{
    		if(rotatePIDCount == 0)
    		{
    			double current = si.getYaw(); // 15
        		
        		double goal = current + vi.getTargetRotation(); 
        		
        		rotatePID.setSetPoint(goal);
        		
        		//rotatePID.reset();
        		
        		rotatePIDCount++;
    		}
    		
    		if(rotatePIDCount <= 100)
    		{
    			double val = -rotatePID.calculate(si.getYaw());
    			
    			if (val > .5)
    				val = .5;
    			else if (val < -.5)
    				val = -.5;
    			
    			ro.setDriveMotors(0.0, val);
    			
    			rotatePIDCount++;
    		}
    		if(rotatePIDCount >= 100)
    		{
    			double current = si.getYaw(); // 15
        		
        		double goal = current + vi.getTargetRotation(); 
        		
        		rotatePID.setSetPoint(goal);
        		
        		//rotatePID.reset();
        		
        		rotatePIDCount = 0;
    		
    		}
    		
    	}
    	
    	if(!di.getShooterButton())
    	{
    		rotatePIDCount = 0;
    	}
    	
    	
    }

    public void disable() {
        //ro.setShooter(0);
    }

    /** AUTO CODE BELOW **/

    /**
     * Used for auto, will turn on the shooter at the target RPM.
     * @param targetRPM the target RPM to run at.
     */
    public void activate(int targetRPM) {
        speedPID.reset();
        speedPID.setSetPoint(targetRPM);
        //ro.setShooter(speedPID.calculate(si.getShooterSpeed()));

    }


    public RobotOutput getRo()
    {
        return ro;
    }


}
