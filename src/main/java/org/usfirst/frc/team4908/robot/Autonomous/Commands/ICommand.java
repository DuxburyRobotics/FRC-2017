package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.Input.VisionInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;

/**
 *
 */
public abstract class ICommand {

    private String type;
    private boolean isFinished;
    private boolean isFirstRun;

    public RobotOutput ro;
    public SensorInput si;
    public VisionInput vi;


    public ICommand(String type, RobotOutput ro, SensorInput si, VisionInput vi) {
        this.type = type;
        this.ro = ro;
        this.si = si;
        this.vi = vi;
        this.isFinished = false;
        this.isFirstRun = true;

    }


    //region Auto Code

    public abstract void init();

    public void update(double time) {

    }

    public void finish() {

    }

    //endregion


    //region Set & Get
    /**
     * SETTERS AND GETTER BOI
     */

    public String getType() {
        return type;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void hasFinished() {
        this.isFinished = true;
    }

    public boolean isFirstRun() {
        return isFirstRun;
    }

    public void firstRun() {
        isFirstRun = false;
        init();
    }



    //endregion

}
