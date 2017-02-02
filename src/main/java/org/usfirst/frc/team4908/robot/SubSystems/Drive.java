package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Drive implements ISubSystem
{

    private DriverInput di;
    private SensorInput si;

    private double driveX;
    private double driveRot;

    private RobotOutput ro;

    public Drive(RobotOutput ro)
    {
        di = new DriverInput();
        si = new SensorInput();
        this.ro = ro;

        driveX = 0.0;
        driveRot = 0.0;
    }

    // TELEOPS
    public void calculate()
    {
        driveX = di.getDriveX();
        driveRot = di.getDriveRot();

        ro.setDriveMotors(driveX, driveRot);
    }

    public void disable()
    {

    }


    // COMMANDS
    public void driveToDistance()
    {

    }
}
