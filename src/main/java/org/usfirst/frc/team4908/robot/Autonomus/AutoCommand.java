package org.usfirst.frc.team4908.robot.Autonomus;

import org.usfirst.frc.team4908.robot.Subsystems.RobotComponents;

import java.util.ArrayList;

/**
 * Created by kyleknobloch on 1/24/17,
 * For
 * *
 * Actions:
 */
public class AutoCommand {

    private int instruction;
    private RobotComponents robotComponents;
    private int commandNumber;
    private long lastTime;
    private boolean firstRun;
    private ArrayList<InstructionSet> autoInstructionList;
    private InstructionSet theSet;


    //TODO: Figure out a way to set theSet from the DuxDash


    public AutoCommand() {
        this.instruction = 0;
        this.commandNumber = 0;
        this.firstRun = true;

    }

    public AutoCommand(RobotComponents robotComponents) {
        this.instruction = 0;
        this.commandNumber = 0;
        this.firstRun = true;
        this.robotComponents = robotComponents;
    }

    public AutoCommand(int instruction) {
        this.instruction = instruction;
        this.commandNumber = 0;
        this.firstRun = true;
    }

    public void init() {
        this.firstRun = true;


    }

    public void periodic() {
        if (firstRun) {
            lastTime = System.nanoTime();
            theSet = autoInstructionList.get(instruction);
            commandNumber = 0;
            firstRun = false;
        }

        theSet.runCommand(commandNumber);
        incrementTime(theSet.getCommand(commandNumber).getWaitTime());


    }


    /**
     * Wait the secondsToWait until moving the command up.
     * @param secondsToWait double seconds to waiy
     */
    private void incrementTime(double secondsToWait) {
        if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000)) {
            lastTime = System.nanoTime();
            commandNumber++;
        }
        
    }


    /**
     * This must be called before we start autonomus.
     */
    public void setInstruction(int instruction) {
        this.instruction = instruction;
    }

    public RobotComponents getRobotComponents() {
        return robotComponents;
    }

}
