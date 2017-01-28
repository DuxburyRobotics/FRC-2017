
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team4908.robot.Autonomus.AutoCommand;
import org.usfirst.frc.team4908.robot.DuxDashboard.DuxDash;
import org.usfirst.frc.team4908.robot.TeleOperation.TeleopComponents;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot
{
    private TeleopComponents teleopComponents;
    private AutoCommand autoCommand;
    private DuxDash duxDash;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     *
     * Initialised the auto, teleOp and Java Dashboard
     */
    @Override
    public void robotInit()
    {
        teleopComponents = new TeleopComponents();
        autoCommand = new AutoCommand();
        duxDash = new DuxDash();

    }


    //region Autonomus
    /**
     * This function is called to start the autonomous code
     */
    public void autonomousInit()
    {
        autoCommand.init();


    }


    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic()
    {

        autoCommand.periodic();

    }

    //endregion


    //region TeleOperation
    /**
     *
     */
    @Override
    public void teleopInit() {

    }


    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic()
    {
        teleopComponents.update();
    }

    //endregion


    //region Test
    /**
     *
     */
    @Override
    public void testInit() {


    }


    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic()
    {
    
    }

    //endregion


    public void disabledInit()
    {
        teleopComponents.disable();
    }


    
}
