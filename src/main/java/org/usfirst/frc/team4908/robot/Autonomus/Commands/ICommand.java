package org.usfirst.frc.team4908.robot.Autonomus.Commands;

/**
 *
 */
public abstract class ICommand {

    private String type;
    private boolean isFinished;
    private boolean isFirstRun;


    public ICommand(String type){
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

    //endregion

}
