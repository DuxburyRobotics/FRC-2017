package org.usfirst.frc.team4908.robot.SubSystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;

/**
 * NOTE: This is only found in robotComponents and should NOT be made an object of anywhere else!!!
 * Actions: Contains all objects and setters of every motor controller/solenoid
 */
public class RobotOutput
{
    private SpeedController shooterOne;
    private SpeedController shooterTwo;
    private SpeedController shooterThree;
    private SpeedController shooterFour;

    private SpeedController frontLeftMotor;
    private SpeedController rearLeftMotor;
    private SpeedController frontRightMotor;
    private SpeedController rearRightMotor;

    private DoubleSolenoid driveGearSolenoid;

    private RobotDrive rd;

    public RobotOutput()
    {
        //shooterOne = new VictorSP(0);
        //shooterTwo = new VictorSP(1);
        //shooterThree = new VictorSP(2);
        //shooterFour = new VictorSP(3);

        frontLeftMotor = new CANTalon(2);
        rearLeftMotor = new CANTalon(3);
        frontRightMotor = new CANTalon(1);
        rearRightMotor = new CANTalon(0);

        driveGearSolenoid = new DoubleSolenoid(4, 5);

        rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public void setDriveMotors(double x, double r)
    {
        rd.arcadeDrive(x, r);
    }

    public void setHighGear(boolean b)
    {
        // if b is true, set to high gear
        if(b)
        {
            driveGearSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        else if (!b)
        {
            driveGearSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else
        {
            driveGearSolenoid.set(DoubleSolenoid.Value.kOff);
        }
    }

    public void setShooter(double val)
    {
        //shooterOne.set(val);
        //shooterTwo.set(val);
        //shooterThree.set(val);
        //shooterFour.set(val);
    }

}
