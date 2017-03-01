package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.RobotOutput;
import org.usfirst.frc.team4908.robot.Util.DuxPID;
import org.usfirst.frc.team4908.robot.Util.SetPoint;

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

    private double kV = 1.0/7.0;
    private double kA = 1.0/50.0;

    private SetPoint setpoint;
    
    private double setSpeed;
    
    DuxPID PID;
    
    private double time;

    public AutoDrive(RobotOutput ro, SensorInput si, double distance)
    {
        super("Drive", ro, si);
        this.distance = distance;
        setpoint = new SetPoint();
    
        PID = new DuxPID(0.5, 0.0, 0.0, 0.1, 7.0);
    
        setSpeed =0.0;
        
        System.out.println("construct");
    }

    //region Auto Code

    public void init()
    {
    	
    	System.out.println("init");
    	this.time = 0.0;
    	
        kT = Math.sqrt((2.0*Math.PI*distance)/5.0);
        k1 = ((2.0*Math.PI)/kT);
        k2 = (5.0/k1);
        k3 = (1.0/k1);

        setpoint.acceleration = 0;
        setpoint.velocity = 0;
        setpoint.position = 0;

        //ro.setLowGear();
    }

    public void update(double time)
    {
    	
    	
    	System.out.println("periodic");
    	
    	this.time = time;
    	
        setpoint.acceleration = (10.0*Math.sin(k1*time));
        setpoint.velocity = (k2*(1-(Math.cos(k1*time))));
        setpoint.position = (k2*(time-(k3*Math.sin(k1*time))));

        PID.setSetPoint(setpoint.position);

        //PID.calc should intake from the SI.encoder for distance. 
        //tune Ka (higher or lower) then tune PID
        
        setSpeed = kV * setpoint.velocity + kA * setpoint.acceleration;
        
        //ro.setDriveMotors(setSpeed, 0.0);
    
        System.out.println(setSpeed);
        //System.out.println(setpoint.velocity + " \t" + kV*setpoint.velocity + " \t" + setpoint.acceleration + " \t" + setpoint.acceleration*(kA));
    } 
    
    
    public boolean isFinished()
    {
    	return time >= kT; //PID.isDone();
    }

    public void finish()
    {

    }

    //endregion




}
