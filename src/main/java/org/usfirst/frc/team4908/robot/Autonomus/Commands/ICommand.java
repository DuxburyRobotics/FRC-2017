package org.usfirst.frc.team4908.robot.Autonomus.Commands;

/**
 *
 */
public abstract class ICommand {

    private double waitTime;
    private String type;


    public ICommand(int waitTime, String type){
        this.waitTime = waitTime;
        this.type = type;

    }


    public abstract void execute();


    //region Set & Get
    /**
     * SETTERS AND GETTER BOI
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public String getType() {
        return type;
    }

    //endregion

}
