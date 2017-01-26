package org.usfirst.frc.team4908.robot.Autonomus;

/**
 * Created by kyleknobloch on 1/25/17,
 * For
 * *
 * Actions:
 */
public class Command {

    private double waitTime;


    public Command() {

        waitTime = 1;   //most likley value

    }


    public void execute() {}


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

    //endregion

}
