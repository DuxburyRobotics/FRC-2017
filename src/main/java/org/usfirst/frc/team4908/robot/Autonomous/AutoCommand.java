package org.usfirst.frc.team4908.robot.Autonomous;

import org.usfirst.frc.team4908.robot.Autonomous.Commands.ICommand;
import org.usfirst.frc.team4908.robot.SubSystems.RobotComponents;

import java.util.ArrayList;

/**
 * Created by kyleknobloch
 */
public class AutoCommand {

    private int instructionSet;
    private RobotComponents rc;
    private int commandNumber;
    private boolean firstRun;
    private ArrayList<InstructionSet> autoInstructionList;
    private InstructionSet theSet;


    //TODO: Figure out a way to set theSet from the DuxDash

    //region Constructors

    /**
     * You must ALWAYS pass in the rc or else nothing will move. Make sure it's the one from the Robot class
     * so that you don't anciently have two in the Robot (because that would be weird).
     * @param rc the Class that controls the robot.
     */
    public AutoCommand(RobotComponents rc) {
        this.instructionSet = 0;
        this.commandNumber = 0;
        this.firstRun = true;
        this.rc = rc;
        this.autoInstructionList = new ArrayList<InstructionSet>();
        generateDefaultAuto();
    }

    //endregion

    public void init() {
        this.firstRun = true;
    }


    /**
     * This function is called periodically from the Robot class when in auto mode. This class will check to see where the
     * InstructionSet is and when to move onto the next command.
     *
     * NOTE: This is designed to change thc command when the first command has decided to call finish() and move to the
     * next command. Until then this will keep calling update() in ICommand.
     */
    public void periodic() {

        if(firstRun) {
            firstRun = false;

            if (theSet == null && instructionSet >= 0) //if theSet was empty and there is a instructionSet we want to use
                theSet = autoInstructionList.get(instructionSet);

            else {
                theSet = autoInstructionList.get(0); //just get the default one that does nothing since there was nothing set to use
                                                     //This means that Auto did not know what to run and is using default one.
                System.out.println("AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n" +
                        "AUTOCOMMAND:: There was no instrctionSet defined. Will use default one that does nothing (index 0)\n");

            } //else
        } //if

        if(theSet.getCommand(commandNumber).isFirstRun())
            theSet.getCommand(commandNumber).firstRun(); //set command firstRun to false and run Start


        //now run update
        theSet.getCommand(commandNumber).update();

        //check if finished
        if (theSet.getCommand(commandNumber).isFinished()) {
            theSet.getCommand(commandNumber).finish(); //call finish function to end command
            commandNumber++; //move to next command
        }

    }


    /**
     * Generate pre-defined InstructionsSets here.
     * This always makes sure that the first one is the default one.
     */
    private void generateDefaultAuto() {

        /**
         * index: 0
         *
         * Actions:
         * None.
         */

        InstructionSet default0 = new InstructionSet(rc);

        default0.addInstruction(new ICommand("do_Nothing") {
            @Override
            public void firstRun() {
                //do nothing
            }
        });
    }


    /**
     * This must be called before we start auto.
     */
    public void setInstructionSet(int instructionSet) {
        this.instructionSet = instructionSet;
        this.theSet = autoInstructionList.get(instructionSet);
    }

    public void setInstructionSet(InstructionSet instructionSet) {
        this.theSet = instructionSet;
        this.instructionSet = -1; //this value is not useful as the theSet was not in the array list in the first place.

    }

    public RobotComponents getRc() {
        return rc;
    }


    //region depreciated

        /*
    public AutoCommand() {
        this.instructionSet = 0;
        this.commandNumber = 0;
        this.firstRun = true;
        this.autoInstructionList = new ArrayList<InstructionSet>();

    }
    */


    /**
     * This function is called periodically from the Robot class when in auto mode. This class will check to see where the
     * InstructionSet is and when to move onto the next command.
     *
     * NOTE: This is designed for a time-based system and NOT a sensor based system
     *
     public void periodic() {
     if (firstRun) {
     lastTime = System.nanoTime();
     theSet = autoInstructionList.get(instructionSet);
     commandNumber = 0;
     firstRun = false;
     }

     theSet.runCommand(commandNumber);
     incrementTime(theSet.getCommand(commandNumber).getWaitTime());


     } */
    /**
     * Wait the secondsToWait until moving the command up.
     * @param secondsToWait double seconds to waiy
     *
    private void incrementTime(double secondsToWait) {
    if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000)) {
    lastTime = System.nanoTime();
    commandNumber++;
    }

    }*/
    //endregion
}
