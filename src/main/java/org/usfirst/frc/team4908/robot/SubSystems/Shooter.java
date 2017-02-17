package org.usfirst.frc.team4908.robot.SubSystems;

import org.usfirst.frc.team4908.robot.Input.*;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

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

    private boolean wasPressed;
    private boolean preSpeed;

    private int preSpeedCounter;
    private int rotatePIDCount;

    private boolean readyToShoot;

    private double preSpeedTarget = 1.0;

    public Shooter(DriverInput di, SensorInput si, RobotOutput ro, VisionInput vi)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;
        this.vi = vi;

        rotatePID = new DuxPID(0.0, 0.0, 0.0, 1, 180); // PID in degrees
        rotatePID.reset();

        speedPID = new DuxPID(1.0, 0.0, 0.0, 0.02, 75.0);
        speedPID.reset();
        setValue = 0.0;

        wasPressed = false;
        preSpeed = false;
        isDown = false;

        preSpeedCounter = 0;
        rotatePIDCount = 0;

        readyToShoot = false;
    }

    public void calculate()
    {
        // TODO: Shift to low gear, add a spin up switch or some shit with SD

        targetRPM = 50.0; // vi.getTargetDistance

        isDown = di.getShooterButton();
        
        if(isDown && !wasPressed) // PRE SPEED START
        {
            preSpeed = true;
            wasPressed = true;

            setValue = 0.0;
            preSpeedCounter = 0;
            rotatePIDCount = 0;
        }

        if(isDown && wasPressed && !preSpeed) // MAIN SET LOOP (after pre speed) - i placed this check before the pre speed loop so that it sets the motors to be equal to prespeedtarget once before starting pids
        {

            setValue = preSpeedTarget;//speedPID.calculate(si.getShooterSpeed());
        }

        if(isDown && preSpeed && wasPressed) // PRE SPEED LOOP
        {
            preSpeedCounter++;

            if (preSpeedCounter >= 5 && setValue <= preSpeedTarget) // increments setvalue
            {
                setValue += 0.1;
                preSpeedCounter = 0;
            }

            if (setValue >= preSpeedTarget) // ends prespeed
            {
                preSpeed = false;
                setValue = preSpeedTarget;

                speedPID.reset();
                speedPID.setSetPoint(targetRPM);
            }
        }

        if(!isDown) // RESET LOOP
        {
            preSpeed = false;
            wasPressed = false;
            setValue = 0.0;
        }

        if(isDown) // rotation for shooting
        {
            // region PIDset
            if(rotatePIDCount == 0)
            {
                rotatePID.setSetPoint(vi.getTargetRotation());
            }

            if (rotatePIDCount < 50)
            {
                rotatePIDCount++;
            }
            else if(!readyToShoot)
            {
                rotatePID.reset();
                rotatePID.setSetPoint(vi.getTargetRotation());
                rotatePIDCount = 0;
            }
            // endregion PIDset

            // region isDoneCheck
            if (speedPID.isDone())
            {
            	System.out.println("heya");
                readyToShoot = true;
            }
            else
            {
                readyToShoot = false;
            }
            // endregion isDoneCheck

            if(!readyToShoot)
            {
                //ro.setDriveMotors(0.0, rotatePID.calculate(si.getYaw()));
            }
            else if(readyToShoot)
            {
                ro.setDriveMotors(0,0);
                
                System.out.println("heya");
                
                ro.setElevator(.5);
                // elevator full speed
            }

        }

        System.out.println(si.getShooterSpeed());

        ro.setShooter(-setValue);
    }

    public void disable() {
        ro.setShooter(0);
    }

    /** AUTO CODE BELOW **/

    /**
     * Used for auto, will turn on the shooter at the target RPM.
     * @param targetRPM the target RPM to run at.
     */
    public void activate(int targetRPM) {
        speedPID.reset();
        speedPID.setSetPoint(targetRPM);
        ro.setShooter(speedPID.calculate(si.getShooterSpeed()));

    }


    public RobotOutput getRo()
    {
        return ro;
    }


}
