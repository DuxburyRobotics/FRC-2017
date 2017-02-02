package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.SubSystems.RobotComponents;

/**
 *
 */
public abstract class ICommand {

    private String type;
    private RobotComponents rc;
    private boolean isFinished;
    private boolean isFirstRun;


    public ICommand(String type, RobotComponents rc){
        this.rc = rc;
        this.type = type;
        this.isFinished = false;
        this.isFinished = true;

    }


    //region Auto Code

    public void start() {

    }

    public void update() {

    }

    public boolean finish() {
        return isFinished;
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
        start();
    }

    public RobotComponents getRc() {
        return rc;
    }

    //endregion

}
