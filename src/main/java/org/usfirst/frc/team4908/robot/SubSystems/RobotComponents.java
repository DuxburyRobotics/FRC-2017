package org.usfirst.frc.team4908.robot.SubSystems;

import java.util.ArrayList;
import org.usfirst.frc.team4908.robot.Input.*;

/**
 * @author Bill
 *         $ Contains array of all subsystems, calcultes output values for each every update. Make sure you only have
 *         $ one of these in the robot or else it might get a little confused.
 */
public class RobotComponents
{
    private ArrayList<ISubSystem> subsystems;

    public RobotComponents(RobotOutput ro, SensorInput si, DriverInput di, VisionInput vi)
    {
        subsystems = new ArrayList<ISubSystem>();

        /**
         * DO NOT CHANGE THIS ORDER
         */


        /**
         * DO NOT CHANGE THIS ORDER
         */
    }

    public void update()
    {
        for (ISubSystem system:subsystems)
            system.calculate();
            
    }

    public void disable()
    {
        for(ISubSystem system:subsystems)
            system.disable();


    }

    /**
     * Individual update and disable commands
     */

    // idk if were ever gonna use the update and disable calls.........

    public void updateDrive() {
    subsystems.get(0).calculate();
    }
    public void updateClimb() {
        subsystems.get(1).calculate();
    }
    public void updateIntake() {
        subsystems.get(2).calculate();
    }
    public void updateShooter() {
        subsystems.get(3).calculate();
    }

    public void disableDrive() {
        subsystems.get(0).disable();
    }
    public void disableClimb() {
        subsystems.get(1).disable();
    }
    public void disableIntake() {
        subsystems.get(2).disable();
    }
    public void disableShooter() {
        subsystems.get(3).disable();
    }
}
