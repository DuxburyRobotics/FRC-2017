package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;

/**
 * Created by kyleknobloch on 1/26/17,
 * For
 * *
 * Actions:
 */
public class AutoDrive extends ICommand {

    double distance;

    private double kT;
    private double k1;
    private double k2;
    private double k3;

    private double kV = 1/19.0;
    private double kA = 0.0;

    private SetPoint setpoint;

    public AutoDrive(String type, RobotOutput ro, SensorInput si, double distance)
    {
        super(type, ro, si);
        this.distance = distance;
        setpoint = new SetPoint();
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

        ro.setHighGear();

        System.out.println("HEREHEREHERE");
    }

    public void update(double time)
    {
        setpoint.acceleration = (10.0*Math.sin(k1*time));
        setpoint.velocity = (k2*(1-(Math.cos(k1*time))));
        setpoint.position = (k2*(time-(k3*Math.sin(k1*time))));


        ro.setDriveMotors(kV * setpoint.velocity + kA * setpoint.acceleration, 0.0);
    
        //System.out.println("here " + setpoint.velocity + " time: " + time + " " + k1 + " " + kT + " " + k2 + " " +k3);
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
