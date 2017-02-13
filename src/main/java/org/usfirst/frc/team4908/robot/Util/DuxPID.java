package org.usfirst.frc.team4908.robot.Util;

/**
 * @author Bill
 *         $
 */
public class DuxPID
{
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

        isDone(error);

        lastError = error;

        return convertMotors(PIDsum); // changes to motor values
    }

    public boolean isDone(double error)
    {
        if(error <= kEpsilon)
            return true;
        else
            return false;
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

}
