package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;

/**
 * Created by kyleknobloch on 1/26/17,
 * For
 * *
 * Actions:
 */
public class AutoIntake extends ICommand{


    public AutoIntake(RobotOutput ro, SensorInput si) {
        super("Intake", ro, si);
    }

    //region Auto Code

    public void init() {
        System.out.print("\n\nAUTO: WARNING THIS COMMAND WILL NOT WORK. (AutoIntake)");
    }

    public void update(double time) {

    }

    public void finish() {

    }

    //endregion





}
