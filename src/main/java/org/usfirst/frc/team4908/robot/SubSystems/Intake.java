package org.usfirst.frc.team4908.robot.SubSystems;


import org.usfirst.frc.team4908.robot.Input.DriverInput;
import org.usfirst.frc.team4908.robot.Input.SensorInput;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class Intake implements ISubSystem
{
    private RobotOutput ro;
    private DriverInput di;
    private SensorInput si;

    public Intake(DriverInput di, SensorInput si, RobotOutput ro)
    {
        this.di = di;
        this.si = si;
        this.ro = ro;

    }


    public void calculate() {

    }

    public void disable() {

    }


    public void activateIntake() {

    }

    public void dissableIntake() {

    }

}

