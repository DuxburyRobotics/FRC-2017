package org.usfirst.frc.team4908.robot.Autonomus.Commands;

/**
 *
 */
public abstract class ICommand {

    private double waitTime;
    private String type;
    private boolean hasExicuted;


    public ICommand(int waitTime, String type){
        this.waitTime = waitTime;
        this.type = type;
        this.hasExicuted = false;

    }


    public void execute() {
        if (!isHasExicuted()) {
            HasExicuted();
            //code here



        }
    }


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

    public boolean isHasExicuted() {
        return hasExicuted;
    }

    public void HasExicuted() {
        this.hasExicuted = true;
    }

    //endregion

}
