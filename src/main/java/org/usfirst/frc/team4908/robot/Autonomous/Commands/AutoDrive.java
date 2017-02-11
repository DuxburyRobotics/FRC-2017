package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.SubSystems.RobotComponents;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * Created by kyleknobloch on 1/26/17,
 * For
 * *
 * Actions:
 */
public class AutoDrive extends ICommand {

    double distance;

    private RobotComponents rc;

    private double kT;
    private double k1;
    private double k2;
    private double k3;

    private double kV = 1.0/19.0;
    private double kA = 1.0/19.0;

    Setpoint setpoint;

    public AutoDrive(String type, RobotComponents rc, double distance)
    {
        super(type, rc);
        this.rc = rc;
        this.distance = distance;
    
        setpoint = new Setpoint();
    }

    //region Auto Code

    public void init()
    {
        kT = Math.sqrt((2.0*Math.PI*distance)/10.0);
        k1 = ((2.0*Math.PI)/kT);
        k2 = (10.0/k1);
        k3 = (1.0/k1);

        setpoint.acceleration = 0;
        setpoint.velocity = 0;
        setpoint.position = 0;

        rc.getDrive().getRo().setHighGear();

        System.out.println("HEREHEREHERE");
    }

    public void update(double time)
    {
        setpoint.acceleration = (10.0*Math.sin(k1*time));
        setpoint.velocity = (k2*(1-(Math.cos(k1*time))));
        setpoint.position = (k2*(time-(k3*Math.sin(k1*time))));


        rc.getDrive().getRo().setDriveMotors(kV * setpoint.velocity + kA * setpoint.acceleration, 0.0);
    
        System.out.println(setpoint.velocity + " \t" + (1.0/19.0)*setpoint.velocity + " \t" + setpoint.acceleration + " \t" + setpoint.acceleration*(kA));
    } 
    
    
    public boolean isFinished()
    {
    	return false;
    }

    public boolean finish()
    {
        return true;

    }

    //endregion




}
