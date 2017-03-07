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

    private double shooterVal;
    private double elevatorVal;
    private double shakerVal;

    private double setValue;

    private boolean isShooterDown;
    private boolean wasShooterDown;
    private boolean isVisionDown;
    private boolean wasVisionDown;

    private int preSpeedCounter;
    private int rotatePIDCount;

    private static final double scaleFactor = 1.0;
    
    private boolean readyToShoot;
    private double speedSet;
    
    private double preSpeedTarget = 0.8;

    private double visionShooterCount;
    private double noVisionShooterCount;

    private double PIDResetCount;
    
    public Shooter(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi = vi;

        rotatePID = new DuxPID(12.5, 0.5, 0.0, 1.0, 180); // PID in degrees
        rotatePID.reset();

        speedPID = new DuxPID(3.0, 0.1, 2.0, 1.0, 83.0);
        speedPID.reset();
        setValue = 0.0;

        wasShooterDown = false;
        
        noVisionShooterCount = 0;
        isShooterDown = false;

        preSpeedCounter = 0;
        rotatePIDCount = 0;
        
        readyToShoot = false;
        
    	Preferences.getInstance().putDouble("RP", 0.0);
    	Preferences.getInstance().putDouble("RI", 0.0);
    	Preferences.getInstance().putDouble("RD", 0.0);
        
        Preferences.getInstance().putDouble("shooterTarget", 10.0);
    }
    
    public void init()
    {
    	rotatePID.setP(Preferences.getInstance().getDouble("RP", 0.0));
    	rotatePID.setI(Preferences.getInstance().getDouble("RI", 0.0));
    	rotatePID.setD(Preferences.getInstance().getDouble("RD", 0.0));
    }

    
    
    public void calculate()
    {
    	isShooterDown = di.getShooterButton();
        isVisionDown = di.getVisionButton();

        if((isVisionDown || isShooterDown) && !wasShooterDown) { ro.setLowGear(); }
        // region SHOOT WITH NO VISION
        if(isShooterDown && !isVisionDown)
        {

            if(!wasShooterDown)
            {
                speedPID.reset();
                speedPID.setSetPoint(54.0);
            }
            
            if(wasShooterDown)
            {
            	System.out.println(si.getShooterSpeed());
            	
                shooterVal = speedPID.calculate(si.getShooterSpeed());

                if(speedPID.isDone() || (noVisionShooterCount >= 50))
                {
                    elevatorVal = 0.8;
                    shakerVal = 1.0;
                }
                else
                {
                    noVisionShooterCount++;
                }
            }
        }

        if(!isShooterDown)
        {
            noVisionShooterCount = 0;
            speedPID.reset();
            speedPID.setSetPoint(0.0);
        }
        // endregion

        // region SHOOTER WITH VISION
        if(isShooterDown && isVisionDown)
        {
            if(!wasShooterDown)
            {
            	rotatePID.reset();
                rotatePID.setSetPoint(vi.getTargetRotation());

                speedPID.reset();
                speedPID.setSetPoint(vi.getTargetSpeed((vi.getTargetDistanceInches()-17.0)/12.0));
            }

            if(wasShooterDown)
            {
                visionShooterCount++;

                ro.setDriveMotors(0.0, -rotatePID.calculate(si.getYaw()));
                shooterVal = speedPID.calculate(si.getShooterSpeed());

                if((rotatePID.isDone() || (visionShooterCount > 250)) && (speedPID.isDone() || (visionShooterCount > 150)))
                {
                    elevatorVal = 0.8;
                    shakerVal = 1.0;
                    visionShooterCount = 0;
                }

                PIDResetCount++;
                if(PIDResetCount >= 1000)
                {
                    rotatePID.setSetPoint(vi.getTargetRotation());
                    speedPID.setSetPoint(vi.getTargetSpeed((vi.getTargetDistanceInches()-17.0)/12.0));
                    PIDResetCount = 0;
                }
            }
        }
        // endregion

        // region SHAKER RESET
        if(!isShooterDown && !isVisionDown && si.getShakerSwitch())
        {
        	System.out.println("here");
            shakerVal = 0.5;
        }
        else if(!isShooterDown && !isVisionDown && !si.getShakerSwitch())
        {
            shakerVal = 0.0;
        }
        // endregion
        
        if(di.getShooterReverseButton())
        {
        	elevatorVal = -0.25;
        	shooterVal = -0.25;
        }


        if(!isShooterDown && !di.getShooterReverseButton())
        {
            shooterVal = 0.0;
            elevatorVal = 0.0;
        }

        // WAS DOWN CHECK
        wasShooterDown = isShooterDown;
        wasVisionDown = isVisionDown;

        // ROBOT OUTPUTS
        ro.setShooter(shooterVal);
        ro.setElevator(elevatorVal);
        ro.setShaker(shakerVal);

        // region OLD
    	/*
    	// FEEDER
        if (!isShooterDown && si.getShakerSwitch())
        {
            ro.setShaker(0.5);
        }
        else
        {
        	ro.setShaker(0.0);
        }
    	
    	
        // TODO: Shift to low gear, add a spin up switch or some shit with SD

    	
    	speedPID.setP(Preferences.getInstance().getDouble("shooterSpeedP", 0.0));
    	speedPID.setI(Preferences.getInstance().getDouble("shooterSpeedI", 0.0));
    	speedPID.setD(Preferences.getInstance().getDouble("shooterSpeedD", 0.0));

    	targetRPM = Preferences.getInstance().getDouble("shooterTarget", 0.0); // vi.getTargetSpeed(getTargetDistanceInches/12.0);
    	//speedPID.setSetPoint(vi.getTargetSpeed(vi.getTargetDistanceInches()/12.0));
    	
        isShooterDown = di.getShooterButton();
        count++; 
        
        if(isShooterDown && !wasShooterDown)
        {
        	//System.out.println("1");
        	speedPID.setSetPoint(targetRPM);
        	wasShooterDown = true;
        }
        
        if(isShooterDown && wasShooterDown)
        {
        	//System.out.println("2");
        	setValue = speedPID.calculate(si.getShooterSpeed());
        
        	System.out.println(si.getShooterSpeed());
        	
        	//ro.setShooter(setValue);
        	
        	if(rotatePID.isDone())// && speedPID.isDone())
        	{
        		System.out.println("\n\n\n\n\n");
        		ro.setElevator(1.0);
        		ro.setShaker(1.0);
        	}
        
        	if(count >= 75)
        	{
        		//ro.setElevator(1.0);
        	}
        	
        	//System.out.println(si.getYaw() + "loo");
        }
        
    
    	rotatePID.setP(Preferences.getInstance().getDouble("rotateP", 20.0));
    	rotatePID.setI(Preferences.getInstance().getDouble("rotateI", 0.5));
    	rotatePID.setD(Preferences.getInstance().getDouble("rotateD", 8.0));
    
    	if(!isShooterDown)
        {
        	//System.out.println("3");
        	setValue= 0.0;
            ro.setElevator(0.0);
 
            count = 0;
            
            wasShooterDown = false;
            
        	speedPID.reset();
        
        }
               
        
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
    			
    			ro.setDriveMotors(0.0, val); // THING THING THING THING THING THING THING
    			
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
    		
    		
    		if(rotatePID.isDone())
    		{
    			ro.setShooter(targetRPM/83.0);
    	        ro.setElevator(1.0);
    	    	ro.setShaker(1.0);
    	   	}	
    		
    	}
    	
    	if(!di.getShooterButton())
    	{
    		rotatePIDCount = 0;
    	}
    	
    	

        */
        // endregion
    }

    public void disable() {
        //ro.setShooter(0);
    }

    /** AUTO CODE BELOW **/

    /**
     * Used for auto, will turn on the shooter at the target RPM.
     *
    public void activate(int targetRPM) {
        speedPID.reset();
        speedPID.setSetPoint(targetRPM);
        //ro.setShooter(speedPID.calculate(si.getShooterSpeed()));

    } */


    public RobotOutput getRo()
    {
        return ro;
    }


}
