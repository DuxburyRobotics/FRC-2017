package org.usfirst.frc.team4908.robot.SubSystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.*;

/**
 * NOTE: This is only found in robotComponents and should NOT be made an object of anywhere else!!!
 * Actions: Contains all objects and setters of every motor controller/solenoid
 */
public class RobotOutput
{
    // DRIVE SUBSYSTEM =====================================================
    private SpeedController frontLeftMotor;
    private SpeedController rearLeftMotor;
    private SpeedController frontRightMotor;
    private SpeedController rearRightMotor;
    private RobotDrive rd;


    public RobotOutput()
    {
        // DRIVE SUBSYSTEM =====================================================
        frontLeftMotor = new CANTalon(2);// real robot 2
        rearLeftMotor = new CANTalon(3); // real robot 3
        frontRightMotor = new CANTalon(1);// real robot 1
        rearRightMotor = new CANTalon(4); // real robot 4
        rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    // DRIVE SUBSYSTEM =====================================================

    public void setDriveMotors(double x, double r)
    {
        rd.arcadeDrive(x, (r*0.75));
    }
}
