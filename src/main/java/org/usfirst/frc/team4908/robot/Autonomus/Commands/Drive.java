package org.usfirst.frc.team4908.robot.Autonomus.Commands;

/**
 * Created by kyleknobloch on 1/26/17,
 * For
 * *
 * Actions:
 */
public class Drive extends ICommand {

    private int speed;

    public Drive (int waitTime, String type, int speed) {
        super(waitTime, type);
        this.speed = speed;

    }

    public void execute() {

    }


}
