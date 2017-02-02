package org.usfirst.frc.team4908.robot.Subsystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.TeleOperation.RobotOutput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Drive implements ISubsystem
{

    private DriverInput di;
    private SensorInput si;

    private double driveX;
    private double driveRot;

    private RobotOutput ro;

    public Drive()
    {
        di = new DriverInput();
        si = new SensorInput();
        ro = new RobotOutput();

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
