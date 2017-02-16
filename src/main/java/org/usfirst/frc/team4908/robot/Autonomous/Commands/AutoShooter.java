package org.usfirst.frc.team4908.robot.Autonomous.Commands;

import org.usfirst.frc.team4908.robot.Input.SensorInput;
import org.usfirst.frc.team4908.robot.SubSystems.*;
import org.usfirst.frc.team4908.robot.Util.DuxPID;

/**
 *
 */
public class AutoShooter extends ICommand {

    private DuxPID PID;
    private double setValue;

    public AutoShooter(RobotOutput ro, SensorInput si) {
        super("Shooter", ro, si);

        PID = new DuxPID(0.0, 0.0, 0.0, 0.02, 0.0);

    }


    //region Auto Code

    @Override
    public void init() {
        //TODO: Vision code will go here
        setValue = 0;

    }

    public void update() {

        PID.reset();
        PID.setSetPoint(4900);
        setValue = PID.calculate(si.getShooterSpeed());
        ro.setShooter(setValue);

    }

    public void update(int targetRPM) {
        PID.reset();
        PID.setSetPoint(targetRPM);
        setValue = PID.calculate(si.getShooterSpeed());
        ro.setShooter(setValue);

    }

    public void finish()
    {
        hasFinished();

        ro.setShooter(0);
    }

    //endregion




}
