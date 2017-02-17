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

    private DoubleSolenoid driveGearSolenoid;

    private RobotDrive rd;

    // SHOOTER SUBSYSTEM ===================================================
    private SpeedController shooterOne;
    private SpeedController shooterTwo;
    private SpeedController shooterThree;
    private SpeedController shooterFour;

    private DoubleSolenoid shooterHoodSolenoid;

    private SpeedController elevator;
    
    // CLIMB SUBSYSTEM ====================================================
    private SpeedController climbMotor;

    // INTAKE SUBSYSTEM ===================================================
    private SpeedController intakeMotor;

    private DoubleSolenoid intakeSolenoid;

    // GEAR SUBSYSTEM =====================================================

    private DoubleSolenoid gearSolenoid;

    public RobotOutput()
    {
        // DRIVE SUBSYSTEM =====================================================
        frontLeftMotor = new CANTalon(2);
        rearLeftMotor = new CANTalon(3);
        frontRightMotor = new CANTalon(1);
        rearRightMotor = new CANTalon(0);

        driveGearSolenoid = new DoubleSolenoid(4, 5);

        rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

        // SHOOTER SUBSYSTEM ===================================================
        shooterOne = new VictorSP(3);
        shooterTwo = new VictorSP(4);
        shooterThree = new VictorSP(6);
        shooterFour = new VictorSP(5);
        
        elevator = new VictorSP(1);

        // shooterHoodSolenoid = new DoubleSolenoid(-1, -1);

        // CLIMB SUBSYSTEM ====================================================
        // climbMotor = new VictorSP(-1);

        // INTAKE SUBSYSTEM ===================================================
        // intakeMotor = new VictorSP(-1);

        // GEAR SUBSYSTEM =====================================================

        // intakeSolenoid = new DoubleSolenoid(-1, -1);
    }

    // DRIVE SUBSYSTEM =====================================================

    public void setDriveMotors(double x, double r)
    {
        rd.arcadeDrive(x, r);
    }

    public void setHighGear()
    {
        driveGearSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void setLowGear()
    {
        driveGearSolenoid.set(DoubleSolenoid.Value.kReverse);
    }


    // SHOOTER SUBSYSTEM ===================================================
    public void setShooter(double val)
    {
        shooterOne.set(val);
        shooterTwo.set(val);
        shooterThree.set(-val);
        shooterFour.set(-val);
    }

    public void setShooterHood()
    {

    }
    
    public void setElevator(double val)
    {
    	elevator.set(val);
    }

    // CLIMB SUBSYSTEM ====================================================

    public void setClimbMotor(double val)
    {
        //intakeMotor.set(val);
    }

    // INTAKE SUBSYSTEM ===================================================

    public void deployIntake()
    {
        //intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retractIntake()
    {
        //intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setIntakeMotor(double val)
    {
        //intakeMotor.set(val);
    }

    // GEAR SUBSYSTEM =====================================================

    public void deployGear()
    {
        //gearSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retractGear()
    {
        //gearSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
