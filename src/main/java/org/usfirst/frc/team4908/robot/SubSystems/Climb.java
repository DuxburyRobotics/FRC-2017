package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Climb implements ISubSystem
{
    private RobotOutput ro;
    private DriverInput di;
    private SensorInput si;

    public Climb(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

    }


    public void calculate() {

    }

    public void disable() {

    }


    /**
     * Start the climbing until finished.
     */
    public void startClimb() {

    }



}
