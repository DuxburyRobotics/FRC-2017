package org.usfirst.frc.team4908.robot.Util;

import edu.wpi.first.wpilibj.Preferences;

/**
 * @author Bill
 *         $
 */
public class DuxPID
{
	private double count = 0;
	
    private double kP;
    private double kI;
    private double kD;
    private double kEpsilon;

    private double setPoint;
    private double error;
    private double errorSum;
    private double PIDsum;
    private double lastError;

    private double calcP;
    private double calcI;
    private double calcD;

    private double maxMotorValue;

    private double doneThreshold = 10;
    
    private double calcValue;

    public DuxPID(double p, double i, double d, double e, double m)
    {
        this.kP = p;
        this.kI = i;
        this.kD = d;
        this.kEpsilon = e;
        this.maxMotorValue = m;

        this.setPoint = 0.0;
        this.error = 0.0;
        this.errorSum = 0.0;
        this.lastError = 0.0;
    }

    public double calculate(double position)
    {	
        error = setPoint - position;
        errorSum += error;

        calcP = kP * error;

        calcI = kI * errorSum;

        calcD = kD * (error - lastError);

        PIDsum = calcP + calcI + calcD; // value is still in RPM's not motor values

        lastError = error;

        System.out.println(error + "\t\t\t" + setPoint + "\t" + kP + "\t" + kI + "\t" + kD);
        
        
        return convertMotors(PIDsum); // changes to motor values
    }

    public boolean isDone()
    {
        if(Math.abs(error) <= kEpsilon)
            count++;
        else
        	count = 0;

        return (count >= doneThreshold);
    }

    public double convertMotors(double value)
    {
        value = (value/maxMotorValue);

        return value;
    }

    public void setSetPoint(double setPoint)
    {
        this.setPoint = setPoint;
    }

    public void reset()
    {
        error = 0.0;
        errorSum = 0.0;
        lastError = 0.0;
    }

    public void setMaxMotorValue(double maxMotorValue)
    {
        this.maxMotorValue = maxMotorValue;
    }

    public double getMaxMotorValue()
    {
        return maxMotorValue;
    }
    
    public void setP(double p)
    {
    	this.kP = p;
    }
    
    public void setI(double I)
    {
    	this.kI = I;
    }
    
    public void setD(double d)
    {
    	this.kD = d;
    }
    
    public double getP()
    {
    	return kP;
    }
    
    public double getI()
    {
    	return kI;
    }
    
    public double getD()
    {
    	return kD;
    }

}
