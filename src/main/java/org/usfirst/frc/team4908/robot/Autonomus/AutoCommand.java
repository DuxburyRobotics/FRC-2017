package org.usfirst.frc.team4908.robot.Autonomus;

import java.util.ArrayList;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class AutoCommand {

    private int instruction;
    private long lastTime;
    private boolean firstRun;
    private ArrayList<InstructionSet> autoInstructionList;
    private InstructionSet theSet;


    //TODO: Figure out a way to set theSet from the DuxDash


    public AutoCommand() {
        this.instruction = 0;
        this.firstRun = true;

    }

    public void init() {
        this.firstRun = true;


    }

    public void periodic() {
        if (firstRun) {
            lastTime = System.nanoTime();
            firstRun = false;
        }

        theSet.runCommand(instruction);
        incrementTime(theSet.getCommand(instruction).getWaitTime());


    }


    /**
     * Wait the secondsToWait until moving the command up.
     * @param secondsToWait double seconds to waiy
     */
    private void incrementTime(double secondsToWait) {
        if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000)) {
            lastTime = System.nanoTime();
            instruction++;
        }
        
    }



}
