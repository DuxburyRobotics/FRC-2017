package org.usfirst.frc.team4908.robot.Subsystems;

import java.util.ArrayList;

/**
 * @author Bill
 *         $ Contains array of all subsystems, calcultes output values for each every update
 */
public class RobotComponents
{
    private ArrayList<ISubsystem> subsystems;

    public RobotComponents()
    {
        subsystems = new ArrayList<ISubsystem>();

        /**
         * DO NOT CHANGE THIS ORDER
         */
        subsystems.add(new Drive());
        subsystems.add(new Climb());
        subsystems.add(new Intake());
        subsystems.add(new Shooter());
        /**
         * DO NOT CHANGE THIS ORDER
         */
    }

    public void update()
    {

        for (ISubsystem system:subsystems)
            system.calculate();

    }

    public void disable()
    {
        for(ISubsystem system:subsystems)
            system.disable();


    }

    /**
     * Individual update and disable commands
     */

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
