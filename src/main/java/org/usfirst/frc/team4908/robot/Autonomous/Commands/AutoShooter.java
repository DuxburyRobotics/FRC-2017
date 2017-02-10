package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.SubSystems.*;

/**
 *
 */
public class AutoShooter extends ICommand {

    private Shooter shooter;

    public AutoShooter(RobotComponents rc) {
        super("Shooter", rc);

    }


    //region Auto Code

    @Override
    public void init() {
        shooter = getRc().getShooter();
        //TODO: Vision code will go here
    }

    public void update() {
        boolean keepGoing = true; //TODO: Change this to some weird vision stuff!

        if (keepGoing) //some vision shit goes right here that's really cool bro.
            shooter.activate(4800);
        else
            hasFinished();
    }

    public void update(int targetRPM) {
        shooter.activate(targetRPM);
    }

    public boolean finish() {
        if (isFinished()) {
            shooter.disable();
            super.hasFinished();
            return super.finish();
        } else
            return false;

    }

    //endregion




}
