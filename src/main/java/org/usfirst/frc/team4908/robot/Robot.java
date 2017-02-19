
package org.usfirst.frc.team4908.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.usfirst.frc.team4908.robot.Autonomous.AutoCommand;
import org.usfirst.frc.team4908.robot.DuxDashboard.DuxDash;
import org.usfirst.frc.team4908.robot.Input.*;
import org.usfirst.frc.team4908.robot.SubSystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot
{

    private RobotOutput robotOutput;
    private SensorInput sensorInput;
    private DriverInput driverInput;
    private VisionInput visionInput;

    private RobotComponents robotComponents;
    private AutoCommand autoCommand;
    private DuxDash duxDash;
    
    private CameraServer camera;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     *
     * Initialised the auto, teleOp and Java Dashboard
     */
    @Override
    public void robotInit()
    {
    	/**
    	 * http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/
    	 * 
    	 * ahrs = new AHRS(SerialPort.Port.kMXP); // Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB 
    	 */
        robotOutput = new RobotOutput();
        sensorInput = new SensorInput();
        driverInput = new DriverInput();
        visionInput = new VisionInput();

        robotComponents = new RobotComponents(robotOutput, sensorInput, driverInput, visionInput);
        autoCommand = new AutoCommand(robotOutput, sensorInput);
        duxDash = new DuxDash();
    }


    //region Autonomous
    /**
     * This function is called to init the autonomous code
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
        robotComponents.update();
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
        robotComponents.disable();
    }


    /**
     * This function gets the RobotComponents class that controls the robot.
     * @return RobotComonents
     */
    public RobotComponents getRobotComponents() {
        return robotComponents;
    }
}
