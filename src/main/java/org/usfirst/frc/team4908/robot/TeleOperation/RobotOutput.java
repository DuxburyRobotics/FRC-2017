package org.usfirst.frc.team4908.robot.TeleOperation;

import edu.wpi.first.wpilibj.SpeedController;

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

    public RobotOutput()
    {

    }

    public void setShooter(double val)
    {
        shooterOne.set(val);
        shooterTwo.set(val);
        shooterThree.set(val);
        shooterFour.set(val);
    }
}
