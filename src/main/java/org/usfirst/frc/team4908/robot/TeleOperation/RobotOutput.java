package org.usfirst.frc.team4908.robot.TeleOperation;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
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

    private RobotDrive rd;


    public RobotOutput()
    {
        shooterOne = new VictorSP(0);
        shooterTwo = new VictorSP(1);
        shooterThree = new VictorSP(2);
        shooterFour = new VictorSP(3);

        frontLeftMotor = new CANTalon(0);
        rearLeftMotor = new CANTalon(1);
        frontRightMotor = new CANTalon(2);
        rearRightMotor = new CANTalon(3);

        rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public void setDriveMotors(double x, double r)
    {
        rd.arcadeDrive(x, r);
    }

    public void setShooter(double val)
    {
        shooterOne.set(val);
        shooterTwo.set(val);
        shooterThree.set(val);
        shooterFour.set(val);
    }
}
